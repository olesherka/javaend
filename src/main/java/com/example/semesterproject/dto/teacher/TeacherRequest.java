package com.example.semesterproject.dto.teacher;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequest {
    @NotBlank
    private String fullName;
    @NotBlank
    private String subject;
    @Email
    @NotBlank
    private String email;
    private Long classroomId;
}
