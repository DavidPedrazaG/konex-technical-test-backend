package konex_technical_test_backend.infrastructure.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import konex_technical_test_backend.domain.model.Medicine;
import konex_technical_test_backend.infrastructure.adapter.out.persistence.entity.MedicineJpaEntity;

@Component
public class MedicineJpaMapper {

    public MedicineJpaMapper() {
    }

    public Medicine toDomain(MedicineJpaEntity jpaEentity) {
        if (jpaEentity == null) {
            return null;
        }
        Medicine medicine = new Medicine();
        medicine.setId(jpaEentity.getId());
        medicine.setName(jpaEentity.getName());
        medicine.setFactoryLaboratory(jpaEentity.getFactoryLaboratory());
        medicine.setDateManufactured(jpaEentity.getDateManufactured());
        medicine.setExpirationDate(jpaEentity.getExpirationDate());
        medicine.setQuantityInStock(jpaEentity.getQuantityInStock());
        medicine.setUnitPrice(jpaEentity.getUnitPrice());
        return medicine;
    }

    public MedicineJpaEntity toJpaEntity(Medicine medicine) {
        if (medicine == null) {
            return null;
        }
        MedicineJpaEntity jpaEntity = new MedicineJpaEntity();
        jpaEntity.setId(medicine.getId());
        jpaEntity.setName(medicine.getName());
        jpaEntity.setFactoryLaboratory(medicine.getFactoryLaboratory());
        jpaEntity.setDateManufactured(medicine.getDateManufactured());
        jpaEntity.setExpirationDate(medicine.getExpirationDate());
        jpaEntity.setQuantityInStock(medicine.getQuantityInStock());
        jpaEntity.setUnitPrice(medicine.getUnitPrice());
        return jpaEntity;
    }

    public void updateJpaEntity(Medicine medicine, MedicineJpaEntity jpaEntity) {
        if (medicine == null || jpaEntity == null) {
            return;
        }
        jpaEntity.setName(medicine.getName());
        jpaEntity.setFactoryLaboratory(medicine.getFactoryLaboratory());
        jpaEntity.setDateManufactured(medicine.getDateManufactured());
        jpaEntity.setExpirationDate(medicine.getExpirationDate());
        jpaEntity.setQuantityInStock(medicine.getQuantityInStock());
        jpaEntity.setUnitPrice(medicine.getUnitPrice());
    }
}
