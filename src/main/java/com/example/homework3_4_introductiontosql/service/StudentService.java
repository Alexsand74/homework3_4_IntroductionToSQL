package com.example.homework3_4_introductiontosql.service;

import com.example.homework3_4_introductiontosql.exception.StudentNotFoundException;
import com.example.homework3_4_introductiontosql.model.Faculty;
import com.example.homework3_4_introductiontosql.model.Student;
import com.example.homework3_4_introductiontosql.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository repository;
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student add(Student student) {
        return repository.save(student);
    }

    public Student get(long id) {
        return repository.findById(id).orElse(null);
    }

    public Student remove(long id) {
        var studentTemporary = repository.findById(id).orElse(null);
        if (studentTemporary != null) {
            repository.delete(studentTemporary);
        }
        return studentTemporary;
    }

    public Student update(Student student) {
        var studentTemporary = repository.findById(student.getId()).orElse(null);
        if (studentTemporary != null) {
            repository.save(student);;
        }
        return student;
    }

    public Collection<Student> filterByAge(int age) {
        return  repository.findAllByAge(age);
    }

    public Collection<Student> returnAll() {
        return repository.findAllBy();
    }


    public Collection<Student> filterByAgeBetween(int min, int max) {
        return repository.findAllByAgeBetween(min, max);
    }

    public Faculty returnByFaculty(long id) {
        var studentTemporary = repository.findById(id).orElseThrow(
                () -> new StudentNotFoundException ());
        return studentTemporary.getFaculty();
    }
}
