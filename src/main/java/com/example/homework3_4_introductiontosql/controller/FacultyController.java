package com.example.homework3_4_introductiontosql.controller;

import com.example.homework3_4_introductiontosql.model.Faculty;
import com.example.homework3_4_introductiontosql.model.Student;
import com.example.homework3_4_introductiontosql.service.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @PostMapping
    public Faculty add(@RequestBody Faculty faculty) {
        return service.add(faculty);
    }

    @GetMapping("/{id}")
    public Faculty get(@PathVariable long id) {
        return service.get(id);
    }

    @PutMapping
    public Faculty update(@RequestBody Faculty faculty) {
        return service.update(faculty);
    }

    @DeleteMapping("/{id}")
    public Faculty remove(@PathVariable long id) {
        return service.remove(id);
    }

    @GetMapping("/byColor")
    public Collection<Faculty> byColor(@RequestParam String color) {
        return service.filterByColor(color);
    }

    @GetMapping("/byAll")
    public Collection<Faculty> byAll() {
        return service.returnAll();
    }

    @GetMapping("/byNameOrColor")
    public Collection<Faculty> byNameOrColor(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color
    ) {
        return service.filterByNameOrColor(name, color);
    }
    @GetMapping("/weReturnStudentsByTheNameOfTheFaculty")
    public Collection<Student> weReturnStudentsByTheNameOfTheFaculty(
            @RequestParam String name) {
        return service.returnStudentsByNameOfFaculty(name);
    }
}
