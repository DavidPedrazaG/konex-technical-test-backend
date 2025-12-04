package konex_technical_test_backend.domain.port.out.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import konex_technical_test_backend.domain.model.Sale;

public interface SaleRepositoryPort {
    
    Sale generateSale(Sale sale);

    List<Sale> findAll();
    Optional<Sale> findById(UUID id);
    List<Sale> findByDateTime(LocalDateTime startDate, LocalDateTime enDate);
    List<Sale> findByMedicineId(UUID id);
    List<Sale> findByQuantity(int from, int to);
    List<Sale> findByUnitPrice(double from, double to);
    List<Sale> findByTotalPrice(double from, double to);

}
