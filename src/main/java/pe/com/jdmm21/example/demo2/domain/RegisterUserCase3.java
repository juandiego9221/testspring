package pe.com.jdmm21.example.demo2.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegisterUserCase3 {
    private final SaveUserPort saveUserPort;

    public Long registerUser(User user, boolean sendWelcomeMail) {
        user.setRegistrationDate(LocalDateTime.now());
        return saveUserPort.saveUser(user);
    }


}
