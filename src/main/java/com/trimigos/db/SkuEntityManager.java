package com.trimigos.db;

import com.trimigos.entity.OrderEntity;
import com.trimigos.entity.SkuEntity;

import java.sql.*;
import java.util.ArrayList;

public class SkuEntityManager {

    private SkuEntity sku;
    private ArrayList<SkuEntity> skuEntities;

    public SkuEntityManager(SkuEntity sku) {
        this.sku = sku;
        skuEntities = new ArrayList<>();
    }

    public SkuEntityManager() {
        skuEntities = new ArrayList<>();

    }

    public SkuEntity getSku() {
        return sku;
    }

    public void setSku(SkuEntity sku) {
        this.sku = sku;
    }

    public void save() {
        String insertSKUQuery = "INSERT  INTO SKU (id,name)  VALUES(?,?)";

        String insertStock = "INSERT INTO InventoryStock (id,name,rate,saleprice,thresholdstock) VALUES (?,?,?,?,?)";


        Connection conn = null;
        // Create a PreparedStatement
        try {

            Class.forName("org.sqlite.JDBC");
            // db parameters
            String url = "jdbc:sqlite:C:/sqlite/dbs/orderdb.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement(insertSKUQuery);
            PreparedStatement stockStatement = conn.prepareStatement(insertStock);

            preparedStatement.setString(1, sku.getId());
            preparedStatement.setString(2, sku.getSkuName());


            stockStatement.setString(1,sku.getId());
            stockStatement.setString(2 ,sku.getSkuName());

            stockStatement.setDouble(3,sku.getRate());
            stockStatement.setDouble(4,sku.getSalePrice());
            stockStatement.setDouble(5,sku.getThresholdstock());

            preparedStatement.executeUpdate();
            stockStatement.executeUpdate();
            conn.commit();


            System.out.println("SKU: " + sku.getSkuName() + " persisted successfully");
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {

            if (e.getMessage().contains("PRIMARY KEY")) {
                System.out.println("Ignorning duplicate SKU: " + sku.getSkuName());
            } else {
                e.printStackTrace();
            }


        }


    }

    public ArrayList<SkuEntity> fetchAllSkus() {
        try {
            skuEntities.clear();
            Connection conn = null;
            Class.forName("org.sqlite.JDBC");
            // db parameters
            String url = "jdbc:sqlite:C:/sqlite/dbs/orderdb.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                Statement statement = conn.createStatement();
                String query = "SELECT * FROM InventoryStock";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String skuId = resultSet.getString("id");
                    String skuName = resultSet.getString("name");
                    double rate = resultSet.getDouble("rate");
                    double salePrice = resultSet.getDouble("saleprice");
                    int quantity = resultSet.getInt("quantity");
                    int threasholdQuanity = resultSet.getInt("thresholdstock");


                    skuEntities.add(new SkuEntity(skuId,skuName,rate,salePrice,quantity,threasholdQuanity));
                }

                System.out.println("  SKU fetched succesfully");
                conn.close();
            }


        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return  skuEntities;
    }


    public SkuEntity fetchSkuById(String id) {
        SkuEntity skuEntity = null;
        try {


            Connection conn = null;
            Class.forName("org.sqlite.JDBC");
            // db parameters
            String url = "jdbc:sqlite:C:/sqlite/dbs/orderdb.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            if (conn != null) {

                String query = "SELECT * FROM InventoryStock where id =?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1,id);
                ResultSet resultSet = preparedStatement.executeQuery(query);
                while (resultSet.next()) {
                    String skuId = resultSet.getString("id");
                    String skuName = resultSet.getString("name");
                    double rate = resultSet.getDouble("rate");
                    double salePrice = resultSet.getDouble("saleprice");
                    int quantity = resultSet.getInt("quantity");
                    int threasholdQuanity = resultSet.getInt("thresholdstock");


                    skuEntity = new SkuEntity(skuId,skuName,rate,salePrice,quantity,threasholdQuanity);
                }

                System.out.println("  SKU fetched succesfully");
                conn.close();
            }


        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return skuEntity;
    }


    public ArrayList<SkuEntity> fetchLowStockSkus() {
        try {
            skuEntities.clear();
            Connection conn = null;
            Class.forName("org.sqlite.JDBC");
            // db parameters
            String url = "jdbc:sqlite:C:/sqlite/dbs/orderdb.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                Statement statement = conn.createStatement();
                String query = "SELECT * FROM InventoryStock where quantity<thresholdstock";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String skuId = resultSet.getString("id");
                    String skuName = resultSet.getString("name");
                    double rate = resultSet.getDouble("rate");
                    double salePrice = resultSet.getDouble("saleprice");
                    int quantity = resultSet.getInt("quantity");
                    int threasholdQuanity = resultSet.getInt("thresholdstock");


                    skuEntities.add(new SkuEntity(skuId,skuName,rate,salePrice,quantity,threasholdQuanity));
                }

                System.out.println("  low stock skus fetched succesfully");
                conn.close();
            }


        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return  skuEntities;
    }
}
