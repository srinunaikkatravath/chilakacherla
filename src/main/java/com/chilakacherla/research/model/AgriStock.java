package com.chilakacherla.research.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rbk_agri_stocks")
public class AgriStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private String category; // Fertilizer, Seeds, Pesticides
    private Integer stockBags;
    private Double pricePerBag;
    private String rbkStatus; // IN_STOCK, LIMITED, OUT_OF_STOCK
    private String lastUpdated;

    public AgriStock() {}

    public AgriStock(String itemName, String category, Integer stockBags, Double pricePerBag, String rbkStatus, String lastUpdated) {
        this.itemName = itemName;
        this.category = category;
        this.stockBags = stockBags;
        this.pricePerBag = pricePerBag;
        this.rbkStatus = rbkStatus;
        this.lastUpdated = lastUpdated;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Integer getStockBags() { return stockBags; }
    public void setStockBags(Integer stockBags) { this.stockBags = stockBags; }

    public Double getPricePerBag() { return pricePerBag; }
    public void setPricePerBag(Double pricePerBag) { this.pricePerBag = pricePerBag; }

    public String getRbkStatus() { return rbkStatus; }
    public void setRbkStatus(String rbkStatus) { this.rbkStatus = rbkStatus; }

    public String getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(String lastUpdated) { this.lastUpdated = lastUpdated; }
}
