package com.trimigos.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class PurchaseBillFormData {

    private String vehicleNumber;
    private String carrierName;
    private String ModeOfTransport;
    private Integer grNumber;
    private LocalDate billDate;
    private ArrayList<PurchaseBillItem> items;


    public PurchaseBillFormData(String vehicleNumber, String carrierName, String modeOfTransport, Integer grNumber, LocalDate billDate, ArrayList<PurchaseBillItem> items) {
        this.vehicleNumber = vehicleNumber;
        this.carrierName = carrierName;
        ModeOfTransport = modeOfTransport;
        this.grNumber = grNumber;
        this.billDate = billDate;
        this.items = items;
    }

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

    public ArrayList<PurchaseBillItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<PurchaseBillItem> items) {
        this.items = items;
    }
}
