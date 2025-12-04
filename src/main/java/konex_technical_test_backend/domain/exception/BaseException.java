package konex_technical_test_backend.domain.exception;

public class BaseException extends RuntimeException {

    private String code;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message, String errorCode) {
        super(message);
        this.code = errorCode;
    }

    public BaseException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.code = errorCode;
    }

    public String getCode() {
        return code;
    }
}