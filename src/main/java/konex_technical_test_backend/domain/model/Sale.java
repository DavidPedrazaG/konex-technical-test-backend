package konex_technical_test_backend.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Sale {
    
    private String id;
    private LocalDateTime dateTime;
    private Medicine medicine;
    private int quantity;
    private double unitPrice;
    private double totalPrice;

    public Sale() {
    }
    
    public Sale(String id, LocalDateTime dateTime, Medicine medicine, int quantity, double unitPrice, double totalPrice) {
        this.id = id;
        this.dateTime = dateTime;
        this.medicine = medicine;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }
    
    public Sale(Medicine medicine, int quantity, double unitPrice) {
        this.id = null;
        this.dateTime = LocalDateTime.now();
        this.medicine = medicine;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = unitPrice * quantity;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", dateTime='" + dateTime + '\'' +
                ", medicineId=" + medicine.getId() +
                ", medicineName=" + medicine.getName() +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
            '}';
    }

}
