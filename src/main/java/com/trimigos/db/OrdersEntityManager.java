package com.trimigos.db;

import com.trimigos.entity.OrderEntity;

import java.sql.*;
import java.util.ArrayList;


public class OrdersEntityManager {

    private Connection connection;

    public ArrayList<OrderEntity> getOrders() {
        return orders;
    }

    private ArrayList<OrderEntity> orders;


    public OrdersEntityManager()
    {
              connect();
              orders = new ArrayList<>();
    }
    /**
     * Connect to Orders database
     */
      public  void connect() {

        System.out.println("connect called");
        connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            // db parameters
            String url = "jdbc:sqlite:C:/trimigosbooks/sqlite/dbs/orderdb.db";
            // create a connection to the database
            connection = DriverManager.getConnection(url);


            if(connection == null)
            {
                System.out.println("Connection to SQLite has not been established.");
            }
            else
            {
                System.out.println("Connection to SQLite has been established.");
            }




        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    synchronized public void getAllOrders()
    {
        try
        {
            orders.clear();
            if(connection != null)
            {
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM Orders";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next())
                {
                    String orderId = resultSet.getString("orderid");
                    String customer_name = resultSet.getString(":customer_name");
                    String location = resultSet.getString(":location");
                    boolean pending =  (resultSet.getInt("pending") ==0)?true:false;
                    String items = resultSet.getString("items");

                    orders.add(new OrderEntity(customer_name,orderId,location,pending,items));
                }

                System.out.println("  Orders fetched succesfully");
            }



        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    synchronized  public void getAllPendingOrders()   {
        Connection conn=null;
        try
        {

            Class.forName("org.sqlite.JDBC");
            // db parameters
            String url = "jdbc:sqlite:C:/trimigosbooks/sqlite/dbs/orderdb.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Getting all pending orders");
            orders.clear();

            if(conn != null && !conn.isClosed())
            {

                Statement statement = conn.createStatement();

                String query = "SELECT * FROM Orders where pending=1";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next())
                {
                    String orderId = resultSet.getString("orderid");
                    String customer_name = resultSet.getString("customer_name");
                    String location = resultSet.getString("location");
                    String items = resultSet.getString("items");

                    orders.add(new OrderEntity(customer_name,orderId,location,true,items));
                }

                System.out.println("Pending orders fetched succesfully");
                conn.close();
            }



        }catch (SQLException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }


    synchronized  public OrderEntity getOrder(String OrderId)   {
        Connection conn=null;
        OrderEntity order =null;
        try
        {


            Class.forName("org.sqlite.JDBC");
            // db parameters
            String url = "jdbc:sqlite:C:/trimigosbooks/sqlite/dbs/orderdb.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            if(conn != null && !conn.isClosed())
            {



                String query = "SELECT * FROM Orders where orderID=?";
                PreparedStatement statement = conn.prepareStatement(query);

                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next())
                {
                    String orderId = resultSet.getString("orderid");
                    String customer_name = resultSet.getString("customer_name");
                    String location = resultSet.getString("location");
                    String items = resultSet.getString("items");

                    order = new OrderEntity(customer_name,orderId,location,true,items);
                }


                conn.close();
            }



        }catch (SQLException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }

        return order;
    }


    synchronized public void updateOrderCompleted(String orderId) {
        Connection conn=null;
        String updateQuery = "UPDATE Orders SET pending = ? where orderid =?";
        // Create a PreparedStatement
        try {

            Class.forName("org.sqlite.JDBC");
            // db parameters
            String url = "jdbc:sqlite:C:/trimigosbooks/sqlite/dbs/orderdb.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);

            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, orderId);
           if( preparedStatement.executeUpdate() ==1)
           {
               System.out.println("Updated succesfully");
           }

            System.out.println("OrderId: " + orderId + "Updated successfully");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error updating data in SQLite database.");
            e.printStackTrace();
        }
    }

    public void deleteOrder(int orderId) {
        String deleteOrderQuery = "Delete From Orders where orderid =?";
        // Create a PreparedStatement
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteOrderQuery);

            preparedStatement.setInt(1, 0);

            preparedStatement.executeQuery(deleteOrderQuery);

            System.out.println("OrderId: " + orderId + "deleted successfully");

        } catch (SQLException e) {
            System.out.println("Error occured when deleting the order");
            e.printStackTrace();
        }
    }

    public void deleteAllOrders( ) {
        String deleteQuery = "Delete From Orders";
        // Create a PreparedStatement
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.executeQuery(deleteQuery);
            System.out.println("orders purged successfully");

        } catch (SQLException e) {
            System.out.println("Error clearing orders table");
            e.printStackTrace();
        }
    }

    synchronized public void AddOrder(String orderId,String customerName,String location,String items) {


        String insertOrUpdateQuery = "INSERT  INTO ORDERS (orderid, customer_name, location, pending, items)  VALUES( ?, ?, ?, ?, ? )";
        Connection conn=null;
        // Create a PreparedStatement
        try {

            Class.forName("org.sqlite.JDBC");
            // db parameters
            String url = "jdbc:sqlite:C:/trimigosbooks/sqlite/dbs/orderdb.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = conn.prepareStatement(insertOrUpdateQuery);

            preparedStatement.setString(1,orderId);
            preparedStatement.setString(2, customerName);
            preparedStatement.setString(3, location);
            preparedStatement.setInt(4, 1);
            preparedStatement.setString(5,items);
            preparedStatement.executeUpdate();

            System.out.println("Order persisted successfully for the customer "+customerName);
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {

            if(e.getMessage().contains("PRIMARY KEY"))
            {
                System.out.println("Ignorning duplicate order "+orderId);
            }
            else
            {
                e.printStackTrace();
            }


        }
    }

    public void disConnect()
    {
        try
        {
            connection.close();
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    synchronized  public void clearOrders()
    {
        orders.clear();
    }



}