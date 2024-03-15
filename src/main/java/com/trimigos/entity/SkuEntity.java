package com.trimigos.entity;

import java.util.UUID;

public class SkuEntity {


    private String id;


    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    private String skuName;
    private double rate;
    private  double salePrice;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThresholdstock() {
        return thresholdstock;
    }

    public void setThresholdstock(int thresholdstock) {
        this.thresholdstock = thresholdstock;
    }

    public SkuEntity(String id, String skuName, double rate, double salePrice, int quantity, int thresholdstock) {
        this.id = id;
        this.skuName = skuName;
        this.rate = rate;
        this.salePrice = salePrice;
        this.quantity = quantity;
        this.thresholdstock = thresholdstock;
    }

    private int quantity;
    private int thresholdstock;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SkuEntity(String id, String skuName, double rate, double salePrice) {
        this.id = id;
        this.skuName = skuName;
        this.rate = rate;
        this.salePrice = salePrice;
    }

    public SkuEntity(String skuName, double rate, double salePrice,int thresholdstock) {
        this.skuName = skuName;
        this.rate = rate;
        this.salePrice = salePrice;
        this.thresholdstock = thresholdstock;
        this.id = UUID.randomUUID().toString();
    }
}
