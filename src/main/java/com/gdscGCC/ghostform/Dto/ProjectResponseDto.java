package com.gdscGCC.ghostform.Dto;

import com.gdscGCC.ghostform.Entity.Project;
import com.gdscGCC.ghostform.Entity.Template;
import lombok.Getter;

import java.util.List;

@Getter

public class ProjectResponseDto {
    private Long id;
    private String description;
    private String title;
    private List<Template> templates;
    private String lastModifiedDate;

    public ProjectResponseDto(Project project) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.templates = templates;
        this.lastModifiedDate = lastModifiedDate;
    }
}
