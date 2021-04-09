package pe.com.jdmm21.example.demo2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.com.jdmm21.example.demo2.persistence.UserEntity;
import pe.com.jdmm21.example.demo2.persistence.UserRepository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

/*
LA ANOTACION LEVANTA / CONFIGURA UN APLICATION CONTEXT DE SPRING
SE PUEDE INJECTAR DATASPIRCE , JDBC TEMPLATE O ENTITYMANAGER, COMO TAMBIEN REPOSITORIOS PROPIOS, TODOS ESTROS VAN A
SER CONFIGURADOS HACIA NUESTRA BD EMBEBIDA

ESTE CONFIGURACION ES COMPARTIDO POR TODOS LOS METODOS DENTRO DE UNA CLASE ANOTADAA CON DATAJPATEST
    LO QUE HACE ES QUE CADA PRUEBA CORRA SU PROPIA TRANSACION A LA QUE LUEGO SE LE HACE ROLLBACK
 */
@ExtendWith(SpringExtension.class)
/*
ENABLE SPRING SUPORT
 */
@DataJpaTest
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=validate"})
public class UserEntityRepositoryTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(userRepository).isNotNull();
    }

    @Test
    void whenInitialized_thenFindByName() {
        UserEntity user = userRepository.findByName("Zaphod Beeblebrox");
        assertThat(user).isNotNull();
    }
}
