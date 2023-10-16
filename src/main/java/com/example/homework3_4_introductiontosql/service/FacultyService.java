package com.example.homework3_4_introductiontosql.service;

import com.example.homework3_4_introductiontosql.model.Faculty;
import com.example.homework3_4_introductiontosql.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class FacultyService {
    private final FacultyRepository repository;
    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Faculty add(Faculty faculty) {
      return repository.save(faculty);
    }

    public Faculty get(long id) {
      return repository.findById(id).orElse(null);
    }

    public Faculty remove(long id) {
        var facultyTemporary = repository.findById(id).orElse(null);
        if (facultyTemporary != null) {
            repository.delete(facultyTemporary);
        }
          return facultyTemporary;
    }

    public Faculty update(Faculty faculty) {
        var facultyTemporary = repository.findById(faculty.getId()).orElse(null);
        if (facultyTemporary != null) {
            repository.save(faculty);;
        }
        return faculty;
    }

    public Collection<Faculty> filterByColor(String color) {
        return  repository.findFacultiesByColor(color);
    }

    public Collection<Faculty> returnAll() {
        return repository.findAllBy();
    }

    public Collection<Faculty> filterByNameOrColor(String name, String color) {
        return repository.findAllByNameOrColorIgnoreCase(name,color);
    }
}
