package konex_technical_test_backend.domain.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Medicine {
    
    private UUID id;
    private String name;
    private String factoryLaboratory;
    private LocalDate dateManufactured;
    private LocalDate expirationDate;
    private int quantityInStock;
    private double unitPrice;

    public Medicine() {
        
    }


    public Medicine(String name, String factoryLaboratory, LocalDate dateManufactured, LocalDate expirationDate, int quantityInStock, double unitPrice) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.factoryLaboratory = factoryLaboratory;
        this.dateManufactured = dateManufactured;
        this.expirationDate = expirationDate;
        this.quantityInStock = quantityInStock;
        this.unitPrice = unitPrice;
    }

    public Medicine(UUID id, String name, String factoryLaboratory, LocalDate dateManufactured, LocalDate expirationDate, int quantityInStock, double unitPrice) {
        this.id = id;
        this.name = name;
        this.factoryLaboratory = factoryLaboratory;
        this.dateManufactured = dateManufactured;
        this.expirationDate = expirationDate;
        this.quantityInStock = quantityInStock;
        this.unitPrice = unitPrice;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return Objects.equals(id, medicine.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", factoryLaboratory=" + factoryLaboratory +
                ", dateManufactured=" + dateManufactured +
                ", expirationDate=" + expirationDate +
                ", quantityInStock=" + quantityInStock +
                ", unitPrice=" + unitPrice +
            '}';
    }

}
