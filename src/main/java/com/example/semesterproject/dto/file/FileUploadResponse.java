package com.example.semesterproject.dto.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileUploadResponse {
    private String filename;
    private String downloadUrl;
}
