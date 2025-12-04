package konex_technical_test_backend.domain.port.out.repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

import konex_technical_test_backend.domain.model.Medicine;

public interface MedicineRepositoryPort {
    
    Medicine save(Medicine medicine);

    List<Medicine> findAll();
    Optional<Medicine> findById(UUID id);
    Optional<Medicine> findByName(String name);
    List<Medicine> findByFactoryLaboratory(String factoryLaboratory);
    List<Medicine> findByDateManufactured(LocalDate startDate, LocalDate enDate);
    List<Medicine> findByExpirationDate(LocalDate startDate, LocalDate enDate);
    List<Medicine> findByQuantityInStock(int from, int to);
    List<Medicine> findByUnitPrice(double from, double to);
    
    Medicine update(Medicine medicine);

    void deleteById(UUID id);
}
