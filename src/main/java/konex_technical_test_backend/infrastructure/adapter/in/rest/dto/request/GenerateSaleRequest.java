package konex_technical_test_backend.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GenerateSaleRequest {
    
    @NotBlank(message = "El ID del medicamento no puede ser nulo")
    private String medicineId;

    @NotNull(message = "La cantidad no puede ser nula")
    private Integer quantity;

    public GenerateSaleRequest() {
    }

    public GenerateSaleRequest(String medicineId, Integer quantity) {
        this.medicineId = medicineId;
        this.quantity = quantity;
    }

    public String getMedicineId() {
        return this.medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
