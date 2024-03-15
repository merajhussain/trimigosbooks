package com.trimigos.models;

import com.trimigos.db.SkuEntityManager;
import com.trimigos.entity.SkuEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SkuModel {

    private String sku;
    private double rate;
    private double salePrice;
    private int thresholdQuantity;

    private SkuEntityManager skuEntityManager;




    public SkuModel(String sku,double rate,double salePrice, int thresholdQuantity) {
        this.sku = sku;
        this.rate = rate;
        this.salePrice = salePrice;
        this.thresholdQuantity = thresholdQuantity;

        skuEntityManager = new SkuEntityManager(new SkuEntity(this.sku,this.rate,this.salePrice,this.thresholdQuantity));


    }

    public SkuModel() {

        skuEntityManager = new SkuEntityManager();
    }

    public void saveSku()
    {
        skuEntityManager.save();

    }

    public ArrayList<SkuEntity> getAllSkus()
    {
        return skuEntityManager.fetchAllSkus();
    }
}
