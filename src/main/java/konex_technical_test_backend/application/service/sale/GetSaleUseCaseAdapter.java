package konex_technical_test_backend.application.service.sale;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import konex_technical_test_backend.domain.exception.sale.SaleNotFoundException;
import konex_technical_test_backend.domain.model.Sale;
import konex_technical_test_backend.domain.port.in.usecase.sale.GetSaleUseCasePort;
import konex_technical_test_backend.domain.port.out.repository.SaleRepositoryPort;

@Service
@Transactional
public class GetSaleUseCaseAdapter implements GetSaleUseCasePort {

    private final SaleRepositoryPort sRepositoryPort;

    public GetSaleUseCaseAdapter(SaleRepositoryPort sRepositoryPort) {
        this.sRepositoryPort = sRepositoryPort;
    }

    public List<Sale> getAll() {
        return sRepositoryPort.findAll();
    }

    public Sale getById(String id) {
        return sRepositoryPort.findById(id)
            .orElseThrow(() -> new SaleNotFoundException(id));
    }
    public List<Sale> getByDateTime(LocalDateTime startDate, LocalDateTime enDate) {
        return sRepositoryPort.findByDateTime(startDate, enDate);
    }
    public List<Sale> getByMedicineId(String id) {
        return sRepositoryPort.findByMedicineId(id);
    }
    public List<Sale> getByQuantity(int from, int to) {
        return sRepositoryPort.findByQuantity(from, to);
    }
    public List<Sale> getByUnitPrice(double from, double to) {
        return sRepositoryPort.findByUnitPrice(from, to);
    }
    public List<Sale> getByTotalPrice(double from, double to) {
        return sRepositoryPort.findByTotalPrice(from, to);
    }
}
