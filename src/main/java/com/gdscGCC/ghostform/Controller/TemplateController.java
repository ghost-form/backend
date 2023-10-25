package com.gdscGCC.ghostform.Controller;

import com.gdscGCC.ghostform.Entity.Project;
import com.gdscGCC.ghostform.Entity.Template;
import com.gdscGCC.ghostform.Repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TemplateController {
    private final TemplateRepository templateRepository;

    @PostMapping("/templates/save")
    public void templateSave(@RequestBody Template template){
        templateRepository.save(template);
    }

    @GetMapping("/templates/get")
    public void templateGet(@RequestParam Long id){
        Template template = templateRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 템플릿이 없습니다. id="+ id));

        System.out.println("template id : " + template.getId());
        System.out.println("template name : " + template.getName());
        System.out.println("template content : " + template.getContent());

    }
    @GetMapping("/templates/delete")
    public void templateDelete(@RequestParam Long id){
        Template template = templateRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 템플릿이 없습니다. id="+ id));
        System.out.println("template id : " + template.getId() + "template was deleted.");
        templateRepository.deleteById(id);
    }

    @PostMapping("/templates/update")
    public void templateUpdate(@RequestBody Template template){
        Long id = template.getId();
        Optional<Template> templateWrapper = templateRepository.findById(id);
        Template toWriteTemplate = templateWrapper.get();
        templateRepository.save(template);
        asefasejf
    }
}
