package com.gdscGCC.ghostform.Controller;

import com.gdscGCC.ghostform.Dto.Template.TemplateRequestDto;
import com.gdscGCC.ghostform.Service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/templates")
public class TemplateAPIController {
    private final TemplateService templateService;

    @GetMapping("/{id}")
    public void templateGet(@PathVariable Long id){
        templateService.findById(id);
    }

    @PostMapping("")
    public Long templateSave(@RequestBody TemplateRequestDto requestDto){
        return templateService.save(requestDto);
    }


    @DeleteMapping("/{id}")
    public void templateDelete(@PathVariable Long id){
        templateService.delete(id);
    }

    @PutMapping("/{id}")
    public Long templateUpdate(@PathVariable Long id, @RequestBody TemplateRequestDto requestDto){
        System.out.println("id는!!! : " + id);
        return templateService.update(id, requestDto);
    }
}
