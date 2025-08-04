package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "warehouses") // Xác định tên bảng trong database
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20) // Code là duy nhất và bắt buộc
    private String code;

    @Column(nullable = false, length = 100) // Tên kho bắt buộc
    private String name;

    @Column(length = 200) // Địa chỉ kho
    private String location;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryTransaction> transactions;

    // Constructors
    public Warehouse() {
    }

    public Warehouse(String code, String name, String location) {
        this.code = code;
        this.name = name;
        this.location = location;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<InventoryTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<InventoryTransaction> transactions) {
        this.transactions = transactions;
    }

    // Phương thức tiện ích
    public void addTransaction(InventoryTransaction transaction) {
        transactions.add(transaction);
        transaction.setWarehouse(this);
    }

    public void removeTransaction(InventoryTransaction transaction) {
        transactions.remove(transaction);
        transaction.setWarehouse(null);
    }

    // toString() để debug
    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}