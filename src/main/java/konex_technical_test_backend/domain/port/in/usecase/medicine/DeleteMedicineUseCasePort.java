package konex_technical_test_backend.domain.port.in.usecase.medicine;

import java.util.UUID;

public interface DeleteMedicineUseCasePort {
    
    void execute(UUID id);

}
