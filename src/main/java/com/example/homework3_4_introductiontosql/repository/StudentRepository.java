package com.example.homework3_4_introductiontosql.repository;

import com.example.homework3_4_introductiontosql.model.Faculty;
import com.example.homework3_4_introductiontosql.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Collection<Student> findAllByAge(int age);
    Collection<Student>  findAllBy ();
    Collection<Student> findAllByAgeBetween(int min, int max);
}
