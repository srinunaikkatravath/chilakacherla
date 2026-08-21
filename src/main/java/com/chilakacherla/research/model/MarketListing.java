package com.chilakacherla.research.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "community_marketplace")
public class MarketListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String listingType; // Produce, Livestock, Machinery, Services
    private String title;
    private String price;
    private String sellerName;
    private String sellerPhone;
    private String location;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Boolean available = true;
    private LocalDate postedDate;

    public MarketListing() {}

    public MarketListing(String listingType, String title, String price, String sellerName,
                         String sellerPhone, String location, String description) {
        this.listingType = listingType;
        this.title = title;
        this.price = price;
        this.sellerName = sellerName;
        this.sellerPhone = sellerPhone;
        this.location = location;
        this.description = description;
        this.available = true;
        this.postedDate = LocalDate.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getListingType() { return listingType; }
    public void setListingType(String listingType) { this.listingType = listingType; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getSellerName() { return sellerName; }
    public void setSellerName(String sellerName) { this.sellerName = sellerName; }

    public String getSellerPhone() { return sellerPhone; }
    public void setSellerPhone(String sellerPhone) { this.sellerPhone = sellerPhone; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }

    public LocalDate getPostedDate() { return postedDate; }
    public void setPostedDate(LocalDate postedDate) { this.postedDate = postedDate; }
}
