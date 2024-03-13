package com.trimigos.models;

import java.util.ArrayList;

public class PurchaseBillItem {


    private String sku;
    private double quantity;
    private double rate;
    private double discount;
    private double taxableAmount;
    private double igstr;
    private double igstv;
    private double FinalPrice;


    public PurchaseBillItem(String sku, double quantity, double rate, double discount, double taxableAmount, double igstr, double igstv, double finalPrice) {
        this.sku = sku;
        this.quantity = quantity;
        this.rate = rate;
        this.discount = discount;
        this.taxableAmount = taxableAmount;
        this.igstr = igstr;
        this.igstv = igstv;
        FinalPrice = finalPrice;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
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
        return FinalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        FinalPrice = finalPrice;
    }
}
