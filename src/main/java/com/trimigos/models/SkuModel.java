package com.trimigos.models;

import com.trimigos.db.SkuEntityManager;
import com.trimigos.entity.SkuEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SkuModel {

    private String sku;

    private SkuEntityManager skuEntityManager;




    public SkuModel(String sku) {
        this.sku = sku;

        skuEntityManager = new SkuEntityManager(new SkuEntity(this.sku));


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
