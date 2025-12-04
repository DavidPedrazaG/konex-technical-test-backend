package konex_technical_test_backend.infrastructure.adapter.in.rest.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SaleResponse {
    
    private UUID id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateTime;
    private MedicineResponse medicine;
    private int quantity;
    private double unitPrice;
    private double totalPrice;

    public SaleResponse() {
    }

    public SaleResponse(UUID id, LocalDateTime dateTime, MedicineResponse medicine, int quantity, double unitPrice, double totalPrice) {
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

    public MedicineResponse getMedicine() {
        return this.medicine;
    }

    public void setMedicine(MedicineResponse medicine) {
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
