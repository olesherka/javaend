package com.example.semesterproject.repository;

import com.example.semesterproject.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);

    Page<Student> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndClassroom_Id(
            String firstName, String lastName, Long classroomId, Pageable pageable);

    Page<Student> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
            String firstName, String lastName, Pageable pageable);
}
