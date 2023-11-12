package com.gdscGCC.ghostform.Controller;

import com.gdscGCC.ghostform.Dto.ChatGPT.ChatGPTResponseDto;
import com.gdscGCC.ghostform.Dto.Ask.CsvAskResponseDto;
import com.gdscGCC.ghostform.Dto.Project.ProjectResponseDto;
import com.gdscGCC.ghostform.Entity.Ask;
import com.gdscGCC.ghostform.Service.ChatGPTService;
import com.gdscGCC.ghostform.Service.CsvService;
import com.gdscGCC.ghostform.Service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/csv")
public class CsvAPIController {
    private final CsvService csvService;
    private final ProjectService projectService;
    private final ChatGPTService chatGPTService;

    @PostMapping("{id}/readCsv.do")
    //public void readCsv(@RequestParam("file") MultipartFile file) {
    public ResponseEntity<List<List<Ask>>> readCsv(@PathVariable Long id) {
        try {
            ClassPathResource resource = new ClassPathResource("csv/asdf.csv");
            File testFile = resource.getFile();
            ProjectResponseDto project = projectService.findById(id);

            CsvAskResponseDto askList = csvService.read(new FileInputStream(testFile));
            List<ChatGPTResponseDto> output = new ArrayList<>();
            List<String> questions = new ArrayList<>();
            for (int i = 0; i < askList.getAsks().get(0).size(); i++) questions.add(project.getContent());

            int size = questions.size();
            for (List<Ask> asks : askList.getAsks()) {
               int index = 0;
               System.out.println(asks.size());
               for (Ask ask : asks) {
                   System.out.println(ask.getKey() + ", " + ask.getValue());
                   questions.set(index, questions.get(index).replace("{{" + ask.getKey() + "}}", ask.getValue()));
                   System.out.println(questions.get(index));
                   System.out.println("---------------------------");
               }
               System.out.println(questions.get(index));
            }

            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
