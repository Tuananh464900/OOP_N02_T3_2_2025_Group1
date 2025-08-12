package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "TÃªn sáº£n pháº©m khÃ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng")
    @Size(min=2,max=150)
    private String name;

    private String category;

    private String description;

    private double defaultPrice;

    private double sellPrice;

    private String unit;

    @Min(0)
    private int minStock;

    @Min(0)
    private int maxStock;

    @Min(0)
    private int quantity; // náº¿u cáº§n

    // Constructors
    public Product() {
    }

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.defaultPrice = price;
        this.sellPrice = price;
        this.unit = "cÃ¡i";
        this.minStock = 0;
        this.maxStock = 1000;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public double getDefaultPrice() { return defaultPrice; }

    public void setDefaultPrice(double defaultPrice) { this.defaultPrice = defaultPrice; }

    public double getSellPrice() { return sellPrice; }

    public void setSellPrice(double sellPrice) { this.sellPrice = sellPrice; }

    public String getUnit() { return unit; }

    public void setUnit(String unit) { this.unit = unit; }

    public int getMinStock() { return minStock; }

    public void setMinStock(int minStock) { this.minStock = minStock; }

    public int getMaxStock() { return maxStock; }

    public void setMaxStock(int maxStock) { this.maxStock = maxStock; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
