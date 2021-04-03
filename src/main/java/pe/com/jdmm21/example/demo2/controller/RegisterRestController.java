package pe.com.jdmm21.example.demo2.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pe.com.jdmm21.example.demo2.domain.RegisterUserCase2;
import pe.com.jdmm21.example.demo2.domain.User;
import pe.com.jdmm21.example.demo2.web.UserResource;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RegisterRestController {
    final static Logger LOGGER = LoggerFactory.getLogger(RegisterRestController.class);
    private final RegisterUserCase2 registerUserCase;
//    private final RegisterUserCase registerUserCase;

    @PostMapping("/forums/{forumId}/register")
    UserResource register(
            @PathVariable("forumId") Long forumId,
            @Valid @RequestBody UserResource userResource,
            @RequestParam("sendWelcomeMail") boolean sendWelcomeMail
    ) {
        User user = new User(
                userResource.getName(),
                userResource.getEmail()
        );
        Long id = registerUserCase.registerUser(user, sendWelcomeMail);
        return new UserResource(user.getName(), user.getEmail());
    }
}
