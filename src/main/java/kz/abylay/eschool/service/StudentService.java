package kz.abylay.eschool.service;

import kz.abylay.eschool.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();
    Optional<Student> get(Long id);
    Student add(Student student);
    Student edit(Student student);
    void delete(Student student);
}
