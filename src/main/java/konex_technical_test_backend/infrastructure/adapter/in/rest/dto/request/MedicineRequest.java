package konex_technical_test_backend.infrastructure.adapter.in.rest.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MedicineRequest {
    
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotBlank(message = "El laboratorio fabricante no puede estar vacío")
    private String factoryLaboratory;

    @NotNull(message = "La fecha de fabricación no puede ser nula")
    private LocalDate dateManufactured;

    @NotNull(message = "La fecha de expiración no puede ser nula")
    private LocalDate expirationDate;

    @NotNull(message = "La cantidad en stock no puede ser nula")
    @Positive(message = "La cantidad en stock debe ser un número positivo")
    private Integer quantityInStock;

    @NotNull(message = "El precio unitario no puede ser nulo")
    @Positive(message = "El precio unitario debe ser un número positivo")
    private Double unitPrice;

    public MedicineRequest() {
    }

    public MedicineRequest(String name, String factoryLaboratory, LocalDate dateManufactured, LocalDate expirationDate, Integer quantityInStock, Double unitPrice) {
        this.name = name;
        this.factoryLaboratory = factoryLaboratory;
        this.dateManufactured = dateManufactured;
        this.expirationDate = expirationDate;
        this.quantityInStock = quantityInStock;
        this.unitPrice = unitPrice;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFactoryLaboratory() {
        return this.factoryLaboratory;
    }

    public void setFactoryLaboratory(String factoryLaboratory) {
        this.factoryLaboratory = factoryLaboratory;
    }

    public LocalDate getDateManufactured() {
        return this.dateManufactured;
    }

    public void setDateManufactured(LocalDate dateManufactured) {
        this.dateManufactured = dateManufactured;
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getQuantityInStock() {
        return this.quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Double getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }


}
