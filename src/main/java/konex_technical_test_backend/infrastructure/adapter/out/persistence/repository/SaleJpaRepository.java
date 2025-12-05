package konex_technical_test_backend.infrastructure.adapter.out.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import konex_technical_test_backend.infrastructure.adapter.out.persistence.entity.SaleJpaEntity;

@Repository
public interface SaleJpaRepository extends JpaRepository<SaleJpaEntity, String> {
    
    List<SaleJpaEntity> findByDateTimeBetween(LocalDateTime startDate, LocalDateTime enDate);
    List<SaleJpaEntity> findByMedicineId(String id);
    List<SaleJpaEntity> findByQuantityBetween(int from, int to);
    List<SaleJpaEntity> findByUnitPriceBetween(double from, double to);
    List<SaleJpaEntity> findByTotalPriceBetween(double from, double to);

}
