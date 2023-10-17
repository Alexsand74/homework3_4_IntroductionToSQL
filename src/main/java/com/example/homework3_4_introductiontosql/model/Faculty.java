package com.example.homework3_4_introductiontosql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;

    @JsonIgnore
    @OneToMany (mappedBy = "faculty")
    private Collection <Student> students;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Faculty{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", color='" + color + '\'' +
               '}';
    }
}
