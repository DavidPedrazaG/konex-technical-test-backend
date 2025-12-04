package konex_technical_test_backend.infrastructure.adapter.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class SaleJpaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medicine_id", nullable = false)
    private MedicineJpaEntity medicine;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    public SaleJpaEntity() {
    }

    public SaleJpaEntity(UUID id, LocalDateTime dateTime, MedicineJpaEntity medicine, int quantity, double unitPrice, double totalPrice) {
        this.id = id;
        this.dateTime = dateTime;
        this.medicine = medicine;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    @PrePersist
    protected void onCreate() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
        if (this.dateTime == null) {
            this.dateTime = LocalDateTime.now();
        }
        if (this.unitPrice == 0) {
            this.unitPrice = this.medicine.getUnitPrice();
        }
        if (this.totalPrice == 0) {
            this.totalPrice = this.unitPrice * this.quantity;
        }
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

    public MedicineJpaEntity getMedicine() {
        return this.medicine;
    }

    public void setMedicine(MedicineJpaEntity medicine) {
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
