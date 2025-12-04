package konex_technical_test_backend.application.service.medicine;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import konex_technical_test_backend.domain.exception.medicine.MedicineNotFoundException;
import konex_technical_test_backend.domain.model.Medicine;
import konex_technical_test_backend.domain.port.in.usecase.medicine.GetMedicineUseCasePort;
import konex_technical_test_backend.domain.port.out.repository.MedicineRepositoryPort;

@Service
@Transactional
public class GetMedicineUseCaseAdapter implements GetMedicineUseCasePort {

    private final MedicineRepositoryPort mRepositoryPort;

    public GetMedicineUseCaseAdapter(MedicineRepositoryPort mRepositoryPort) {
        this.mRepositoryPort = mRepositoryPort;
    }
    
    public List<Medicine> getAll() {
        return mRepositoryPort.findAll();
    }

    public Medicine getById(UUID id) {
        return mRepositoryPort.findById(id)
            .orElseThrow(() -> new MedicineNotFoundException(id));
    }

    public Medicine getByName(String name) {
        return mRepositoryPort.findByName(name)
            .orElseThrow(() -> new MedicineNotFoundException(
                String.format("El medicamento con el nombre '%s' no ha sido encontrado", name)
            ));
    }

    public List<Medicine> getByFactoryLaboratory(String factoryLaboratory) {
        return mRepositoryPort.findByFactoryLaboratory(factoryLaboratory);
    }
    public List<Medicine> getByDateManufactured(LocalDate startDate, LocalDate enDate) {
        return mRepositoryPort.findByDateManufactured(startDate, enDate);
    }
    public List<Medicine> getByExpirationDate(LocalDate startDate, LocalDate enDate) {
        return mRepositoryPort.findByExpirationDate(startDate, enDate);
    }
    public List<Medicine> getByQuantityInStock(int from, int to) {
        return mRepositoryPort.findByQuantityInStock(from, to);
    }
    public List<Medicine> getByUnitPrice(double from, double to) {
        return mRepositoryPort.findByUnitPrice(from, to);
    }

}
