package com.gdscGCC.ghostform.Controller;

import com.gdscGCC.ghostform.Dto.TemplateRequestDto;
import com.gdscGCC.ghostform.Service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class TemplateAPIController {
    private final TemplateService templateService;

    @GetMapping("/templates/{id}")
    public void templateGet(@PathVariable Long id){
        templateService.findById(id);
    }

    @PostMapping("/templates/save")
    public Long templateSave(@RequestBody TemplateRequestDto requestDto){
        return templateService.save(requestDto);
    }


    @DeleteMapping("/templates/delete/{id}")
    public void templateDelete(@PathVariable Long id){
        templateService.delete(id);
    }

    @PutMapping("/templates/{id}")
    public Long templateUpdate(@PathVariable Long id, @RequestBody TemplateRequestDto requestDto){
        System.out.println("id는!!! : " + id);
        return templateService.update(id, requestDto);
    }
}
