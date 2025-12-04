package konex_technical_test_backend.domain.exception.medicine;

import java.util.UUID;

import konex_technical_test_backend.domain.exception.BaseException;

public class MedicineNotFoundException extends BaseException {

    private static final String CODE = "MEDICINE_NOT_FOUND";
    private static final String DEFAULT_MESSAGE = "El medicamento no ha sido encontrado";

    public MedicineNotFoundException(UUID id) {
        super(
            String.format("El medicamento con el id '%s' no ha sido encontrado", id),
            CODE
        );
    }

    public MedicineNotFoundException(String message) {
        super(message, CODE);
    }

    public MedicineNotFoundException() {
        super(DEFAULT_MESSAGE, CODE);
    }
    
}
