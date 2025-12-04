package konex_technical_test_backend.domain.exception.sale;

import java.util.UUID;

import konex_technical_test_backend.domain.exception.BaseException;

public class SaleNotFoundException extends BaseException {

    private static final String CODE = "SALE_NOT_FOUND";
    private static final String DEFAULT_MESSAGE = "La venta no ha sido encontrada";

    public SaleNotFoundException(UUID id) {
        super(
            String.format("La venta con el id '%s' no ha sido encontrada", id),
            CODE
        );
    }

    public SaleNotFoundException(String message) {
        super(message, CODE);
    }

    public SaleNotFoundException() {
        super(DEFAULT_MESSAGE, CODE);
    }
    
}
