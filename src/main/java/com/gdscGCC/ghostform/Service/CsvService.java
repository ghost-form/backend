package com.gdscGCC.ghostform.Service;

import com.gdscGCC.ghostform.Entity.Ask;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CsvService {

    /** CSV Reader, 미완성 */
    public List<Ask> readCsv(File file) throws Exception {
        try (FileReader reader = new FileReader(file)) {
            List<String[]> rows = new CSVReader(reader).readAll();

            List<Ask> asks = new ArrayList<>();
            String[] headers = rows.get(0);

            for (int i = 1; i < rows.size(); i++) {
                String[] data = rows.get(i);
                Ask ask = new Ask();

                Map<String, String> properties = new HashMap<>();
                for (int j = 0; j < headers.length; j++) {
                    properties.put(headers[j], data[j]);
                }

            }
            return asks;
        }
    }

}
