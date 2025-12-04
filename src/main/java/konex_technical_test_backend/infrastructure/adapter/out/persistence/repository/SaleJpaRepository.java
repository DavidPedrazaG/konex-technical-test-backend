package konex_technical_test_backend.infrastructure.adapter.out.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import konex_technical_test_backend.infrastructure.adapter.out.persistence.entity.SaleJpaEntity;

@Repository
public interface SaleJpaRepository extends JpaRepository<SaleJpaEntity, String> {
    
    List<SaleJpaEntity> findByDateTime(LocalDateTime startDate, LocalDateTime enDate);
    List<SaleJpaEntity> findByMedicineId(String id);
    List<SaleJpaEntity> findByQuantity(int from, int to);
    List<SaleJpaEntity> findByUnitPrice(double from, double to);
    List<SaleJpaEntity> findByTotalPrice(double from, double to);

}
