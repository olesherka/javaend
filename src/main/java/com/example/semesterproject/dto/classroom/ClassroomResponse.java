package com.example.semesterproject.dto.classroom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassroomResponse {
    private Long id;
    private String roomNumber;
    private String building;
    private Integer capacity;
}
