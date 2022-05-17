package kz.abylay.eschool.service.impl;

import kz.abylay.eschool.models.Student;
import kz.abylay.eschool.repositories.StudentRepository;
import kz.abylay.eschool.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public Optional<Student> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public Student add(Student student) {
        return repository.save(student);
    }

    @Override
    public Student edit(Student student) {
        return repository.save(student);
    }

    @Override
    public void delete(Student student) {
        repository.delete(student);
    }

    @PostConstruct
    public void myInit() {
        add(new Student(null, "Abylay", "Sagymbayev", 4.0));
        add(new Student(null, "Tom", "Corner", 2.7));
        add(new Student(null, "John", "Johnson", 3.5));
    }

    @PreDestroy
    public void destroy() {
        repository.deleteAll();
    }
}
