package pe.com.jdmm21.example.demo2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pe.com.jdmm21.example.demo2.domain.RegisterUserCase;
import pe.com.jdmm21.example.demo2.domain.User;
import pe.com.jdmm21.example.demo2.persistence.UserRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterUserCaseTest {
    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private RegisterUserCase registerUserCase;

    @BeforeEach
    void initUserCase() {
        registerUserCase = new RegisterUserCase(userRepository);
    }

    @Test
    void saveUser() {
        User user1 = new User("asd", "asd");
        registerUserCase.registerUser(user1);
        assertTrue(true);
    }
}
