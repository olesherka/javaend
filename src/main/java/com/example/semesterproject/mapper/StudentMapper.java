package com.example.semesterproject.mapper;

import com.example.semesterproject.dto.student.StudentRequest;
import com.example.semesterproject.dto.student.StudentResponse;
import com.example.semesterproject.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public Student toEntity(StudentRequest request) {
        Student student = new Student();
        update(student, request);
        return student;
    }

    public void update(Student student, StudentRequest request) {
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setAge(request.getAge());
    }

    public StudentResponse toResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setEmail(student.getEmail());
        response.setAge(student.getAge());
        response.setCreatedAt(student.getCreatedAt());
        if (student.getClassroom() != null) {
            response.setClassroomId(student.getClassroom().getId());
            response.setClassroomRoomNumber(student.getClassroom().getRoomNumber());
        }
        return response;
    }
}
