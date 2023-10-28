package com.gdscGCC.ghostform.Dto.Project;

import com.gdscGCC.ghostform.Entity.Project;
import com.gdscGCC.ghostform.Entity.Template;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@NoArgsConstructor
@Getter
public class ProjectRequestDto {
    private Long project_id;
    private String title;
    private String description;
    private List<Template> templates;
    private HashMap<String, Object> variables;
    private Long user_id;
    private LocalDateTime lastModifiedDate;



    public LocalDateTime setLastModifiedDate() {
        return this.lastModifiedDate = LocalDateTime.now();
    }
    @Builder
    public ProjectRequestDto(Long project_id, String title, String description, List<Template> templates, HashMap<String, Object> variables, Long user_id) {
        this.project_id = project_id;
        this.title = title;
        this.description = description;
        this.templates = templates;
        this.variables = variables;
        this.user_id = user_id;
        this.lastModifiedDate = setLastModifiedDate();
    }

    public Project toEntity(){
        return Project.builder()
                .project_id(this.project_id)
                .title(this.title)
                .description(this.description)
                .templates(this.templates)
                .variables(this.variables)
                .user_id(this.user_id)
                .build();
    }
}
