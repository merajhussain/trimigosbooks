package com.trimigos.entity;

import java.util.UUID;

public class PurchaseItemEntity {

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getPurchaseBillId() {
        return purchaseBillId;
    }

    public void setPurchaseBillId(String purchaseBillId) {
        this.purchaseBillId = purchaseBillId;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTaxableAmount() {
        return taxableAmount;
    }

    public void setTaxableAmount(double taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

    public double getIgstr() {
        return igstr;
    }

    public void setIgstr(double igstr) {
        this.igstr = igstr;
    }

    public double getIgstv() {
        return igstv;
    }

    public void setIgstv(double igstv) {
        this.igstv = igstv;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    private String itemID;
    private String purchaseBillId;
    private int sku;

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    private String skuName;
    private double quantity;
    private double rate;
    private double discount;
    private double taxableAmount;
    private double igstr;
    private double igstv;
    private double finalPrice;

    public PurchaseItemEntity(String purchaseBillId, int sku, String skuName, double quantity, double rate, double discount, double taxableAmount, double igstr, double igstv, double finalPrice) {
        this.purchaseBillId = purchaseBillId;
        this.sku = sku;
        this.skuName = skuName;
        this.quantity = quantity;
        this.rate = rate;
        this.discount = discount;
        this.taxableAmount = taxableAmount;
        this.igstr = igstr;
        this.igstv = igstv;
        this.finalPrice = finalPrice;
        this.itemID = UUID.randomUUID().toString();
    }
}
