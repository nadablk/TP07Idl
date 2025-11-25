package com.example.demo.Controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.demo.model.Student;
import com.example.demo.demo.repository.StudentRepository;


// AssertJ for fluent assertions
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

// Indicates this is a Spring Boot test that loads the full application context
@SpringBootTest

// Use H2 test profile
@ActiveProfiles("test")

// Specifies the order in which test methods will be executed
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class ControllerTest {

    // Injects the StudentRepository bean from the Spring context
    @Autowired
    private StudentRepository studentRepository;

    // First test: verifies that a student can be saved to the repository
    @Test

    @Order(1) // Ensures this test runs first

    void shouldSaveStudent() {
        // Create a new student object
        Student student = new Student();
        student.setName("Charlie");
        student.setAddress("Algeria");

        // Save the student to the H2 in-memory database
        studentRepository.save(student);

        // Assert that the repository now contains exactly one record
        assertThat(studentRepository.count()).isEqualTo(1);
    }

    // Second test: verifies that all students can be retrieved correctly
    @Test
    @Order(2) // Ensures this test runs after shouldSaveStudent()

    void shouldFindAllStudents() {
        // Fetch all students from the repository
        List<Student> students = studentRepository.findAll();

        // Assert that there is at least one student in the list
        assertThat(students).isNotEmpty();

        // Assert that the first student's name is "Charlie"
        assertThat(students.get(0).getName()).isEqualTo("Charlie");
    }
}