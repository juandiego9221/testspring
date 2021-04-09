package pe.com.jdmm21.example.demo2.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class FieldValidationError {
    private String field;
    private String message;

    public FieldValidationError(
            @JsonProperty("field") String field,
            @JsonProperty("message") String message
    ) {
        this.field = field;
        this.message = message;
    }
}
