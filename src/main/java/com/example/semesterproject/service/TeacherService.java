package com.example.semesterproject.service;

import com.example.semesterproject.dto.teacher.TeacherRequest;
import com.example.semesterproject.dto.teacher.TeacherResponse;
import com.example.semesterproject.entity.Teacher;
import com.example.semesterproject.exception.BadRequestException;
import com.example.semesterproject.exception.NotFoundException;
import com.example.semesterproject.mapper.TeacherMapper;
import com.example.semesterproject.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final ClassroomService classroomService;
    private final AuditService auditService;

    public TeacherService(TeacherRepository teacherRepository,
                          TeacherMapper teacherMapper,
                          ClassroomService classroomService,
                          AuditService auditService) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
        this.classroomService = classroomService;
        this.auditService = auditService;
    }

    public List<TeacherResponse> getAll() {
        return teacherRepository.findAll().stream().map(teacherMapper::toResponse).toList();
    }

    public TeacherResponse getById(Long id) {
        return teacherMapper.toResponse(findEntity(id));
    }

    public TeacherResponse create(TeacherRequest request) {
        if (teacherRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Teacher email already exists");
        }
        Teacher teacher = teacherMapper.toEntity(request);
        if (request.getClassroomId() != null) {
            teacher.setClassroom(classroomService.findEntity(request.getClassroomId()));
        }
        Teacher saved = teacherRepository.save(teacher);
        auditService.logAction("CREATE_TEACHER", "system", "Teacher id=" + saved.getId());
        return teacherMapper.toResponse(saved);
    }

    public TeacherResponse update(Long id, TeacherRequest request) {
        Teacher teacher = findEntity(id);
        teacherMapper.update(teacher, request);
        if (request.getClassroomId() != null) {
            teacher.setClassroom(classroomService.findEntity(request.getClassroomId()));
        } else {
            teacher.setClassroom(null);
        }
        Teacher saved = teacherRepository.save(teacher);
        auditService.logAction("UPDATE_TEACHER", "system", "Teacher id=" + saved.getId());
        return teacherMapper.toResponse(saved);
    }

    public void delete(Long id) {
        teacherRepository.delete(findEntity(id));
        auditService.logAction("DELETE_TEACHER", "system", "Teacher id=" + id);
    }

    private Teacher findEntity(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher not found: " + id));
    }
}
