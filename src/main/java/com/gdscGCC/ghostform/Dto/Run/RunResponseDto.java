package com.gdscGCC.ghostform.Dto.Run;

import com.gdscGCC.ghostform.Entity.Run;
import com.gdscGCC.ghostform.Entity.RunStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RunResponseDto {
    private Long id;
    private String content;
    private String data;
    private String variables;
    private RunStatus status;

    public RunResponseDto(Run run) {
        this.id = run.getId();
        this.content = run.getContent();
        this.data = run.getData();
        this.variables = run.getVariables();
        this.status = run.getStatus();
    }
}
