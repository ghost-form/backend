package com.gdscGCC.ghostform.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Run {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="project_id", updatable = false)
    private Project project;

    private String content;

    private String data;

    private String variables;

    private RunStatus status;

    @Builder
    public Run(Project project, String content, String data, String variables, RunStatus status) {
        this.project = project;
        this.content = content;
        this.data = data;
        this.variables = variables;
        this.status = status;
    }

    public enum RunStatus {
        START,
        RUNNING,
        END
    }
}
