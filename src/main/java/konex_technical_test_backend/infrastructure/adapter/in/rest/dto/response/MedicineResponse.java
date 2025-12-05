package konex_technical_test_backend.infrastructure.adapter.in.rest.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MedicineResponse {
    
    private String id;
    private String name;
    private String factoryLaboratory;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateManufactured;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    private int quantityInStock;
    private double unitPrice;

    public MedicineResponse() {
    }

    public MedicineResponse(String id, String name, String factoryLaboratory, LocalDate dateManufactured, LocalDate expirationDate, int quantityInStock, double unitPrice) {
        this.id = id;
        this.name = name;
        this.factoryLaboratory = factoryLaboratory;
        this.dateManufactured = dateManufactured;
        this.expirationDate = expirationDate;
        this.quantityInStock = quantityInStock;
        this.unitPrice = unitPrice;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getQuantityInStock() {
        return this.quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

}
