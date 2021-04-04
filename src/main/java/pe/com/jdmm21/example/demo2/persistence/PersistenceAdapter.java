package pe.com.jdmm21.example.demo2.persistence;

import org.springframework.stereotype.Component;
import pe.com.jdmm21.example.demo2.domain.SaveUserPort;
import pe.com.jdmm21.example.demo2.domain.User;

@Component
public class PersistenceAdapter implements SaveUserPort {

    private final UserRepository userRepository;

    public PersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long saveUser(User user) {
        UserEntity userEntity = new UserEntity(
                user.getName(),
                user.getEmail()
        );
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return savedUserEntity.getId();
    }
}
