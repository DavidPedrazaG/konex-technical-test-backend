package konex_technical_test_backend.domain.exception.medicine;

import konex_technical_test_backend.domain.exception.BaseException;

public class NameAlreadyRegisteredException extends BaseException {

    private static final String CODE = "MEDICINE_NAME_ALREADY_REGISTERED";
    private static final String DEFAULT_MESSAGE = "Un medicamento ya fue registrado con ese nombre";

    public NameAlreadyRegisteredException(String name, String nameFounded) {
        super(
            String.format("El medicamneto con el nombre %d ya está registrado como %d", name, nameFounded),
            CODE
        );
    }

    public NameAlreadyRegisteredException(String message) {
        super(message, CODE);
    }

    public NameAlreadyRegisteredException() {
        super(DEFAULT_MESSAGE, CODE);
    }
    
}
