package com.gdscGCC.ghostform.Controller;

import com.gdscGCC.ghostform.Dto.ProjectRequestDto;
import com.gdscGCC.ghostform.Service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProjectAPIController {
    private final ProjectService projectService;

    @PostMapping("/projects/save")
    public Long projectSave(@RequestBody ProjectRequestDto requestDto){
        return projectService.save(requestDto);
    }

    @GetMapping("/projects/get")
    public void projectGet(@RequestParam Long id){
        projectService.findById(id);
    }
    @GetMapping("/projects/delete")
    public void projectDelete(@RequestParam Long id){
        projectService.delete(id);
    }

    @PutMapping("/api/v1/projects/{id}")
    public Long projectUpdate(@PathVariable Long id, @RequestBody ProjectRequestDto requestDto){
        System.out.println("id는!!! : " + id);
        return projectService.update(id, requestDto);
    }
}
