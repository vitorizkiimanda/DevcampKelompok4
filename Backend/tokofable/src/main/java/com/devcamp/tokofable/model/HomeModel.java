package com.devcamp.tokofable.model;

public class HomeModel {

    private Double totalWeight ;
    private Double totalPrice ;
    private Double sellingPriceYesterday ;
    private Double purchasePriceYesterday ;
    private Double sellingPriceNow ;
    private Double purchasePriceNow ;

    public HomeModel() {
    }

    public HomeModel(Double totalWeight, Double totalPrice, Double sellingPriceYesterday, Double purchasePriceYesterday, Double sellingPriceNow, Double purchasePriceNow) {
        this.totalWeight = totalWeight;
        this.totalPrice = totalPrice;
        this.sellingPriceYesterday = sellingPriceYesterday;
        this.purchasePriceYesterday = purchasePriceYesterday;
        this.sellingPriceNow = sellingPriceNow;
        this.purchasePriceNow = purchasePriceNow;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getSellingPriceYesterday() {
        return sellingPriceYesterday;
    }

    public void setSellingPriceYesterday(Double sellingPriceYesterday) {
        this.sellingPriceYesterday = sellingPriceYesterday;
    }

    public Double getPurchasePriceYesterday() {
        return purchasePriceYesterday;
    }

    public void setPurchasePriceYesterday(Double purchasePriceYesterday) {
        this.purchasePriceYesterday = purchasePriceYesterday;
    }

    public Double getSellingPriceNow() {
        return sellingPriceNow;
    }

    public void setSellingPriceNow(Double sellingPriceNow) {
        this.sellingPriceNow = sellingPriceNow;
    }

    public Double getPurchasePriceNow() {
        return purchasePriceNow;
    }

    public void setPurchasePriceNow(Double purchasePriceNow) {
        this.purchasePriceNow = purchasePriceNow;
    }
}
