package com.gdscGCC.ghostform.Controller;

import com.gdscGCC.ghostform.Dto.ProjectRequestDto;
import com.gdscGCC.ghostform.Service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProjectAPIController {
    private final ProjectService projectService;

    /** 한 개의 프로젝트 조회 */
    @GetMapping("/projects/{id}")
    public void projectGet(@PathVariable Long id){
        projectService.findById(id);
    }

    /** 한 개의 프로젝트 생성 */
    @PostMapping("/projects/save")
    public Long projectSave(@RequestBody ProjectRequestDto requestDto){
        return projectService.save(requestDto);
    }

    /** 한 개의 프로젝트 삭제 */
    @DeleteMapping("/projects/delete/{id}")
    public void projectDelete(@PathVariable Long id){
        projectService.delete(id);
    }

    /** 한 개의 프로젝트 수정 */
    @PutMapping("/projects/{id}")
    public Long projectUpdate(@PathVariable Long id, @RequestBody ProjectRequestDto requestDto){
        System.out.println("id는!!! : " + id);
        return projectService.update(id, requestDto);
    }

}
