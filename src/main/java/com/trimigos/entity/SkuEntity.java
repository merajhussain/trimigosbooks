package com.trimigos.entity;

public class SkuEntity {


    public SkuEntity(String sku) {
        this.sku = sku;
    }

    private String sku;
    private int skuId;

    public SkuEntity( int skuId,String sku) {
        this.sku = sku;
        this.skuId = skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }



    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
