package konex_technical_test_backend.domain.port.in.usecase.sale;

import java.util.UUID;

import konex_technical_test_backend.domain.model.Sale;

public interface GenerateSaleUseCasePort {
    
    Sale execute(UUID medicineId, int quantity);

}
