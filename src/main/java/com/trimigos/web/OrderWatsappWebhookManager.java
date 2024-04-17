package com.trimigos.web;

import com.trimigos.db.OrdersEntityManager;
import com.trimigos.entity.OrderEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class OrderWatsappWebhookManager {

    private List<OrderEntity> orders;
    private static int OK_RESPONSE=200;

    public  void pullOrders( ) {
        try {
            // Define the URL
            String urlString = "https://woozy-ubiquitous-comet.glitch.me/messages";
            URL url = new URL(urlString);

            // Create HttpURLConnection object
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder orderRequest = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                orderRequest.append(inputLine);
            }
            in.close();

            // Print the response
            System.out.println("Response: " + orderRequest.toString());

            ProcessOrder(orderRequest);

            // Close the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ProcessOrder(StringBuilder orderRequest)
    {

       //Order format

        /*
        Order HYDERABAD MERAJ
        Item1
        Item2
        Item3
         */



        /*

            JSON Response from webhook Server

            [
               {
                   "orderId": "0e0962a9-8308-4952-b2cf-b243ce295280",
                   "message": "Order HYD SALMAN\nItem1\nItem2\nItem3"
               },
               {
                      "orderId": "c6bc91e8-5f00-468c-a0ae-656d3f1a5043",
                       "message": "Order KDP SARKAR\nItem1\nItem2\nItem3"
                },
               {
                     "orderId": "2463e7d5-47dc-40b6-ab17-5e28683055f2",
                     "message": "Order HYDERABAD MERAJ\nItem1\nItem2\nItem3"
               }
]
        */
        Gson gson = new Gson();

        JsonArray jsonArray = gson.fromJson(orderRequest.toString(), JsonArray.class);
        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            String orderId = jsonObject.get("orderId").getAsString();
            String orderMessage = jsonObject.get("message").getAsString();
            System.out.println("Order ID: " + orderId);
            System.out.println("Message: " + orderMessage);

            String [] orders = orderMessage.split("\n");

            String orderHeader = orders[0];

            String[] details = orderHeader.split(" ");


            String  location =  details[1];
            String customerName = details[2];


                    String items="";

                    for(int itemIndex=1;itemIndex<orders.length;itemIndex++)
                    {
                        items += orders[itemIndex]+",";
                    }

                    items = items.substring(0, items.length()-1); // remove last character

                    OrdersEntityManager ordersEntityManager = new OrdersEntityManager();

                    ordersEntityManager.AddOrder(orderId,customerName,location,items);




        }

    }



    public  void clearOrdersFromServer( ) {
        try {
            // Define the URL
            String urlString = "https://woozy-ubiquitous-comet.glitch.me/clearmessages";
            URL url = new URL(urlString);

            // Create HttpURLConnection object
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to GET
            connection.setRequestMethod("POST");

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if( responseCode == OK_RESPONSE )
            {
                System.out.println("Orders cleared from the server successfully");
            }


            // Close the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}