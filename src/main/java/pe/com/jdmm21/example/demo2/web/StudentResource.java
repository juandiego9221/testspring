package pe.com.jdmm21.example.demo2.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class StudentResource {
    @NotNull
    private final String name;
    private final int age;

    public StudentResource(@JsonProperty("name") String name, @JsonProperty("age") int age) {
        this.name = name;
        this.age = age;
    }


}
