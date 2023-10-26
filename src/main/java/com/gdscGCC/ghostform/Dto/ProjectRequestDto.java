package com.gdscGCC.ghostform.Dto;

import com.gdscGCC.ghostform.Entity.Project;
import com.gdscGCC.ghostform.Entity.Template;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@NoArgsConstructor
@Getter
public class ProjectRequestDto {
    private Long id;
    private String description;
    private String title;
    private List<Template> templates;
    private String lastModifiedDate;

    @Builder
    public ProjectRequestDto(Long id, String description, String title, String lastModifiedDate, List<Template> templates) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.templates = templates;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Project toEntity(){
        return Project.builder()
                .id(this.id)
                .description(this.description)
                .title(this.title)
                .templates(templates)
                .lastModifiedDate(this.lastModifiedDate)
                .build();
    }
}
