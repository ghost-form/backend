package com.gdscGCC.ghostform.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gdscGCC.ghostform.Dto.Ask.AskListRequestDto;
import com.gdscGCC.ghostform.Dto.ChatGPT.ChatGPTResponseDto;
import com.gdscGCC.ghostform.Dto.Project.ProjectRequestDto;
import com.gdscGCC.ghostform.Dto.Project.ProjectResponseDto;
import com.gdscGCC.ghostform.Dto.Run.RunListDto;
import com.gdscGCC.ghostform.Dto.Run.RunRequestDto;
import com.gdscGCC.ghostform.Dto.Run.RunResponseDto;
import com.gdscGCC.ghostform.Entity.Ask;
import com.gdscGCC.ghostform.Entity.Project;
import com.gdscGCC.ghostform.Entity.Run;
import com.gdscGCC.ghostform.Service.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects")
@Tag(name = "Project", description = "Project API")
public class ProjectAPIController {
    private final ProjectService projectService;
    private final ChatGPTService chatGPTService;
    private final RunService runService;
    private final CsvService csvService;
    private final FileService fileService;


    /** 한 개의 프로젝트 조회 */
    @GetMapping("/{project_id}")
    public ResponseEntity<ProjectResponseDto> projectGet(@PathVariable Long project_id){
        return ResponseEntity.status(HttpStatus.OK).body(projectService.findById(project_id));
    }

    /** 모든 프로젝트 조회 */
    @GetMapping("/all")
    public List<Project> projectListGet(){
        return projectService.findAll();
    }

    /** 한 개의 프로젝트 생성 */
    @PostMapping("")
    public Long projectSave(@RequestBody ProjectRequestDto requestDto){
        return projectService.save(requestDto);
    }

    /** 한 개의 프로젝트 삭제 */
    @DeleteMapping("")
    public void projectDelete(@PathVariable Long project_id){
        projectService.delete(project_id);
    }

    /** 한 개의 프로젝트 수정 */
    @PutMapping("/{project_id}")
    public ResponseEntity<ProjectResponseDto> projectUpdate(@PathVariable Long project_id, @RequestBody ProjectRequestDto requestDto){
        System.out.println("id는!!! : " + project_id);
        return ResponseEntity.status(HttpStatus.OK).body(projectService.update(project_id, requestDto));
    }

    /** ChatGPT로부터 Stream 형태의 답변 받아오기 */
    @PostMapping(value = "/{project_id}/ask", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> makeQuestionStream (@PathVariable Long project_id, @RequestBody AskListRequestDto askListRequestDto) {
        ProjectResponseDto project = projectService.findById(project_id);
        String question = project.getContent();
        for (Ask a : askListRequestDto.getAskList()) {
            question = question.replace("{{" + a.getKey() + "}}", a.getValue());
        }
        try {
            return chatGPTService.getChatGptStreamResponse(question);
        } catch (JsonProcessingException e) {
            log.warn(e.toString());
            return Flux.empty();
        }
    }

    /** ChatGPT로부터 JSON 형태의 답변 받아오기 */
    @PostMapping(value = "/{project_id}/ask/json")
    public ResponseEntity<ChatGPTResponseDto> makeQuestion (@PathVariable Long project_id, @RequestBody AskListRequestDto askListRequestDto) {
        ProjectResponseDto project = projectService.findById(project_id);
        String question = project.getContent();
        for (Ask a : askListRequestDto.getAskList()) {
            question = question.replace("{{" + a.getKey() + "}}", a.getValue());
        }
        ChatGPTResponseDto chatGPTResponseDto = null;
        try {
            chatGPTResponseDto = chatGPTService.askQuestion(question);
        } catch (Exception e) {
            log.warn(e.toString());
        }
        return ResponseEntity.status(HttpStatus.OK).body(chatGPTResponseDto);
    }


    /** Project의 모든 변수 조회
     * RequestParameter로 project_id를 받아옴*/
    @GetMapping("/{project_id}/variables")
    public ResponseEntity<String> getAllVariables(@PathVariable Long project_id){
        return ResponseEntity.status(HttpStatus.OK).body(projectService.findById(project_id).getVariables());
    }

    /** Project에 한 개의 변수 생성
     * RequestParameter로 project_id를 받아옴
     * RequestBody로 생성할 JSON을 받아옴 */
    @PostMapping("/{project_id}/variables")
    public ResponseEntity<ProjectResponseDto> updateVariable(@PathVariable Long project_id, @RequestBody String variable) {
        ProjectResponseDto responseDto = projectService.findById(project_id);
        ProjectRequestDto requestDto = new ProjectRequestDto(responseDto);
        requestDto.setVariables(variable);
        requestDto.setLastModifiedDate();
        projectService.update(requestDto.getProject_id(), requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(projectService.findById(project_id));
    }

    /** project의 run list를 겨옴 */
    @GetMapping("/{project_id}/run")
    public ResponseEntity<List<Run>> getRunList(@PathVariable Long project_id) {
        ProjectResponseDto responseDto = projectService.findById(project_id);
        List<Run> runs = responseDto.getRuns();
        return ResponseEntity.status(HttpStatus.OK).body(runs);
    }

    /** run 한개를 추가함 */
    @PostMapping("/{project_id}/run")
    public ResponseEntity<RunResponseDto> addRun(@PathVariable Long project_id, @RequestBody RunRequestDto requestDto) {
        Project project = projectService.findByIdGetProject(project_id);
        requestDto.setProject(project);
        requestDto.setStatus(Run.RunStatus.START);
        Run run = requestDto.toEntity();
        project.addRun(run);
        runService.save(run);
        return ResponseEntity.status(HttpStatus.OK).body(new RunResponseDto(run));
    }

    /** csv 파일을 기반으로 run 여러개를 추가함 */
    @PostMapping("/{project_id}/upload-file")
    public ResponseEntity<List<RunResponseDto>> addRunList(@PathVariable Long project_id, @RequestParam MultipartFile multipartFile, HttpServletRequest request) {
        try {
            File file = fileService.save(multipartFile, request);
            ProjectResponseDto project = projectService.findById(project_id);
            Project p = projectService.findByIdGetProject(project_id);
            List<String> questions = csvService.makeQuestions(new FileInputStream(file), project);

            List<RunResponseDto> output = new ArrayList<>();
            for (String question : questions) {
                RunRequestDto requestDto = new RunRequestDto();
                requestDto.setProject(p);
                requestDto.setStatus(Run.RunStatus.START);
                requestDto.setVariables(project.getVariables());
                requestDto.setData("");
                requestDto.setContent(question);

                Run run = requestDto.toEntity();
                runService.save(run);

                p.addRun(run);
                output.add(new RunResponseDto(run));
            }

            return ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** Run 실행, run의 id만 필요 */
    @PostMapping("/{project_id}/execute")
    public ResponseEntity<List<ChatGPTResponseDto>> execute(@PathVariable Long project_id, @RequestBody RunListDto runListDto) {
        List<ChatGPTResponseDto> answerList = new ArrayList<>();
        for (RunRequestDto request : runListDto.getRuns()) {
            Run run = runService.getRunById(request.getId());
            run.setStatus(Run.RunStatus.RUNNING);
            String question = run.getContent();
            answerList.add(chatGPTService.askQuestion(question));
            run.setStatus(Run.RunStatus.END);
        }
        return ResponseEntity.status(HttpStatus.OK).body(answerList);
    }

}
