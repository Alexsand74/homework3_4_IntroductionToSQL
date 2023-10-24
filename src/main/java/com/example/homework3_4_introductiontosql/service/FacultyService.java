package com.example.homework3_4_introductiontosql.service;

import com.example.homework3_4_introductiontosql.exception.FacultyNotFoundException;
import com.example.homework3_4_introductiontosql.model.Faculty;
import com.example.homework3_4_introductiontosql.model.Student;
import com.example.homework3_4_introductiontosql.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(Faculty faculty) {
      return facultyRepository.save(faculty);
    }

    public Faculty get(long id) {
      return facultyRepository.findById(id).orElse(null);
    }

    public Faculty remove(long id) {
        var facultyTemporary = facultyRepository.findById(id).orElse(null);
        if (facultyTemporary != null) {
            facultyRepository.delete(facultyTemporary);
        }
          return facultyTemporary;
    }

    public Faculty update(Faculty faculty) {
        facultyRepository.findById(faculty.getId()).ifPresent(facultyTemporary -> facultyRepository.save(faculty));
        return faculty;
    }

    public Collection<Faculty> filterByColor(String color) {
        return  facultyRepository.findFacultiesByColor(color);
    }

    public Collection<Faculty> returnAll() {
        return facultyRepository.findAllBy();
    }

    public Collection<Faculty> filterByNameOrColor(String name, String color) {
        return facultyRepository.findAllByNameOrColorIgnoreCase(name,color);
    }

    public Collection<Student> returnStudentsByNameOfFaculty(String name) {
       var faculty = facultyRepository.findFirstByNameIgnoreCase(name);
    if (faculty == null) { throw new FacultyNotFoundException();}
        return faculty.getStudents();
    }
}
