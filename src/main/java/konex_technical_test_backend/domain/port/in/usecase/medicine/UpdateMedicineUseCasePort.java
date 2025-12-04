package konex_technical_test_backend.domain.port.in.usecase.medicine;

import java.time.LocalDate;

import konex_technical_test_backend.domain.model.Medicine;

public interface UpdateMedicineUseCasePort {
    
    Medicine execute(String id, String name, String factoryLaboratory, LocalDate dateManufactured, LocalDate expirationDate, int quantityInStock, double unitPrice);
}
