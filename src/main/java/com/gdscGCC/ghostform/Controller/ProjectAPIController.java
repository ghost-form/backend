package com.gdscGCC.ghostform.Controller;

import com.gdscGCC.ghostform.Dto.Project.ProjectRequestDto;
import com.gdscGCC.ghostform.Dto.Project.ProjectResponseDto;
import com.gdscGCC.ghostform.Dto.Variable.VariableRequestDto;
import com.gdscGCC.ghostform.Entity.Project;
import com.gdscGCC.ghostform.Service.ProjectService;
import com.gdscGCC.ghostform.Service.VariableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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
}
