package pe.com.jdmm21.example.demo2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.com.jdmm21.example.demo2.domain.RegisterUserCase;
import pe.com.jdmm21.example.demo2.domain.User;
import pe.com.jdmm21.example.demo2.persistence.UserEntity;
import pe.com.jdmm21.example.demo2.persistence.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class RegisterUserCaseTest {
    /*
    1. PLAIN MOCKITO
    LA IDEA DEL MOCK ES CREAR UN OBJETO QUE CRA UN OBJETO COMO USER REPOSITOY DESDE AFUERA , SE DEBE DEFINIR
    COMPORTAMIENTO DEL MOCK
     */
//    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    /*
    2. USO DE ANOTACION, APARTE DEL COMPORTAMIENTO DEL MOCK SE DEBE USAR MOCKITO EXTENSION - JUNIT5
     */
    @Mock
    private UserRepository userRepository;
    //    *1
    @InjectMocks
    private RegisterUserCase registerUserCase;

    /*
    EN LUGAR DE INICIALIZAR  DE MANERA MANUAL , SE PUEDE USAR LA ANOTACION INJECTMOCKS EN ESE CAMPO, PARA QUE
    MOCKITO CREAR LA INSTANCI POR NOSOTROS (*1)
     */
//    @BeforeEach
//    void initUserCase() {
//        registerUserCase = new RegisterUserCase(userRepository);
//    }

    @Test
    void saveUser() {
        when(userRepository.save(any(UserEntity.class))).thenReturn(new UserEntity("jd", "as"));
        UserEntity savedUser = registerUserCase.registerUser(new User("jd", "asd"));
        assertThat(savedUser.getName().equals("jd"));
    }
}
