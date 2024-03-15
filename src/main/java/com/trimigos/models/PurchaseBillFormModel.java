package com.trimigos.models;

import com.trimigos.db.PurchaseBIllEntityManager;
import com.trimigos.entity.PurchaseBillEntity;
import com.trimigos.entity.PurchaseItemEntity;
import com.trimigos.entity.SkuEntity;

import java.util.ArrayList;
import java.util.HashMap;

public class PurchaseBillFormModel {

    public PurchaseBillFormModel(PurchaseBillFormData purchaseBillFormData, ArrayList<String> skus, HashMap<String, SkuEntity> skuMap) {
        this.purchaseBillFormData = purchaseBillFormData;

        PurchaseBillEntity purchaseBillEntity = new PurchaseBillEntity(purchaseBillFormData.getVehicleNumber(),
                                                                       purchaseBillFormData.getCarrierName(),
                                                                       purchaseBillFormData.getModeOfTransport(),
                                                                       purchaseBillFormData.getGrNumber(),
                                                                       purchaseBillFormData.getBillDate(),
                                                                       purchaseBillFormData.getTotalBillValue(),
                                                                       purchaseBillFormData.getItems().size());

        ArrayList<PurchaseItemEntity> purchaseItemEntities = new ArrayList<>();




        for(var formItem : purchaseBillFormData.getItems() )
        {
            purchaseItemEntities.add(new PurchaseItemEntity(purchaseBillEntity.getPurchaseBillId(),skuMap.get(formItem.getSku()).getId(),
                    formItem.getSku(), formItem.getQuantity(),
                                                            formItem.getRate(),
                                                            formItem.getDiscount(),
                                                            formItem.getTaxableAmount(),
                                                            formItem.getIgstr(),
                                                            formItem.getIgstv(),
                                                            formItem.getFinalPrice(),
                                                            skuMap.get(formItem.getSku()).getQuantity(),
                                                            skuMap.get(formItem.getSku()).getRate()));
        }


        this. purchaseBIllEntityManager = new PurchaseBIllEntityManager(purchaseBillEntity,purchaseItemEntities);
    }

    private PurchaseBillFormData purchaseBillFormData;
    PurchaseBIllEntityManager purchaseBIllEntityManager;


    public void SavePurchaseBill()
    {
        purchaseBIllEntityManager.addPurchaseBillData();
    }

    public PurchaseBillFormData getPurchaseBillFormData() {
        return purchaseBillFormData;
    }

    public void setPurchaseBillFormData(PurchaseBillFormData purchaseBillFormData) {
        this.purchaseBillFormData = purchaseBillFormData;
    }

    public PurchaseBIllEntityManager getPurchaseBIllEntityManager() {
        return purchaseBIllEntityManager;
    }

    public void setPurchaseBIllEntityManager(PurchaseBIllEntityManager purchaseBIllEntityManager) {
        this.purchaseBIllEntityManager = purchaseBIllEntityManager;
    }
}
