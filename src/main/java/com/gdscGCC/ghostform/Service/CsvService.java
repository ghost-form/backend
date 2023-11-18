package com.gdscGCC.ghostform.Service;

import com.gdscGCC.ghostform.Dto.Ask.CsvAskResponseDto;
import com.gdscGCC.ghostform.Dto.Project.ProjectResponseDto;
import com.gdscGCC.ghostform.Entity.Ask;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {
    public CsvAskResponseDto read(FileInputStream inputStream) {
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));

            String[] header = reader.peek();
            header[0] = header[0].replace("\uFEFF", "");
            reader.skip(1);
            List<List<Ask>> output = new ArrayList<>(header.length);
            for (int i = 0; i < header.length; i++) output.add(new ArrayList<Ask>());

            String[] line;
            while ((line = reader.readNext()) != null) {
                int i = 0;
                line[0] = line[0].replace("\uFEFF", "");
                for (String words : line) {
                    output.get(i).add(new Ask(header[i], words));
                    i++;
                }
            }

            reader.close();
            return new CsvAskResponseDto(output);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public List<String> makeQuestions(FileInputStream inputStream, ProjectResponseDto project) {
        CsvAskResponseDto csvAskResponseDto = this.read(inputStream);
        List<List<Ask>> askList = csvAskResponseDto.getAsks();
        List<String> questions = new ArrayList<>();
        for (int i = 0; i < csvAskResponseDto.getAsks().get(0).size(); i++) questions.add(project.getContent());

        for (List<Ask> asks : askList) {
            int i = 0;
            for (Ask ask : asks) {
                String str = questions.get(i);
                str = str.replace("{{" + ask.getKey() + "}}", ask.getValue());
                questions.set(i, str);
                i++;
            }
        }

        return questions;
    }

}
