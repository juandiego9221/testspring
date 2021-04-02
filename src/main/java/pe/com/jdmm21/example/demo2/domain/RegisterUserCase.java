package pe.com.jdmm21.example.demo2.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.jdmm21.example.demo2.persistence.UserEntity;
import pe.com.jdmm21.example.demo2.persistence.UserRepository;

@Service
@RequiredArgsConstructor
public class RegisterUserCase {
    //    @Autowired
//    private UserRepository userRepository;
    /*
    EL CONTENIDO DE ESTE ARCHIVO NO VA A CAMBIAR DURANTE EL TIEMPO DE VIDAD DE LA APLICACION
     */
    private final UserRepository userRepository;

    public UserEntity registerUser(User user) {
        UserEntity userEntity = new UserEntity();
        user.setName(user.getName());
        return userRepository.save(userEntity);
    }
}
