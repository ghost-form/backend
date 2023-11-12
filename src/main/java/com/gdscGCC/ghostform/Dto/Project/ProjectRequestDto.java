package com.gdscGCC.ghostform.Dto.Project;

import com.gdscGCC.ghostform.Entity.Project;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.HashMap;

@Setter
@NoArgsConstructor
@Getter
public class ProjectRequestDto {
    private Long project_id;
    private String title;
    private String description;
    private String content;
    private String variables;
    private Long user_id;
    private LocalDateTime lastModifiedDate;
    private Long star;

    public LocalDateTime setLastModifiedDate() {
        return LocalDateTime.now();
    }
    @Builder
    public ProjectRequestDto(Long project_id, String title, String description, LocalDateTime lastModifiedDate, String content, String variables, Long user_id) {
        this.project_id = project_id;
        this.title = title;
        this.description = description;
        this.lastModifiedDate = setLastModifiedDate();
        this.content = content;
        this.variables = variables;
        this.user_id = user_id;
        this.star = 0L;
    }

    public ProjectRequestDto(ProjectResponseDto project) {
        this.project_id = project.getProject_id();
        this.title = project.getTitle();
        this.description = project.getDescription();
        this.lastModifiedDate = project.getLastModifiedDate();
        this.content = project.getContent();
        this.variables = project.getVariables();
        this.user_id = project.getUser_id();
        this.star = project.getStar();
    }

    public Project toEntity(){
        return Project.builder()
                .title(this.title)
                .description(this.description)
                .lastModifiedDate(this.lastModifiedDate)
                .content(this.content)
                .variables(this.variables)
                .user_id(user_id)
                .star(star)
                .build();
    }
}
