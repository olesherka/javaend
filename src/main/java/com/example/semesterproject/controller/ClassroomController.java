package com.example.semesterproject.controller;

import com.example.semesterproject.dto.classroom.ClassroomRequest;
import com.example.semesterproject.dto.classroom.ClassroomResponse;
import com.example.semesterproject.service.ClassroomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
@Tag(name = "Classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public List<ClassroomResponse> getAll() {
        return classroomService.getAll();
    }

    @GetMapping("/{id}")
    public ClassroomResponse getById(@PathVariable Long id) {
        return classroomService.getById(id);
    }

    @PostMapping
    public ClassroomResponse create(@Valid @RequestBody ClassroomRequest request) {
        return classroomService.create(request);
    }

    @PutMapping("/{id}")
    public ClassroomResponse update(@PathVariable Long id, @Valid @RequestBody ClassroomRequest request) {
        return classroomService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        classroomService.delete(id);
    }
}
