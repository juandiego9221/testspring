package pe.com.jdmm21.example.demo2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pe.com.jdmm21.example.demo2.domain.RegisterUserCase2;
import pe.com.jdmm21.example.demo2.persistence.UserEntity;
import pe.com.jdmm21.example.demo2.persistence.UserRepository;
import pe.com.jdmm21.example.demo2.web.UserResource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
PRUEBA DE INTERGRACION
    CONJUINTO DE MULTIPLES UNIT TEST
    TEST QUE CUBRE MULTIPLES CAPAS, COMO INTERACCION ENTRE CPA DE NEGOCIO Y CAPA DE PERSISTENCIA
    UN TEST QUE CUBRE EL FLUJO COMPLETO DE LA APLICACION, POR EJEMPLO CUANDO SE ENVIA UN REQUEST A LA APLICACION , SE
     VALIDA EL RESPONSE  Y SI ALGUN ESTADO DE LA BASE DE DATOS A CAMBIADO SEGUN SE ESPERA

 @SPRINGBOOTTEST USADO PARA CRAR UN APLICAITION CONTEST QUE CONTIENE TODOS LOS OBJETOS QUE SE NECESTIAN PARA REALIZAR
  TODAS LA PRUEBAS MENCIOANDAS, LA DESVENTAJA ES QUE PUEDE DEMORAR
    PARA PRUEBAS SIMPLLES SE DEBE USAR PLAIN TEST, EN DONDE DE MANERA MANUAL SE CREA EL OBJETO Y LUEGO MOCKERAR, DE
    ESTA MANERA NO SEA REALIZA EL INICIA DE TOODO EL APLICATION CONTEST
    PARA PRUEBAS QUE CUBREN INTEEGRACION CON LA CABE WEB O LA CAPA DE PERSITENCIA SE USA WEBMVCTEST O DATAJPATEST
    PARA PRUEBA QUE INVOLLUCRAN TODA LA APLICACION DESDE UNA PETICION HACIA BASE DE DATOS O TESTR QUE CUBRE PARTES DE
     LA APLICACION QUE SONDIFICES DE LEVANTAR MANUIALMENTE  SE USA SPRINGBOOTTEST


SPRINBOOTTEST
    POR DEFECTO INICIA BUSCANDO EN EL PAQUETE ACTUAL LLAS CLASES TEST Y DESDE AHI HACE LA BUSQUEDA A TRAAVEZ DE LA
    ESTRUCTURA DE PAQUETES, EN BUSCA  DE CLASES ANOTADAS CON SPRINBOOTCONFIGURATION  , DESDE AHI LEE LA
    CONFFIGRUACION  PARA CREAR EL APLICATION CONTEST

    SPRINBBOOTAPPLICATION CONTIENE SPRINBOOTCONFIGURATION

    EXTENDWITH YA ES PARTE DE DATAJPATEST , WEBMVCTEST , SPRINGBOOTTEST
 */
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(
//        properties = {
//                "spring.jpa.hibernate.ddl-auto=create-drop",
//        }
//)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=validate"})
public class RegisterUserCaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void registrationWorksThroughAllLayers() throws Exception {
        UserResource user = new UserResource("jd", "zaphod@galaxy.net");
        mockMvc.perform(post("/forums/{formId}/register", 42L)
                .contentType(MediaType.APPLICATION_JSON)
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());

        UserEntity userEntity = userRepository.findByName("jd");
        assertThat(userEntity.getEmail()).isEqualTo("zaphod@galaxy.net");
    }
}
