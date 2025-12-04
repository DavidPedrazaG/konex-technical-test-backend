package konex_technical_test_backend.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import konex_technical_test_backend.domain.model.Medicine;

public class SaleDTO {
    
    private UUID id;
    private LocalDateTime dateTime;
    private Medicine medicine;
    private int quantity;
    private double unitPrice;
    private double totalPrice;

    public SaleDTO() {
    }

    public SaleDTO(UUID id, LocalDateTime dateTime, Medicine medicine, int quantity, double unitPrice, double totalPrice) {
        this.id = id;
        this.dateTime = dateTime;
        this.medicine = medicine;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Medicine getMedicine() {
        return this.medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
