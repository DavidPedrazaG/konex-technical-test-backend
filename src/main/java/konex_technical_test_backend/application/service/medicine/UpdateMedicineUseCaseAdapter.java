package konex_technical_test_backend.application.service.medicine;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import konex_technical_test_backend.domain.exception.ValidationException;
import konex_technical_test_backend.domain.exception.medicine.ExpirationDateLaterThanManufacturedDateException;
import konex_technical_test_backend.domain.exception.medicine.NameAlreadyRegisteredException;
import konex_technical_test_backend.domain.model.Medicine;
import konex_technical_test_backend.domain.port.in.usecase.medicine.UpdateMedicineUseCasePort;
import konex_technical_test_backend.domain.port.out.repository.MedicineRepositoryPort;

@Service
@Transactional
public class UpdateMedicineUseCaseAdapter implements UpdateMedicineUseCasePort {

    private final MedicineRepositoryPort mRepositoryPort;

    public UpdateMedicineUseCaseAdapter(MedicineRepositoryPort mRepositoryPort) {
        this.mRepositoryPort = mRepositoryPort;
    }
    
    public Medicine execute(UUID id, String name, String factoryLaboratory, LocalDate dateManufactured, LocalDate expirationDate, int quantityInStock, double unitPrice) {
        validateData(id, name, factoryLaboratory, dateManufactured, expirationDate, quantityInStock, unitPrice);

        Medicine medicine = new Medicine(id, name, factoryLaboratory, dateManufactured, expirationDate, quantityInStock, unitPrice);

        medicine = mRepositoryPort.update(medicine);

        return medicine;
    }

    private void validateData(UUID id, String name, String factoryLaboratory, LocalDate dateManufactured, LocalDate expirationDate, int quantityInStock, double unitPrice) {

        if (id == null) {
            throw new ValidationException("id", "El id del medicamento es obligatorio");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("name", "El nombre del medicamento es obligatorio");
        }
        
        if (factoryLaboratory == null || factoryLaboratory.trim().isEmpty()) {
            throw new ValidationException("factoryLaboratory", "El laboratorio de fabrica del medicamento es obligatorio");
        }

        if (dateManufactured == null) {
            throw new ValidationException("dateManufactured", "La fecha de fabricacion del medicamento es obligatoria");
        }

        if (expirationDate == null) {
            throw new ValidationException("expirationDate", "La fecha de vencimiento del medicamento es obligatoria");
        }

        if (expirationDate.isBefore(dateManufactured)) {
            throw new ExpirationDateLaterThanManufacturedDateException(dateManufactured, expirationDate);
        }

        if (unitPrice <= 0) {
            throw new ValidationException("unitPrice", "El valor unitario no puede ser menor o igual a cero");
        }

        Optional<Medicine> existMedicine = this.mRepositoryPort.findByName(name);

        if (existMedicine.isPresent()) {
            throw new NameAlreadyRegisteredException(name, existMedicine.get().getName());
        }

    }
}
