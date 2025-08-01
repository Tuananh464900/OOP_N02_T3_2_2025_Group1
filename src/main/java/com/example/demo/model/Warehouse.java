package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<InventoryTransaction> transactions;

    public Warehouse() {}

    public Warehouse(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public List<InventoryTransaction> getTransactions() { return transactions; }
    public void setTransactions(List<InventoryTransaction> transactions) { this.transactions = transactions; }
}
