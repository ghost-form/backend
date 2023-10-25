package com.gdscGCC.ghostform.Dto;

import com.gdscGCC.ghostform.Entity.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
public class ProjectRequestDto {
    private Long id;
    private String description;
    private String title;
    private String lastModifiedDate;

    @Builder
    public ProjectRequestDto(Long id, String description, String title, String lastModifiedDate) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Project toEntity(){
        return Project.builder()
                .id(this.id)
                .description(this.description)
                .title(this.title)
                .lastModifiedDate(this.lastModifiedDate)
                .build();
    }
}
