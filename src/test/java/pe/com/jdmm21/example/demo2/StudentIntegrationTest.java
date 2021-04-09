package pe.com.jdmm21.example.demo2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pe.com.jdmm21.example.demo2.persistence.StudentEntity;
import pe.com.jdmm21.example.demo2.persistence.StudentRepository;
import pe.com.jdmm21.example.demo2.web.StudentResource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=validate"})
public class StudentIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentRepository studentRepository;

    /*
    example1
     */

    /*
    example2
     */

    @Test
    void registrationSuccess() throws Exception {
        StudentResource studentResource = new StudentResource("jdmm21",12);
        mockMvc.perform(post("/example/save/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentResource)))
                .andExpect(status().isOk());

        StudentEntity studentEntity = studentRepository.findByName("jdmm21");
        assertThat(studentEntity.getAge()).isEqualTo(12);


    }
}
