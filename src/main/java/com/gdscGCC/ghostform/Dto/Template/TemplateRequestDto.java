package com.gdscGCC.ghostform.Dto.Template;

import com.gdscGCC.ghostform.Entity.Template;
import com.gdscGCC.ghostform.Entity.Project;
import com.gdscGCC.ghostform.Repository.ProjectRepository;
import com.gdscGCC.ghostform.Service.ProjectService;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class TemplateRequestDto {
    private Long template_id;
    private String name;
    private String content;
    private Project project;
    private HashMap<String, Object> variables;

    @Builder
    public TemplateRequestDto(Long template_id, String name, String content, Project project, HashMap<String, Object> variables){
        this.template_id = template_id;
        this.name = name;
        this.content = content;
        this.project = project;
        this.variables = variables;
    }

    // request Dto로 받은 Template 객체를 entity화 해서 저장하는 용도
    public Template toEntity(){

        return Template.builder()
                .template_id(template_id)
                .name(name)
                .content(content)
                .project(project)
                .variables(variables)
                .build();
    }
}
