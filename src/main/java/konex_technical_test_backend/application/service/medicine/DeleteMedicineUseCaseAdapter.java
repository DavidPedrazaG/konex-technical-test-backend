package konex_technical_test_backend.application.service.medicine;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import konex_technical_test_backend.domain.port.in.usecase.medicine.DeleteMedicineUseCasePort;
import konex_technical_test_backend.domain.port.out.repository.MedicineRepositoryPort;

@Service
@Transactional
public class DeleteMedicineUseCaseAdapter implements DeleteMedicineUseCasePort {

    private final MedicineRepositoryPort mRepositoryPort;

    public DeleteMedicineUseCaseAdapter(MedicineRepositoryPort mRepositoryPort) {
        this.mRepositoryPort = mRepositoryPort;
    }
    
    public void execute(String id) {

        if(id == null) {
            throw new IllegalArgumentException("El ID del medicamento no puede ser nulo");
        }

        mRepositoryPort.deleteById(id);
    }

}
