package com.gdscGCC.ghostform.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
public class FileService {
    @Value(value = "${spring.servlet.multipart.location}")
    private String folder;

    public File save(MultipartFile file, HttpServletRequest request) {
        String path = request.getServletContext().getRealPath(folder);
        String filepath = path + "/" + generateFilename();
        try {
            File folderPath = new File(path);
            File savefile = new File(filepath);
            if (!folderPath.exists()) {
                folderPath.mkdirs();
            }
            if (!savefile.exists()) {
                savefile.createNewFile();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(savefile));
            bos.write(file.getBytes());
            bos.close();

            return savefile;
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
    }


    private String generateFilename() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Random random = new Random();
        String randomNumber = Integer.toString(random.nextInt(Integer.MAX_VALUE));
        String timeStamp = dateFormat.format(new Date());
        return timeStamp + randomNumber + ".csv";
    }
}
