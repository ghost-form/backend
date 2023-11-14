package com.gdscGCC.ghostform.Dto.Ask;

import com.gdscGCC.ghostform.Entity.Ask;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CsvAskResponseDto {
    private List<List<Ask>> asks;

    public CsvAskResponseDto(List<List<Ask>> asks) {
        this.asks = asks;
    }
}
