package pe.com.jdmm21.example.demo2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest(properties = "foo=bar")
@SpringBootTest
//@ActiveProfiles("test")
@TestPropertySource(locations = "/foo.properties")
public class SpringBootPropertiesTest {

    @Value("${foo}")
    String foo;

    @Test
    void test() {
        assertThat(foo).isEqualTo("bar");

    }
}
