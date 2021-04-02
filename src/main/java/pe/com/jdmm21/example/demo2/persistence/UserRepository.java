package pe.com.jdmm21.example.demo2.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByName(String name);

    @Query("select u from UserEntity u where u.name=:name")
    UserEntity findByNameCustomerQuery(@Param("name") String name);

//    @Query
//    UserEntity findByNameNativeQuery(@Param("name") String name);
}
