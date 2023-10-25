package com.gdscGCC.ghostform.Dto;

import com.gdscGCC.ghostform.Entity.Project;
import lombok.Getter;

@Getter

public class ProjectResponseDto {
    private Long id;
    private String description;
    private String title;
    private String lastModifiedDate;

    public ProjectResponseDto(Project project) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.lastModifiedDate = lastModifiedDate;
    }
}
