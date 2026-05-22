package com.example.semesterproject.mapper;

import com.example.semesterproject.dto.teacher.TeacherRequest;
import com.example.semesterproject.dto.teacher.TeacherResponse;
import com.example.semesterproject.entity.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {
    public Teacher toEntity(TeacherRequest request) {
        Teacher teacher = new Teacher();
        update(teacher, request);
        return teacher;
    }

    public void update(Teacher teacher, TeacherRequest request) {
        teacher.setFullName(request.getFullName());
        teacher.setSubject(request.getSubject());
        teacher.setEmail(request.getEmail());
    }

    public TeacherResponse toResponse(Teacher teacher) {
        TeacherResponse response = new TeacherResponse();
        response.setId(teacher.getId());
        response.setFullName(teacher.getFullName());
        response.setSubject(teacher.getSubject());
        response.setEmail(teacher.getEmail());
        response.setCreatedAt(teacher.getCreatedAt());
        if (teacher.getClassroom() != null) {
            response.setClassroomId(teacher.getClassroom().getId());
            response.setClassroomRoomNumber(teacher.getClassroom().getRoomNumber());
        }
        return response;
    }
}
