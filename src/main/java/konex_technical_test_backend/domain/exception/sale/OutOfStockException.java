package konex_technical_test_backend.domain.exception.sale;

import konex_technical_test_backend.domain.exception.BaseException;

public class OutOfStockException extends BaseException{

    private static final String CODE = "OUT_OF_STOCK";
    private static final String DEFAULT_MESSAGE = "No se encuentran suficientes medicamentos en stock para la venta requerida";

    public OutOfStockException(int quantityRequired, int quantityInStock) {
        super(
            String.format("No se encuentra la cantidad del medicamento solicitado para la venta. \n Cantidad en Stock: '%s', Cantidad requerida: '%s'", quantityInStock, quantityRequired),
            CODE
        );
    }

    public OutOfStockException(String message) {
        super(message, CODE);
    }

    public OutOfStockException() {
        super(DEFAULT_MESSAGE, CODE);
    }
}
