package com.example.homework3_4_introductiontosql.controller;

import com.example.homework3_4_introductiontosql.model.Avatar;
import com.example.homework3_4_introductiontosql.model.Faculty;
import com.example.homework3_4_introductiontosql.model.Student;
import com.example.homework3_4_introductiontosql.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

import org.springframework.http.HttpHeaders;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController( StudentService studentService) {
        this.studentService = studentService;

    }
//    @GetMapping("{id}")
//    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
//         Student student = studentService.findStudent(id);
//        if (student == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(student);
//    }
//
//    @PutMapping
//    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
//        Student foundStudent = studentService.update(student);
//        if (foundStudent == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//        return ResponseEntity.ok(foundStudent);
//    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable long id) {
        return studentService.get(id);
    }
    // localhost:8080/student/2
    @DeleteMapping("/{id}")
    public Student remove(@PathVariable long id) {
        return studentService.remove(id);
    }

    // localhost:8080/student/byAge?age=20
    @GetMapping("/byAge")
    public Collection<Student> byAge(@RequestParam int age) {
        return studentService.filterByAge(age);
    }
    @GetMapping("/byAll")
    public Collection<Student> byAll () {
         return studentService.returnAll();
    }

    @GetMapping("/byAgeBetween")
    public Collection<Student> byAgeBetween(@RequestParam int min, @RequestParam int max) {
        return studentService.filterByAgeBetween(min, max);
    }
    @GetMapping("/byFaculty/{id}")
    public Faculty byFaculty (@PathVariable long id) {
        return studentService.returnByFaculty(id);
    }
}
