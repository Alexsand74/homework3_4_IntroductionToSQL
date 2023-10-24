package com.example.homework3_4_introductiontosql.service;

import com.example.homework3_4_introductiontosql.exception.StudentNotFoundException;
import com.example.homework3_4_introductiontosql.model.Avatar;
import com.example.homework3_4_introductiontosql.model.Faculty;
import com.example.homework3_4_introductiontosql.model.Student;
//import com.example.homework3_4_introductiontosql.repository.AvatarRepository;
import com.example.homework3_4_introductiontosql.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
     public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
         }
    public Student findStudent(long id) {
        return studentRepository.findById(id).orElseThrow();
    }




    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    public Student add(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }

    public Student get(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student remove(long id) {
        var studentTemporary = studentRepository.findById(id).orElse(null);
        if (studentTemporary != null) {
            studentRepository.delete(studentTemporary);
        }
        return studentTemporary;
    }

    public Student update(Student student) {
        var studentTemporary = studentRepository.findById(student.getId()).orElse(null);
        if (studentTemporary != null) {
            studentRepository.save(student);;
        }
        return student;
    }

    public Collection<Student> filterByAge(int age) {
        return  studentRepository.findAllByAge(age);
    }

    public Collection<Student> returnAll() {
        return studentRepository.findAllBy();
    }


    public Collection<Student> filterByAgeBetween(int min, int max) {
        return studentRepository.findAllByAgeBetween(min, max);
    }

    public Faculty returnByFaculty(long id) {
        var studentTemporary = studentRepository.findById(id).orElseThrow(
                () -> new StudentNotFoundException ());
        return studentTemporary.getFaculty();
    }
}
