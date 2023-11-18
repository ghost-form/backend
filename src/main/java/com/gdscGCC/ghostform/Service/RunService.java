package com.gdscGCC.ghostform.Service;

import com.gdscGCC.ghostform.Entity.Run;
import com.gdscGCC.ghostform.Repository.RunRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RunService {
    private final RunRepository runRepository;

    public Run getRunById(Long id) {
        return runRepository.getRunById(id).orElseThrow(() -> new RuntimeException("Not Found."));
    }

    @Transactional
    public void save(Run run) {
        runRepository.save(run);
    }

    @Transactional
    public void setState(Run run, Run.RunStatus status) {
        run.setStatus(status);
    }

}
