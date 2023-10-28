package com.gdscGCC.ghostform.Controller;

import com.gdscGCC.ghostform.Dto.Project.ProjectRequestDto;
import com.gdscGCC.ghostform.Dto.Project.ProjectResponseDto;
import com.gdscGCC.ghostform.Dto.Variable.VariableRequestDto;
import com.gdscGCC.ghostform.Service.ProjectService;
import com.gdscGCC.ghostform.Service.VariableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects")
public class ProjectAPIController {
    private final ProjectService projectService;
    private final VariableService variableService;


    /** 한 개의 프로젝트 조회 */
    @GetMapping("/{project_id}")
    public ResponseEntity<ProjectResponseDto> projectGet(@PathVariable Long project_id){
        return ResponseEntity.status(HttpStatus.OK).body(projectService.findById(project_id));
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

    /** Project의 모든 변수 조회
     * RequestParameter로 project_id를 받아옴*/
    @GetMapping("/{project_id}/variables")
    public ResponseEntity<HashMap<String, Object>> getAllVariables(@PathVariable Long project_id){
        return ResponseEntity.status(HttpStatus.OK).body(variableService.getAllVariables(project_id));
    }

    /** Project의 특정 변수 하나 조회
     * RequestParameter로 project_id 및 variable_key를 받아옴*/
    @GetMapping("/{project_id}/variable")
    public ResponseEntity<Object> getOneVariables(@PathVariable Long project_id, @RequestParam String key){
        return ResponseEntity.status(HttpStatus.OK).body(variableService.getOneVariable(project_id, key));
    }

    /** 한 개의 변수 생성
     * RequestParameter로 project_id를 받아옴
     * RequestBody로 생성할 JSON을 받아옴 */
    @PostMapping("/{project_id}/variables")
    public HashMap<String, Object> createOneVariable(@PathVariable Long project_id, @RequestBody HashMap<String, Object> map){
        return variableService.create(project_id, map);
    }

    /** 한 개의 변수 삭제
     * RequestParameter로 project_id를 받아옴
     * RequestParameter로 삭제할 변수 key를 받아옴 */
    @DeleteMapping("/{project_id}/variables")
    public void deleteOneVariable(@PathVariable Long project_id, @RequestParam String deleteKey){
        variableService.deleteOne(project_id, deleteKey);
    }

    /** 한 개의 변수 수정
     * RequestParameter로 project_id를 받아옴
     * RequestBody로 수정할 변수 key와 value를 받아옴*/
    @PutMapping("/{project_id}/variables")
    public void updateOneVariable(@PathVariable Long project_id, @RequestBody VariableRequestDto variableRequestDto){
        variableService.updateOneVariable(project_id, variableRequestDto);
    }
}
