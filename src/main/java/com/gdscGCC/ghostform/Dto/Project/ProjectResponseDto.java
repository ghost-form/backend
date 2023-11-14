package com.gdscGCC.ghostform.Dto.Project;

import com.gdscGCC.ghostform.Entity.Project;
import com.gdscGCC.ghostform.Entity.Run;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ProjectResponseDto {
    private Long project_id;
    private String title;
    private String description;
    private String content;
    private String variables;
    private Long user_id;
    private LocalDateTime lastModifiedDate;
    private Long star;
    private Project.Visibility visibility;
//    private Run run_id;
    private List<Run> runs;

    public ProjectResponseDto(Project project) {
        this.project_id = project.getProject_id();
        this.title = project.getTitle();
        this.description = project.getDescription();
        this.lastModifiedDate = project.getLastModifiedDate();
        this.content = project.getContent();
        this.variables = project.getVariables();
        this.user_id = project.getUser_id();
        this.star = project.getStar();
        this.runs = project.getRun();
        this.visibility = project.getVisibility();
//        this.run_id = project.getRun_id();
    }

}
