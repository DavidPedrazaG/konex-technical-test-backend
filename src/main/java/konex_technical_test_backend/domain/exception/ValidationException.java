package konex_technical_test_backend.domain.exception;

public class ValidationException extends BaseException{
    private static final String CODE = "VALIDATION_ERROR";

    public ValidationException(String message) {
        super(message, CODE);
    }

    public ValidationException(String field, String message) {
        super(
            String.format("Error de validación en el campo '%s': %s", field, message),
            CODE
        );
    }
}
