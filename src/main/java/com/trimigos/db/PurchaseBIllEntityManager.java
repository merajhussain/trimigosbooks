package com.trimigos.db;

import com.trimigos.entity.PurchaseBillEntity;
import com.trimigos.entity.PurchaseItemEntity;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PurchaseBIllEntityManager {

   private PurchaseBillEntity purchaseBill;
   private ArrayList<PurchaseItemEntity> items;

   public PurchaseBIllEntityManager(PurchaseBillEntity purchaseBill, ArrayList<PurchaseItemEntity> items) {
      this.purchaseBill = purchaseBill;
      this.items = items;
   }


   public void addPurchaseBillData()
   {
      String insertPurchaseOrderQuery = "INSERT  INTO PurchaseOrder (PurchaseOrderId, vehicleNumber, carrierName, ModeOfTransport, grNumber,billDate,totalBillValue,totalItems)  VALUES( ?,?,?,?,?,?,?,?)";


      Connection conn=null;
      // Create a PreparedStatement
      try {

         Class.forName("org.sqlite.JDBC");
         // db parameters
         String url = "jdbc:sqlite:C:/sqlite/dbs/orderdb.db";
         // create a connection to the database
         conn = DriverManager.getConnection(url);
         PreparedStatement preparedStatement = conn.prepareStatement(insertPurchaseOrderQuery);

         conn.setAutoCommit(false);

         preparedStatement.setString(1,purchaseBill.getPurchaseBillId());
         preparedStatement.setString(2, purchaseBill.getVehicleNumber());
         preparedStatement.setString(3, purchaseBill.getCarrierName());
         preparedStatement.setString(4, purchaseBill.getModeOfTransport());
         preparedStatement.setInt(5, purchaseBill.getGrNumber());
         preparedStatement.setDate(6, Date.valueOf(purchaseBill.getBillDate()));
         preparedStatement.setDouble(7,purchaseBill.getTotalBillValue());
         preparedStatement.setDouble(8,purchaseBill.getTotalItems());
         preparedStatement.executeUpdate();

         for( var item: items)
         {
            String insertPurchaseOrderItemQuery = "INSERT  INTO PurchaseItem (itemId, purchaseBillId, skuId, skuname, quantity, rate,discount,taxableAmount,igstr,igstv,finalPrice)  VALUES( ?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedItemStatement = conn.prepareStatement(insertPurchaseOrderItemQuery);
            preparedItemStatement.setString(1,item.getItemID());
            preparedItemStatement.setString(2, item.getPurchaseBillId());
            preparedItemStatement.setInt(3, item.getSku());
            preparedItemStatement.setString(4, item.getSkuName());
            preparedItemStatement.setDouble(5, item.getQuantity());
            preparedItemStatement.setDouble(6, item.getRate());
            preparedItemStatement.setDouble(7, item.getDiscount());
            preparedItemStatement.setDouble(8,item.getTaxableAmount());
            preparedItemStatement.setDouble(9,item.getIgstr());
            preparedItemStatement.setDouble(10,item.getIgstv());
            preparedItemStatement.setDouble(11,item.getFinalPrice());
            preparedItemStatement.executeUpdate();

         }



         conn.commit();
         System.out.println("Purchase Order "+purchaseBill.getPurchaseBillId()+" persisted successfully");
         conn.close();

      } catch (SQLException | ClassNotFoundException e) {

         if(e.getMessage().contains("PRIMARY KEY"))
         {
            System.out.println("Ignorning duplicate purchase order "+purchaseBill.getPurchaseBillId());
         }
         else
         {
            e.printStackTrace();
         }


      }

   }


}
