package com.gdscGCC.ghostform.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gdscGCC.ghostform.Dto.Ask.AskRequestDto;
import com.gdscGCC.ghostform.Dto.ChatGPT.ChatGPTResponseDto;
import com.gdscGCC.ghostform.Dto.Project.ProjectRequestDto;
import com.gdscGCC.ghostform.Dto.Project.ProjectResponseDto;
import com.gdscGCC.ghostform.Dto.Variable.VariableRequestDto;
import com.gdscGCC.ghostform.Entity.Ask;
import com.gdscGCC.ghostform.Entity.Project;
import com.gdscGCC.ghostform.Service.ChatGPTService;
import com.gdscGCC.ghostform.Service.ProjectService;
import com.gdscGCC.ghostform.Service.VariableService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects")
@Tag(name = "Project", description = "Project API")
public class ProjectAPIController {
    private final ProjectService projectService;
    private final ChatGPTService chatGPTService;
    private final VariableService variableService;



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

    @PostMapping(value = "/{project_id}/ask", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> makeQuestionStream (@PathVariable Long project_id, @RequestBody AskRequestDto askRequestDto) {
        ProjectResponseDto project = projectService.findById(project_id);
        String question = project.getContent();
        for (Ask a : askRequestDto.getAskList()) {
            question = question.replace("{{" + a.getKey() + "}}", a.getValue());
        }
        try {
            return chatGPTService.getChatGptStreamResponse(question);
        } catch (JsonProcessingException e) {
            log.warn(e.toString());
            return Flux.empty();
        }
    }

    @PostMapping(value = "/{project_id}/ask/json")
    public ResponseEntity<ChatGPTResponseDto> makeQuestion (@PathVariable Long project_id, @RequestBody AskRequestDto askRequestDto) {
        ProjectResponseDto project = projectService.findById(project_id);
        String question = project.getContent();
        for (Ask a : askRequestDto.getAskList()) {
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
}
