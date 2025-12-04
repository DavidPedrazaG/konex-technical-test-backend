package konex_technical_test_backend.domain.port.in.usecase.sale;

import konex_technical_test_backend.domain.model.Sale;

public interface GenerateSaleUseCasePort {
    
    Sale execute(String medicineId, int quantity);

}
