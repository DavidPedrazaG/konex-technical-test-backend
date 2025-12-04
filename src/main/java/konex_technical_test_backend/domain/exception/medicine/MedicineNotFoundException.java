package konex_technical_test_backend.domain.exception.medicine;

import konex_technical_test_backend.domain.exception.BaseException;

public class MedicineNotFoundException extends BaseException {

    private static final String CODE = "MEDICINE_NOT_FOUND";
    private static final String DEFAULT_MESSAGE = "El medicamento no ha sido encontrado";

    public MedicineNotFoundException(String id) {
        super(
            String.format("El medicamento con el id '%s' no ha sido encontrado", id),
            CODE
        );
    }

    public MedicineNotFoundException() {
        super(DEFAULT_MESSAGE, CODE);
    }
    
}
