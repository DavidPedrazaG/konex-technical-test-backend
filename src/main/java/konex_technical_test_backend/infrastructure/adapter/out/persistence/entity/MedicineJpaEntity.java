package konex_technical_test_backend.infrastructure.adapter.out.persistence.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "medicines",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
    }
)
public class MedicineJpaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "factory_laboratory", nullable = false)
    private String factoryLaboratory;

    @Column(name = "date_manufactured", nullable = false)
    private LocalDate dateManufactured;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "quantity_in_stock", nullable = false)
    private int quantityInStock;

    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    @OneToMany(mappedBy = "medicine", fetch = FetchType.LAZY)
    private List<SaleJpaEntity> sales = new ArrayList<>();

    public MedicineJpaEntity() {
    }

    public MedicineJpaEntity(UUID id, String name, String factoryLaboratory, LocalDate dateManufactured, LocalDate expirationDate, int quantityInStock, double unitPrice) {
        this.id = id;
        this.name = name;
        this.factoryLaboratory = factoryLaboratory;
        this.dateManufactured = dateManufactured;
        this.expirationDate = expirationDate;
        this.quantityInStock = quantityInStock;
        this.unitPrice = unitPrice;
    }

    @PrePersist
    protected void onCreate() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
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

    public List<SaleJpaEntity> getSales() {
        return this.sales;
    }

    public void setSales(List<SaleJpaEntity> sales) {
        this.sales = sales;
    }

}
