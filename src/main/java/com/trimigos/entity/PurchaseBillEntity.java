package com.trimigos.entity;

import java.time.LocalDate;
import java.util.UUID;

public class PurchaseBillEntity {

    public PurchaseBillEntity(String vehicleNumber, String carrierName, String modeOfTransport, Integer grNumber, LocalDate billDate, double totalBillValue, int totalItems) {
        this.vehicleNumber = vehicleNumber;
        this.carrierName = carrierName;
        ModeOfTransport = modeOfTransport;
        this.grNumber = grNumber;
        this.billDate = billDate;
        this.totalBillValue = totalBillValue;
        this.totalItems = totalItems;
        this.purchaseBillId = UUID.randomUUID().toString();
    }

    public String getPurchaseBillId() {
        return purchaseBillId;
    }

    private String purchaseBillId;
    private String vehicleNumber;
    private String carrierName;
    private String ModeOfTransport;
    private Integer grNumber;
    private LocalDate billDate;
    private double totalBillValue;
    private int totalItems;


    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getModeOfTransport() {
        return ModeOfTransport;
    }

    public void setModeOfTransport(String modeOfTransport) {
        ModeOfTransport = modeOfTransport;
    }

    public Integer getGrNumber() {
        return grNumber;
    }

    public void setGrNumber(Integer grNumber) {
        this.grNumber = grNumber;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public double getTotalBillValue() {
        return totalBillValue;
    }

    public void setTotalBillValue(double totalBillValue) {
        this.totalBillValue = totalBillValue;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}
