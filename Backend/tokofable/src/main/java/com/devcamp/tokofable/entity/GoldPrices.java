package com.devcamp.tokofable.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class GoldPrices {
    @Id
    private String id ;
    private Date date ;
    private Double sellingPrice ;
    private Double purchasePrice ;

    public GoldPrices() {
    }

    public GoldPrices(String id, Date date, Double sellingPrice, Double purchasePrice) {
        this.id = id;
        this.date = date;
        this.sellingPrice = sellingPrice;
        this.purchasePrice = purchasePrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
