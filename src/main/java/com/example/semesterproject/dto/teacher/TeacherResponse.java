package com.example.semesterproject.dto.teacher;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TeacherResponse {
    private Long id;
    private String fullName;
    private String subject;
    private String email;
    private Long classroomId;
    private String classroomRoomNumber;
    private LocalDateTime createdAt;
}
