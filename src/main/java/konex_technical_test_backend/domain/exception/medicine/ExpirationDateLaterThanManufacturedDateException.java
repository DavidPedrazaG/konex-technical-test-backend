package konex_technical_test_backend.domain.exception.medicine;

import java.time.LocalDate;

import konex_technical_test_backend.domain.exception.BaseException;

public class ExpirationDateLaterThanManufacturedDateException extends BaseException {

    private static final String CODE = "EXPIRATION_DATE_LATER";
    private static final String DEFAULT_MESSAGE = "La fecha de vencimiento no puede ser mayor a la fecha de fabricación";

    public ExpirationDateLaterThanManufacturedDateException(LocalDate dateManufactured, LocalDate expirationDate) {
        super(
            String.format("La fecha de vencimiento ('%s') no puede ser mayor a la fecha de fabricación ('%s')", dateManufactured, expirationDate),
            CODE
        );
    }

    public ExpirationDateLaterThanManufacturedDateException(String message) {
        super(message, CODE);
    }

    public ExpirationDateLaterThanManufacturedDateException() {
        super(DEFAULT_MESSAGE, CODE);
    }
    
}
