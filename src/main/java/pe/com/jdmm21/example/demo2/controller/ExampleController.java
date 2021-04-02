package pe.com.jdmm21.example.demo2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    @GetMapping("/example1")
    public String example1() {
        return "hola";
    }
}
