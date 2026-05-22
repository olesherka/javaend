package com.example.semesterproject.controller;

import com.example.semesterproject.dto.student.StudentRequest;
import com.example.semesterproject.dto.student.StudentResponse;
import com.example.semesterproject.service.ReportService;
import com.example.semesterproject.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/students")
@Tag(name = "Students")
public class StudentController {
    private final StudentService studentService;
    private final ReportService reportService;

    public StudentController(StudentService studentService, ReportService reportService) {
        this.studentService = studentService;
        this.reportService = reportService;
    }

    @GetMapping
    public Page<StudentResponse> search(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(required = false) Long classroomId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "firstName") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return studentService.search(name, classroomId, page, size, sortBy, direction);
    }

    @GetMapping("/{id}")
    public StudentResponse getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PostMapping
    public StudentResponse create(@Valid @RequestBody StudentRequest request) {
        return studentService.create(request);
    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id, @Valid @RequestBody StudentRequest request) {
        return studentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }

    @GetMapping("/report")
    public CompletableFuture<String> generateReport() {
        return reportService.generateStudentReportAsync();
    }
}
