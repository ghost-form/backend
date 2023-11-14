package com.gdscGCC.ghostform.Dto.Run;

import com.gdscGCC.ghostform.Entity.Project;
import com.gdscGCC.ghostform.Entity.Run;
import com.gdscGCC.ghostform.Entity.RunStatus;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RunRequestDto {
    private Project project;
    private String content;
    private String data;
    private String variables;
    private RunStatus status;

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
