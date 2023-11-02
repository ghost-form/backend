package com.gdscGCC.ghostform.Dto.Project;

import com.gdscGCC.ghostform.Entity.Project;
import com.gdscGCC.ghostform.Entity.Template;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter

public class ProjectResponseDto {
    private Long project_id;
    private String title;
    private String description;
    private Template template;
    private Long user_id;
    private LocalDateTime lastModifiedDate;

    public ProjectResponseDto(Project project) {
        this.project_id = project.getProject_id();
        this.title = project.getTitle();
        this.description = project.getDescription();
        this.template = project.getTemplate();
        this.user_id = project.getUser_id();
        this.lastModifiedDate = project.setLastModifiedDate();
    }
}
