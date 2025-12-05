package konex_technical_test_backend.domain.port.in.usecase.sale;

import java.time.LocalDateTime;
import java.util.List;

import konex_technical_test_backend.domain.model.Sale;

public interface GetSaleUseCasePort {

    List<Sale> getAll();
    Sale getById(String id);
    List<Sale> getByDateTime(LocalDateTime startDate, LocalDateTime enDate);
    List<Sale> getByMedicineId(String id);
    List<Sale> getByQuantity(int from, int to);
    List<Sale> getByUnitPrice(double from, double to);
    List<Sale> getByTotalPrice(double from, double to);
}
