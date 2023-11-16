package com.gdscGCC.ghostform.Dto.Run;

import com.gdscGCC.ghostform.Entity.Project;
import com.gdscGCC.ghostform.Entity.Run;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RunRequestDto {
    private Long id;
    private Project project;
    private String content;
    private String data;
    private String variables;
    private Run.RunStatus status;

    public Run toEntity() {
        return Run.builder()
                .project(project)
                .content(content)
                .data(data)
                .variables(variables)
                .status(status)
                .build();
    }
}
