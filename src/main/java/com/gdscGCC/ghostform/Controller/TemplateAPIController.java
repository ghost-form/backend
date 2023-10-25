package com.gdscGCC.ghostform.Controller;

import com.gdscGCC.ghostform.Dto.TemplateRequestDto;
import com.gdscGCC.ghostform.Entity.Template;
import com.gdscGCC.ghostform.Service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TemplateAPIController {
    private final TemplateService templateService;

    @PostMapping("/templates/save")
    public Long templateSave(@RequestBody TemplateRequestDto requestDto){
        return templateService.save(requestDto);
    }

    @GetMapping("/templates/get")
    public void templateGet(@RequestParam Long id){
        templateService.findById(id);
    }
    @GetMapping("/templates/delete")
    public void templateDelete(@RequestParam Long id){
        templateService.delete(id);
    }

    @PutMapping("/api/v1/templates/{id}")
    public Long templateUpdate(@PathVariable Long id, @RequestBody TemplateRequestDto requestDto){
        return templateService.update(id, requestDto);
    }
}
