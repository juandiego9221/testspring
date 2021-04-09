package pe.com.jdmm21.example.demo2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.hql.internal.ast.ErrorReporter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pe.com.jdmm21.example.demo2.controller.ContrllerExceptionHandler;
import pe.com.jdmm21.example.demo2.controller.RegisterRestController;
import pe.com.jdmm21.example.demo2.domain.RegisterUserCase2;
import pe.com.jdmm21.example.demo2.domain.RegisterUserCase3;
import pe.com.jdmm21.example.demo2.domain.User;
import pe.com.jdmm21.example.demo2.web.ErrorResult;
import pe.com.jdmm21.example.demo2.web.UserResource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
INDICA A JUNIT 5 QUE HABILITE SPRING SUPORT
 */
@ExtendWith(SpringExtension.class)
/*
AL INDICAR EL CONTROER EN WEBMVCTEST INDICA A SPRINGBOOT QUE RESTRINJA EL APPLICATION CONTEXT PARA LA PRUEBA SOO A
LOS CONTROLER PROCISTO EN LA ANOTACION
 */
@WebMvcTest(controllers = RegisterRestController.class)
//@WebMvcTest
public class RegisterRestcontrollertest {
    /*
    USADO PARA SIMULAR HTTP REQUEST
     */
    @Autowired
    private MockMvc mockMvc;

    /*
    SPRING PROVE DE MANERA AUTOMATICA BEAN COMO OBJECT MAPPER PARA MAPEAR HACIDE Y DESDE JSON
     */
    @Autowired
    private ObjectMapper objectMapper;

    /*
    MOCKBEAN ES USADO PARA SIMULAR LA LOGICA DE NEGOCIO , YA QUE NO SE QUIERE PROBAR LA INTEGRACION ENTRE CONTROLLER
    Y LOGICA DE NEGOCIO, SINO ENTRE EL CONTROLLER Y LA CAPA HTTP

    MOCKBEAN AUTOMATICAMENTE REEMPLZA EL BEAN DE MISMO TIPO EN ELL APLICATION CONTEXT
     */
    @MockBean
    private RegisterUserCase3 registerUserCase;

    @Test
    void whenValidInput_thenReturn200() throws Exception {
        UserResource user = new UserResource("asd", "asd");
        mockMvc
                .perform(post("/forums/{forumId}/register", 42L)
                        .contentType("application/json")
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    void whenNulllValue_thenReturn400() throws Exception {
        UserResource user = new UserResource(null, "asdaa");
        mockMvc.perform(post("/forums/{forumId}/register", 42L).contentType("application/json").param(
                "sendWelcomeMail", "true").content(objectMapper.writeValueAsString(user))).andExpect(status().isBadRequest());
    }

    @Test
    void whenValidInput_thenMapsToBusinessModel() throws Exception {
        UserResource user = new UserResource("xx1", "correo");
        mockMvc
                .perform(post("/forums/{forumId}/register", 42L)
                        .contentType("application/json")
                        .param("sendWelcomeMail", "true")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
        /*
        DESPUES DE LA LLAMADA AL CONTROLLER ...
        ARGUMENT CAPTOR CAPTURA EL OBJETO USER QUE FUE PASO AL METODO DE LA INTERFACE  Y REALIZA EL ASSERT PARA LOS
        CAMPOS CONTENIDOS EN ESE OBJETO
         */
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        verify(registerUserCase, times(1)).registerUser(userCaptor.capture(), eq(true));
        assertThat(userCaptor.getValue().getName()).isEqualTo("xx1");
        assertThat(userCaptor.getValue().getEmail()).isEqualTo("correo");
    }

    @Test
    void whenValidInput_thenReturnUserResource() throws Exception {
        UserResource user = new UserResource("xx1", "correo");
        /*
        PARA REALIZAR EL ASERCION DEL CUERPO DE RESPUESTA , SE NECESITAR ALMACERA R EL RESULT DE LA INTECION HTTP EN
        UNA VARAIBLE DE TIPO MVCRESULT USANDO EL METODO RETURN()

        SE PUEDE COMPARAR EL JSON STRING DEVEULTO DESDE EL CUERPO DE REPSUESTA Y COMPARARLO CON LA CADENA STRING EN
        EL METOOD ISQUEALTOIGNORINGWHITESPACE
            LA CADENA STRING SE PUEDE GENERAR USANDO OBJECT MAPPER
         */
        MvcResult mvcResult = mockMvc.perform(post("/forums/{formId}/register", 42L)
                .contentType(MediaType.APPLICATION_JSON).param("sendWelcomeMail", "true").content(objectMapper.writeValueAsString(user))).andReturn();

        UserResource expectedResponseBody = new UserResource("xx1", "correo");
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expectedResponseBody));
    }

    @Test
    void whenNullValue_thenReturn400AndErrorResult() throws Exception {
        UserResource user = new UserResource(null, "jdmm@correo");
        MvcResult mvcResult =
                mockMvc.perform(post("/forums/{formId}/register", 42L).contentType(MediaType.APPLICATION_JSON).param(
                        "sendWelcomeMail", "true").content(objectMapper.writeValueAsString(user))).andExpect(status().isBadRequest()).andReturn();

        ErrorResult expectedErrorResponse = new ErrorResult("name", "must not be null");
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        String expectedResponseBody = objectMapper.writeValueAsString(expectedErrorResponse);
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    void whenValidInput_thenReturnsUserResource_withFluentApi() throws Exception {
        UserResource user = new UserResource("jdmm21", "jdmm@correo");
        UserResource expected = new UserResource("jdmm21", "jdmm@correo");
        mockMvc.perform(post("/forums/{formId}/register", 42L).contentType(MediaType.APPLICATION_JSON).param(
                "sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(ResponseBodyMatcher.responseBody().containsObjectAsJson(expected, UserResource.class));
    }

    @Test
    void whenNullValue_thenReturn400AndErrorResult_withFluentApi() throws Exception {
        UserResource user = new UserResource(null, "jdmm21@correo");
        mockMvc.perform(post("/forums/{formId}/register", 43L).contentType(MediaType.APPLICATION_JSON).param(
                "sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(ResponseBodyMatcher.responseBody().containsError("name", "must not be null"));
    }

}
