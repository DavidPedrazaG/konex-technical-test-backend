package konex_technical_test_backend.infrastructure.adapter.in.rest.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public class GenerateSaleRequest {
    
    @NotNull(message = "El ID del medicamento no puede ser nulo")
    private UUID medicineId;

    @NotNull(message = "La cantidad no puede ser nula")
    private Integer quantity;

    public GenerateSaleRequest() {
    }

    public GenerateSaleRequest(UUID medicineId, Integer quantity) {
        this.medicineId = medicineId;
        this.quantity = quantity;
    }

    public UUID getMedicineId() {
        return this.medicineId;
    }

    public void setMedicineId(UUID medicineId) {
        this.medicineId = medicineId;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
