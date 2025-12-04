package konex_technical_test_backend.application.service.medicine;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import konex_technical_test_backend.domain.exception.ValidationException;
import konex_technical_test_backend.domain.exception.medicine.ExpirationDateLaterThanManufacturedDateException;
import konex_technical_test_backend.domain.exception.medicine.NameAlreadyRegisteredException;
import konex_technical_test_backend.domain.model.Medicine;
import konex_technical_test_backend.domain.port.in.usecase.medicine.CreateMedicineUseCasePort;
import konex_technical_test_backend.domain.port.out.repository.MedicineRepositoryPort;

@Service
@Transactional
public class CreateMedicineUseCaseAdapter implements CreateMedicineUseCasePort{

    private final MedicineRepositoryPort mRepositoryPort;

    public CreateMedicineUseCaseAdapter(MedicineRepositoryPort mRepositoryPort) {
        this.mRepositoryPort = mRepositoryPort;
    }

    @Override
    public Medicine execute(String name, String factoryLaboratory, LocalDate dateManufactured, LocalDate expirationDate, int quantityInStock, double unitPrice) {
        validateData(name, factoryLaboratory, dateManufactured, expirationDate, quantityInStock, unitPrice);

        Medicine medicine = new Medicine(name, factoryLaboratory, dateManufactured, expirationDate, quantityInStock, unitPrice);

        medicine = mRepositoryPort.save(medicine);

        return medicine;
    };

    private void validateData(String name, String factoryLaboratory, LocalDate dateManufactured, LocalDate expirationDate, int quantityInStock, double unitPrice) {

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