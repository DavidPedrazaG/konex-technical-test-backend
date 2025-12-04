package konex_technical_test_backend.infrastructure.adapter.out.persistence.adapter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import konex_technical_test_backend.domain.model.Medicine;
import konex_technical_test_backend.domain.port.out.repository.MedicineRepositoryPort;
import konex_technical_test_backend.infrastructure.adapter.out.persistence.mapper.MedicineJpaMapper;
import konex_technical_test_backend.infrastructure.adapter.out.persistence.repository.MedicineJpaRepository;

@Component
public class MedicineRepositoryAdapter implements MedicineRepositoryPort {
    
    private final MedicineJpaRepository medicineJpaRepository;
    private final MedicineJpaMapper medicineJpaMapper;

    public MedicineRepositoryAdapter(MedicineJpaRepository medicineJpaRepository,
            MedicineJpaMapper medicineJpaMapper) {
        this.medicineJpaRepository = medicineJpaRepository;
        this.medicineJpaMapper = medicineJpaMapper;
    }

    @Override
    public Medicine save(Medicine medicine) {
        var jpaEntity = medicineJpaMapper.toJpaEntity(medicine);
        var savedEntity = medicineJpaRepository.save(jpaEntity);
        return medicineJpaMapper.toDomain(savedEntity);
    }

    @Override
    public List<Medicine> findAll() {
        return medicineJpaRepository.findAll()
            .stream()
            .map(medicineJpaMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Medicine> findById(String id) {
        return medicineJpaRepository.findById(id)
            .map(medicineJpaMapper::toDomain);
    }
    
    @Override
    public Optional<Medicine> findByName(String name) {
        return medicineJpaRepository.findFirstByNameContainingIgnoreCase(name)
            .map(medicineJpaMapper::toDomain);
    }

    @Override
    public List<Medicine> findByFactoryLaboratory(String factoryLaboratory) {
        return medicineJpaRepository.findByFactoryLaboratory(factoryLaboratory)
            .stream()
            .map(medicineJpaMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<Medicine> findByDateManufactured(LocalDate startDate, LocalDate enDate) {
        return medicineJpaRepository.findByDateManufactured(startDate, enDate)
            .stream()
            .map(medicineJpaMapper::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Medicine> findByExpirationDate(LocalDate startDate, LocalDate enDate) {
        return medicineJpaRepository.findByExpirationDate(startDate, enDate)
            .stream()
            .map(medicineJpaMapper::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Medicine> findByQuantityInStock(int from, int to) {
        return medicineJpaRepository.findByQuantityInStock(from, to)
            .stream()
            .map(medicineJpaMapper::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Medicine> findByUnitPrice(double from, double to) {
        return medicineJpaRepository.findByUnitPrice(from, to)
            .stream()
            .map(medicineJpaMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public Medicine update(Medicine medicine) {
        var jpaEntity = medicineJpaMapper.toJpaEntity(medicine);
        var updatedEntity = medicineJpaRepository.save(jpaEntity);
        return medicineJpaMapper.toDomain(updatedEntity);
    }

    @Override
    public void deleteById(String id) {
        medicineJpaRepository.deleteById(id);
    }
}
