package com.gdscGCC.ghostform.Service;

import com.gdscGCC.ghostform.Dto.Ask.CsvAskResponseDto;
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
            reader.skip(1);
            List<List<Ask>> output = new ArrayList<>(header.length);
            for (int i = 0; i < header.length; i++) output.add(new ArrayList<Ask>());

            String[] line;
            while ((line = reader.readNext()) != null) {
                int i = 0;
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

}
