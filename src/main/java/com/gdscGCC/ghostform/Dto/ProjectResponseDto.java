package com.gdscGCC.ghostform.Dto;

import lombok.Builder;

public class ProjectResponseDto {
    private Long id;
    private String description;
    private String title;
    private String lastModifiedDate;

    @Builder
    public ProjectResponseDto(Long id, String description, String title, String lastModifiedDate) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.lastModifiedDate = lastModifiedDate;
    }
}
