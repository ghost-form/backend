package com.gdscGCC.ghostform.Controller;

import com.gdscGCC.ghostform.Entity.Ask;
import com.gdscGCC.ghostform.Service.CsvService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/csv")
@Tag(name = "CSV", description = "CSV API")
public class CsvController {

    private final CsvService csvService;

    /** CSV Reader API, 미완성 */
    @RequestMapping("/readCsv.do")
    public List<Ask> readCsv(HttpServletRequest request) {
        try {
            ClassPathResource resource = new ClassPathResource("/resources/csv/asdf.csv");
            String path = resource.getPath();

            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
