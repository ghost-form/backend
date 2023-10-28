package com.gdscGCC.ghostform.Dto.Template;

import com.gdscGCC.ghostform.Entity.Template;
import com.gdscGCC.ghostform.Entity.Project;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TemplateRequestDto {
    private Long template_id;
    private String name;
    private String content;
    private Project project;

    @Builder
    public TemplateRequestDto(Long template_id, String name, String content, Project project){
        this.template_id = template_id;
        this.name = name;
        this.content = content;
        this.project = project;
    }

    // request Dto로 받은 Template 객체를 entity화 해서 저장하는 용도
    public Template toEntity(){
        return Template.builder()
                .template_id(template_id)
                .name(name)
                .content(content)
                .project(project)
                .build();
    }
}
