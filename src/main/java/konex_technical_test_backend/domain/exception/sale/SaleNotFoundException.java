package konex_technical_test_backend.domain.exception.sale;

import konex_technical_test_backend.domain.exception.BaseException;

public class SaleNotFoundException extends BaseException {

    private static final String CODE = "SALE_NOT_FOUND";
    private static final String DEFAULT_MESSAGE = "La venta no ha sido encontrada";

    public SaleNotFoundException(String id) {
        super(
            String.format("La venta con el id '%s' no ha sido encontrada", id),
            CODE
        );
    }

    public SaleNotFoundException() {
        super(DEFAULT_MESSAGE, CODE);
    }
    
}
