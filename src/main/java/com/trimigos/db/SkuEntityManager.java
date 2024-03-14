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
        String insertSKUQuery = "INSERT  INTO SKU (name)  VALUES( ?)";


        Connection conn = null;
        // Create a PreparedStatement
        try {

            Class.forName("org.sqlite.JDBC");
            // db parameters
            String url = "jdbc:sqlite:C:/sqlite/dbs/orderdb.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = conn.prepareStatement(insertSKUQuery);


            preparedStatement.setString(1, sku.getSku());

            preparedStatement.executeUpdate();


            System.out.println("SKU: " + sku.getSku() + " persisted successfully");
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {

            if (e.getMessage().contains("PRIMARY KEY")) {
                System.out.println("Ignorning duplicate SKU: " + sku.getSku());
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
                String query = "SELECT * FROM SKU";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int skuId = resultSet.getInt("id");
                    String skuName = resultSet.getString("name");


                    skuEntities.add(new SkuEntity(skuId, skuName));
                }

                System.out.println("  SKU fetched succesfully");
                conn.close();
            }


        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return  skuEntities;
    }
}
