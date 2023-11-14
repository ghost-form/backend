package com.gdscGCC.ghostform.Dto.Ask;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AskRequestDto {
    private String key;
    private String value;

    @Builder
    public AskRequestDto(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
