package com.example.semesterproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ReportService {
    private static final Logger log = LoggerFactory.getLogger(ReportService.class);

    @Async("taskExecutor")
    public CompletableFuture<String> generateStudentReportAsync() {
        log.info("Report generation started");
        String content = "Student report was generated asynchronously";
        return CompletableFuture.completedFuture(content);
    }
}
