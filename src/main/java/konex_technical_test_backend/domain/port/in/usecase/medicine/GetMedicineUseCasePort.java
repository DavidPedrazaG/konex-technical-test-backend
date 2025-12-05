package konex_technical_test_backend.domain.port.in.usecase.medicine;

import java.time.LocalDate;
import java.util.List;

import konex_technical_test_backend.domain.model.Medicine;

public interface GetMedicineUseCasePort {
    
    List<Medicine> getAll();
    Medicine getById(String id);
    Medicine getByName(String name);
    List<Medicine> getByFactoryLaboratory(String factoryLaboratory);
    List<Medicine> getByDateManufactured(LocalDate startDate, LocalDate enDate);
    List<Medicine> getByExpirationDate(LocalDate startDate, LocalDate enDate);
    List<Medicine> getByQuantityInStock(int from, int to);
    List<Medicine> getByUnitPrice(double from, double to);

}
