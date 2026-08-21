package com.chilakacherla.research.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class MandiRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cropName;
    private String variety;
    private String marketLocation; // Dornala, Markapur, Yerragondapalem, Ongole
    private Double pricePerQuintal;
    private Double priceChangeTrend; // +50, -20
    private String status; // HIGHER, STABLE, LOWER
    private LocalDate updateDate;

    public MandiRate() {}

    public MandiRate(String cropName, String variety, String marketLocation, Double pricePerQuintal, Double priceChangeTrend, String status, LocalDate updateDate) {
        this.cropName = cropName;
        this.variety = variety;
        this.marketLocation = marketLocation;
        this.pricePerQuintal = pricePerQuintal;
        this.priceChangeTrend = priceChangeTrend;
        this.status = status;
        this.updateDate = updateDate;
    }

    public Long getId() { return id; }
    public String getCropName() { return cropName; }
    public void setCropName(String cropName) { this.cropName = cropName; }
    public String getVariety() { return variety; }
    public void setVariety(String variety) { this.variety = variety; }
    public String getMarketLocation() { return marketLocation; }
    public void setMarketLocation(String marketLocation) { this.marketLocation = marketLocation; }
    public Double getPricePerQuintal() { return pricePerQuintal; }
    public void setPricePerQuintal(Double pricePerQuintal) { this.pricePerQuintal = pricePerQuintal; }
    public Double getPriceChangeTrend() { return priceChangeTrend; }
    public void setPriceChangeTrend(Double priceChangeTrend) { this.priceChangeTrend = priceChangeTrend; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getUpdateDate() { return updateDate; }
    public void setUpdateDate(LocalDate updateDate) { this.updateDate = updateDate; }
}
