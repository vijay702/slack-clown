package com.example.bankingManagementSystem.util;

import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Component
public class ImageUploaderUtill {


    private static final String BASE_URI = "localhost:9001";

    @Autowired
    HttpServletRequest httpServletRequest;

    public String imageUploader(MultipartFile iconFile, String path) {
        try {
            String fileName = System.currentTimeMillis() + iconFile.getOriginalFilename();
            File categoryIconFile =
                Paths.get(httpServletRequest.getContextPath(), "DOCUMENTS", path, fileName).toFile();
            Files.createParentDirs(categoryIconFile);
            Files.write(iconFile.getBytes(), categoryIconFile);
            return String.join("/", BASE_URI, "DOCUMENTS", path, fileName);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
