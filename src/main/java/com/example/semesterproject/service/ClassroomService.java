package com.example.semesterproject.service;

import com.example.semesterproject.dto.classroom.ClassroomRequest;
import com.example.semesterproject.dto.classroom.ClassroomResponse;
import com.example.semesterproject.entity.Classroom;
import com.example.semesterproject.exception.BadRequestException;
import com.example.semesterproject.exception.NotFoundException;
import com.example.semesterproject.mapper.ClassroomMapper;
import com.example.semesterproject.repository.ClassroomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {
    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;
    private final AuditService auditService;

    public ClassroomService(ClassroomRepository classroomRepository, ClassroomMapper classroomMapper, AuditService auditService) {
        this.classroomRepository = classroomRepository;
        this.classroomMapper = classroomMapper;
        this.auditService = auditService;
    }

    public List<ClassroomResponse> getAll() {
        return classroomRepository.findAll().stream().map(classroomMapper::toResponse).toList();
    }

    public ClassroomResponse getById(Long id) {
        return classroomMapper.toResponse(findEntity(id));
    }

    public ClassroomResponse create(ClassroomRequest request) {
        if (classroomRepository.existsByRoomNumber(request.getRoomNumber())) {
            throw new BadRequestException("Room number already exists");
        }
        Classroom classroom = classroomMapper.toEntity(request);
        Classroom saved = classroomRepository.save(classroom);
        auditService.logAction("CREATE_CLASSROOM", "system", "Classroom id=" + saved.getId());
        return classroomMapper.toResponse(saved);
    }

    public ClassroomResponse update(Long id, ClassroomRequest request) {
        Classroom classroom = findEntity(id);
        classroomMapper.update(classroom, request);
        Classroom saved = classroomRepository.save(classroom);
        auditService.logAction("UPDATE_CLASSROOM", "system", "Classroom id=" + saved.getId());
        return classroomMapper.toResponse(saved);
    }

    public void delete(Long id) {
        Classroom classroom = findEntity(id);
        classroomRepository.delete(classroom);
        auditService.logAction("DELETE_CLASSROOM", "system", "Classroom id=" + id);
    }

    public Classroom findEntity(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Classroom not found: " + id));
    }
}
