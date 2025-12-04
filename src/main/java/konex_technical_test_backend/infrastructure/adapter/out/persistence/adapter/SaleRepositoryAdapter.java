package konex_technical_test_backend.infrastructure.adapter.out.persistence.adapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import konex_technical_test_backend.domain.model.Sale;
import konex_technical_test_backend.domain.port.out.repository.SaleRepositoryPort;
import konex_technical_test_backend.infrastructure.adapter.out.persistence.mapper.SaleJpaMapper;
import konex_technical_test_backend.infrastructure.adapter.out.persistence.repository.SaleJpaRepository;

@Component
public class SaleRepositoryAdapter implements SaleRepositoryPort {
    
    private final SaleJpaRepository saleJpaRepository;
    private final SaleJpaMapper saleJpaMapper;

    public SaleRepositoryAdapter(SaleJpaRepository saleJpaRepository,
            SaleJpaMapper saleJpaMapper) {
        this.saleJpaRepository = saleJpaRepository;
        this.saleJpaMapper = saleJpaMapper;
    }

    @Override
    public Sale generateSale(Sale sale) {
        var jpaEntity = saleJpaMapper.toJpaEntity(sale);
        var savedEntity = saleJpaRepository.save(jpaEntity);
        return saleJpaMapper.toDomain(savedEntity);
    }

    @Override
    public List<Sale> findAll() {
        return saleJpaRepository.findAll()
            .stream()
            .map(saleJpaMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Sale> findById(UUID id) {
        return saleJpaRepository.findById(id)
            .map(saleJpaMapper::toDomain);
    }

    @Override
    public List<Sale> findByDateTime(LocalDateTime startDate, LocalDateTime enDate) {
        return saleJpaRepository.findByDateTime(startDate, enDate)
            .stream()
            .map(saleJpaMapper::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Sale> findByMedicineId(UUID id) {
        return saleJpaRepository.findByMedicineId(id)
            .stream()
            .map(saleJpaMapper::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Sale> findByQuantity(int from, int to) {
        return saleJpaRepository.findByQuantity(from, to)
            .stream()
            .map(saleJpaMapper::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Sale> findByUnitPrice(double from, double to) {
        return saleJpaRepository.findByUnitPrice(from, to)
            .stream()
            .map(saleJpaMapper::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Sale> findByTotalPrice(double from, double to) {
        return saleJpaRepository.findByTotalPrice(from, to)
            .stream()
            .map(saleJpaMapper::toDomain)
            .collect(Collectors.toList());
    }

}
