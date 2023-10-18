package com.example.homework3_4_introductiontosql.repository;

import com.example.homework3_4_introductiontosql.model.Faculty;
import com.example.homework3_4_introductiontosql.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findFacultiesByColor (String color);
    Collection<Faculty>  findAllBy ();
    Collection<Faculty> findAllByNameOrColorIgnoreCase(String name, String color);
    Faculty findFirstByNameIgnoreCase (String name);

    Collection<Student> findAllStudentById (Long faculty_id);

}
