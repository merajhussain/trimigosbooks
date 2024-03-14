package com.trimigos.models;

import com.trimigos.db.PurchaseBIllEntityManager;
import com.trimigos.entity.PurchaseBillEntity;
import com.trimigos.entity.PurchaseItemEntity;

import java.util.ArrayList;

public class PurchaseBillFormModel {

    public PurchaseBillFormModel(PurchaseBillFormData purchaseBillFormData) {
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
            purchaseItemEntities.add(new PurchaseItemEntity(purchaseBillEntity.getPurchaseBillId(),(int)1,
                    formItem.getSku(), formItem.getQuantity(),
                                                            formItem.getRate(),
                                                            formItem.getDiscount(),
                                                            formItem.getTaxableAmount(),
                                                            formItem.getIgstr(),
                                                            formItem.getIgstv(),
                                                            formItem.getFinalPrice()));
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
