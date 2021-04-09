package pe.com.jdmm21.example.demo2.web;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ErrorResult {
    private final List<FieldValidationError> fieldErrors = new ArrayList<>();

    public ErrorResult(String field, String message) {
        this.fieldErrors.add(new FieldValidationError(field, message));
    }
}
