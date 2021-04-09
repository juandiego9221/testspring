package pe.com.jdmm21.example.demo2.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.jdmm21.example.demo2.persistence.StudentEntity;
import pe.com.jdmm21.example.demo2.persistence.StudentRepository;
import pe.com.jdmm21.example.demo2.web.StudentResource;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public int registerUser(@Valid StudentResource studentResource) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setAge(studentResource.getAge());
        studentEntity.setName(studentResource.getName());
        StudentEntity save = studentRepository.save(studentEntity);
        return save.getId();
    }
}
