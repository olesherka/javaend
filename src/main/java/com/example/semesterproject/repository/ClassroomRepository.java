package com.example.semesterproject.repository;

import com.example.semesterproject.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    boolean existsByRoomNumber(String roomNumber);
}
