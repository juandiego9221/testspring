package pe.com.jdmm21.example.demo2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultMatcher;
import pe.com.jdmm21.example.demo2.web.ErrorResult;
import pe.com.jdmm21.example.demo2.web.FieldValidationError;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

public class ResponseBodyMatcher {
    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> ResultMatcher containsObjectAsJson(
            Object expecteObject,
            Class<T> targetClass
    ) {
        return mvcResult -> {
            String json = mvcResult.getResponse().getContentAsString();
            T actualObject = objectMapper.readValue(json, targetClass);
            assertThat(actualObject).isEqualToComparingFieldByField(expecteObject);
        };
    }

    public ResultMatcher containsError(
            String expectedFieldName,
            String expectedMessage
    ) {
        return mvcResult -> {
            String json = mvcResult.getResponse().getContentAsString();
            ErrorResult errorResult = objectMapper.readValue(json, ErrorResult.class);
            List<FieldValidationError> fieldErrors = errorResult.getFieldErrors().stream()
                    .filter(fieldError -> fieldError.getField().equals(expectedFieldName))
                    .filter(fieldError -> fieldError.getMessage().equals(expectedMessage)).collect(Collectors.toList());
            assertThat(fieldErrors).hasSize(1).withFailMessage("expecting exactly 1 error message with field name " +
                    "'%s' and message '%s'", expectedFieldName, expectedMessage);
        };
    }

    static ResponseBodyMatcher responseBody() {
        return new ResponseBodyMatcher();
    }
}
