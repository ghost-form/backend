package com.gdscGCC.ghostform.Dto.Run;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class RunListDto {
    List<RunRequestDto> runs;
}
