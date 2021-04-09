package pe.com.jdmm21.example.demo2.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*
HAY QUE PREGUNTARSE QUE SE NECESITAR TESTEAR , REPOSITORI ES RESPONSABLE POR LOS OBJETOS DE TIPO ENTIDAD

EL CRITERIO DEL QUERY ES QUE  EL QUERY ES SUFIECIENTEMENTE COMPLEJO PARA REALIZAR UINA PRUEBA, UNA OPCION SON LOS
NATIVE QUERY POR SU NATURALEZA DE NO VALIDACION EN TIEMPO START
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    /*
    CREACION DE QUERY 1
    INFERRED QUERY, SE BASA EN EL NOMBRE DEL METODO
    VALIDACION EN TIEMPO STARTl
     */
    UserEntity findByName(String name);

    /*
     SPRING DATA VALIDA EN TIEMPO START
     */
    @Query("select u from UserEntity u where u.name=:name")
    UserEntity findByNameCustomerQuery(@Param("name") String name);

    /*
    NI SPRING DATA NI HIBERNATE VALIDA NATIVE QUERY EN TIEMPO START
     */
    @Query(value = "select * from user as u where u.name=:name", nativeQuery = true)
    UserEntity findByNameNativeQuery(
            @Param("name") String name
    );

//    @Query
//    UserEntity findByNameNativeQuery(@Param("name") String name);
}
