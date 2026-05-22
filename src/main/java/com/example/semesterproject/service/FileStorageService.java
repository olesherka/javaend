package com.example.semesterproject.service;

import com.example.semesterproject.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class    FileStorageService {

    private final Path uploadDir;

    public FileStorageService(@Value("${app.file.upload-dir:uploads}") String uploadDir) throws IOException {
        this.uploadDir = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(this.uploadDir);
    }

    public String store(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BadRequestException("File is empty");
        }
        String filename = UUID.randomUUID() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), uploadDir.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException e) {
            throw new BadRequestException("Failed to store file");
        }
    }

    public Resource load(String filename) {
        try {
            Path path = uploadDir.resolve(filename).normalize();
            Resource resource = new UrlResource(path.toUri());
            if (!resource.exists()) {
                throw new BadRequestException("File not found");
            }
            return resource;
        } catch (Exception e) {
            throw new BadRequestException("File not found");
        }
    }
}
