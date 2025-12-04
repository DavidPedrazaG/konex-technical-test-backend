package konex_technical_test_backend.application.service.sale;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import konex_technical_test_backend.domain.exception.ValidationException;
import konex_technical_test_backend.domain.exception.medicine.MedicineNotFoundException;
import konex_technical_test_backend.domain.model.Medicine;
import konex_technical_test_backend.domain.model.Sale;
import konex_technical_test_backend.domain.port.in.usecase.sale.GenerateSaleUseCasePort;
import konex_technical_test_backend.domain.port.out.repository.MedicineRepositoryPort;
import konex_technical_test_backend.domain.port.out.repository.SaleRepositoryPort;

@Service
@Transactional
public class GenerateSaleUseCaseAdapter implements GenerateSaleUseCasePort {

    private final MedicineRepositoryPort mRepositoryPort;
    private final SaleRepositoryPort sRepositoryPort;

    public GenerateSaleUseCaseAdapter(MedicineRepositoryPort mRepositoryPort, SaleRepositoryPort sRepositoryPort) {
        this.mRepositoryPort = mRepositoryPort;
        this.sRepositoryPort = sRepositoryPort;
    }
    
    public Sale execute(UUID medicineId, int quantity) {
        
        validateInputs(medicineId, quantity);
        
        Medicine medicine = mRepositoryPort.findById(medicineId)
            .orElseThrow(() -> new MedicineNotFoundException(medicineId));

        Sale sale = new Sale(
            medicine,
            quantity,
            medicine.getUnitPrice()
        );

        sale = sRepositoryPort.generateSale(sale);

        return sale;
        
    }

    private void validateInputs(UUID medicineId, int quantity) {
        if(medicineId == null) {
            throw new ValidationException("medicine", "El medicamento no puede ser nulo");
        }
        if(quantity <= 0) {
            throw new ValidationException("dateTime", "La cantidad debe ser mayor a cero");
        }
    }

}
