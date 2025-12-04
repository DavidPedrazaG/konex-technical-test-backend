package konex_technical_test_backend.infrastructure.adapter.out.persistence.repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import konex_technical_test_backend.infrastructure.adapter.out.persistence.entity.MedicineJpaEntity;

@Repository
public interface MedicineJpaRepository extends JpaRepository<MedicineJpaEntity, String> {
    
    Optional<MedicineJpaEntity> findFirstByNameContainingIgnoreCase(String name);
    List<MedicineJpaEntity> findByFactoryLaboratory(String factoryLaboratory);
    List<MedicineJpaEntity> findByDateManufactured(LocalDate startDate, LocalDate enDate);
    List<MedicineJpaEntity> findByExpirationDate(LocalDate startDate, LocalDate enDate);
    List<MedicineJpaEntity> findByQuantityInStock(int from, int to);
    List<MedicineJpaEntity> findByUnitPrice(double from, double to);

}
