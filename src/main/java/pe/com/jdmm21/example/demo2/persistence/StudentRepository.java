package pe.com.jdmm21.example.demo2.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {
    StudentEntity findByName(String name);
}
