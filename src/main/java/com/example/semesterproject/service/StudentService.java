package com.example.semesterproject.service;

import com.example.semesterproject.dto.student.StudentRequest;
import com.example.semesterproject.dto.student.StudentResponse;
import com.example.semesterproject.entity.Classroom;
import com.example.semesterproject.entity.Student;
import com.example.semesterproject.exception.BadRequestException;
import com.example.semesterproject.exception.NotFoundException;
import com.example.semesterproject.mapper.StudentMapper;
import com.example.semesterproject.repository.StudentRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final ClassroomService classroomService;
    private final AuditService auditService;

    public StudentService(StudentRepository studentRepository,
                          StudentMapper studentMapper,
                          ClassroomService classroomService,
                          AuditService auditService) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.classroomService = classroomService;
        this.auditService = auditService;
    }

    public Page<StudentResponse> search(String name,
                                        Long classroomId,
                                        int page,
                                        int size,
                                        String sortBy,
                                        String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        String value = name == null ? "" : name;
        Page<Student> result;
        if (classroomId != null) {
            result = studentRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndClassroom_Id(
                    value, value, classroomId, pageable);
        } else {
            result = studentRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(value, value, pageable);
        }
        return result.map(studentMapper::toResponse);
    }

    public StudentResponse getById(Long id) {
        return studentMapper.toResponse(findEntity(id));
    }

    public StudentResponse create(StudentRequest request) {
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Student email already exists");
        }
        Student student = studentMapper.toEntity(request);
        if (request.getClassroomId() != null) {
            Classroom classroom = classroomService.findEntity(request.getClassroomId());
            student.setClassroom(classroom);
        }
        Student saved = studentRepository.save(student);
        auditService.logAction("CREATE_STUDENT", "system", "Student id=" + saved.getId());
        return studentMapper.toResponse(saved);
    }

    public StudentResponse update(Long id, StudentRequest request) {
        Student student = findEntity(id);
        studentMapper.update(student, request);
        if (request.getClassroomId() != null) {
            student.setClassroom(classroomService.findEntity(request.getClassroomId()));
        } else {
            student.setClassroom(null);
        }
        Student saved = studentRepository.save(student);
        auditService.logAction("UPDATE_STUDENT", "system", "Student id=" + saved.getId());
        return studentMapper.toResponse(saved);
    }

    public void delete(Long id) {
        studentRepository.delete(findEntity(id));
        auditService.logAction("DELETE_STUDENT", "system", "Student id=" + id);
    }

    public Student findEntity(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found: " + id));
    }
}
