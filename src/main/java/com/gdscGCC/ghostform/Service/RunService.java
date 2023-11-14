package com.gdscGCC.ghostform.Service;

import com.gdscGCC.ghostform.Dto.Run.RunRequestDto;
import com.gdscGCC.ghostform.Repository.RunRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RunService {
    private final RunRepository runRepository;

    public void save(RunRequestDto runRequestDto) {
        runRepository.save(runRequestDto.toEntity());
    }

}
