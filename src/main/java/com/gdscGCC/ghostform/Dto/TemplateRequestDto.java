package com.gdscGCC.ghostform.Dto;

import com.gdscGCC.ghostform.Entity.Template;
import com.gdscGCC.ghostform.Entity.Project;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TemplateRequestDto {
    private Long id;
    private String name;
    private String content;
    private Project project;

    @Builder
    public TemplateRequestDto(Long id, String name, String content, Project project){
        this.id = id;
        this.name = name;
        this.content = content;
        this.project = project;
    }

    // request Dto로 받은 Template 객체를 entity화 해서 저장하는 용도
    public Template toEntity(){
        return Template.builder()
                .id(id)
                .name(name)
                .content(content)
                .project(project)
                .build();
    }
}
