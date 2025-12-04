package konex_technical_test_backend.infrastructure.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import konex_technical_test_backend.domain.model.Sale;
import konex_technical_test_backend.infrastructure.adapter.out.persistence.entity.SaleJpaEntity;
import konex_technical_test_backend.infrastructure.adapter.out.persistence.repository.MedicineJpaRepository;
import konex_technical_test_backend.infrastructure.adapter.out.persistence.repository.SaleJpaRepository;

@Component
public class SaleJpaMapper {
    
    private final MedicineJpaRepository medicineJpaRepository;
    private final MedicineJpaMapper medicineJpaMapper;

    public SaleJpaMapper(MedicineJpaRepository medicineJpaRepository, 
        SaleJpaRepository saleJpaRepository, MedicineJpaMapper medicineJpaMapper) {
        this.medicineJpaRepository = medicineJpaRepository;
        this.medicineJpaMapper = medicineJpaMapper;
    }

    public Sale toDomain(SaleJpaEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }
        Sale sale = new Sale();
        sale.setId(jpaEntity.getId());
        sale.setMedicine(medicineJpaMapper.toDomain(jpaEntity.getMedicine()));
        sale.setDateTime(jpaEntity.getDateTime());
        sale.setQuantity(jpaEntity.getQuantity());
        sale.setUnitPrice(jpaEntity.getUnitPrice());
        sale.setTotalPrice(jpaEntity.getTotalPrice());
        return sale;
    }

    public SaleJpaEntity toJpaEntity(Sale sale) {
        if (sale == null) {
            return null;
        }
        SaleJpaEntity jpaEntity = new SaleJpaEntity();
        jpaEntity.setId(sale.getId());
        jpaEntity.setMedicine(medicineJpaRepository.findById(sale.getMedicine().getId()).orElse(null));
        jpaEntity.setDateTime(sale.getDateTime());
        jpaEntity.setQuantity(sale.getQuantity());
        jpaEntity.setUnitPrice(sale.getUnitPrice());
        jpaEntity.setTotalPrice(sale.getTotalPrice());
        return jpaEntity;
    }

    public void updateJpaEntity(Sale sale, SaleJpaEntity jpaEntity) {
        if (sale == null || jpaEntity == null) {
            return;
        }
        jpaEntity.setMedicine(medicineJpaRepository.findById(sale.getMedicine().getId()).orElse(null));
        jpaEntity.setDateTime(sale.getDateTime());
        jpaEntity.setQuantity(sale.getQuantity());
        jpaEntity.setUnitPrice(sale.getUnitPrice());
        jpaEntity.setTotalPrice(sale.getTotalPrice());
    }

}
