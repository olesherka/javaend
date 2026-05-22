package com.example.semesterproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classrooms")
@Getter
@Setter
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String roomNumber;

    @Column(nullable = false, length = 100)
    private String building;

    @Column(nullable = false)
    private Integer capacity;

    @OneToMany(mappedBy = "classroom")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "classroom")
    private List<Teacher> teachers = new ArrayList<>();
}
