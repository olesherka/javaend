package com.example.semesterproject.mapper;

import com.example.semesterproject.dto.classroom.ClassroomRequest;
import com.example.semesterproject.dto.classroom.ClassroomResponse;
import com.example.semesterproject.entity.Classroom;
import org.springframework.stereotype.Component;

@Component
public class ClassroomMapper {
    public Classroom toEntity(ClassroomRequest request) {
        Classroom classroom = new Classroom();
        classroom.setRoomNumber(request.getRoomNumber());
        classroom.setBuilding(request.getBuilding());
        classroom.setCapacity(request.getCapacity());
        return classroom;
    }

    public void update(Classroom classroom, ClassroomRequest request) {
        classroom.setRoomNumber(request.getRoomNumber());
        classroom.setBuilding(request.getBuilding());
        classroom.setCapacity(request.getCapacity());
    }

    public ClassroomResponse toResponse(Classroom classroom) {
        ClassroomResponse response = new ClassroomResponse();
        response.setId(classroom.getId());
        response.setRoomNumber(classroom.getRoomNumber());
        response.setBuilding(classroom.getBuilding());
        response.setCapacity(classroom.getCapacity());
        return response;
    }
}
