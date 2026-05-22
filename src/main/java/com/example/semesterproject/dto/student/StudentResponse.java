package com.example.semesterproject.dto.student;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private Long classroomId;
    private String classroomRoomNumber;
    private LocalDateTime createdAt;
}
