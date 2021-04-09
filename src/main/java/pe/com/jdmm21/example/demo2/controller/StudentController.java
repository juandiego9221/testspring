package pe.com.jdmm21.example.demo2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.com.jdmm21.example.demo2.domain.StudentService;
import pe.com.jdmm21.example.demo2.web.StudentResource;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @ResponseBody
    @PostMapping("/example/save/user")
    public int getStudentId(@Valid @RequestBody StudentResource studentResource) {
        return studentService.registerUser(studentResource);
    }
}
