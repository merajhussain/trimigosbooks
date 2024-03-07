/**
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

import express from "express";
import axios from "axios";
import { v4 as uuidv4 } from 'uuid';


const app = express();
app.use(express.json());

const { WEBHOOK_VERIFY_TOKEN, GRAPH_API_TOKEN, PORT } = process.env;

let receivedMessages = [];

app.post("/webhook", async (req, res) => {
   

  // check if the webhook request contains a message
  // details on WhatsApp text message payload: https://developers.facebook.com/docs/whatsapp/cloud-api/webhooks/payload-examples#text-messages
  const message = req.body.entry?.[0]?.changes[0]?.value?.messages?.[0];
  
  let body = message.text.body;
  
  let orderID = uuidv4();
   console.log("Unique Order ID:", orderID);
  
   
  
  console.log("Order received:",body);
  receivedMessages.push({ orderId: orderID, message: body });
 
 

    
  res.sendStatus(200);
});

// accepts GET requests at the /webhook endpoint. You need this URL to setup webhook initially.
// info on verification request payload: https://developers.facebook.com/docs/graph-api/webhooks/getting-started#verification-requests
app.get("/webhook", (req, res) => {
  const mode = req.query["hub.mode"];
  const token = req.query["hub.verify_token"];
  const challenge = req.query["hub.challenge"];

  // check the mode and token sent are correct
  if (mode === "subscribe" && token === WEBHOOK_VERIFY_TOKEN) {
    // respond with 200 OK and challenge token from the request
    res.status(200).send(challenge);
    console.log("Webhook verified successfully!");
  } else {
    // respond with '403 Forbidden' if verify tokens do not match
    res.sendStatus(403);
  }
});

app.get("/", (req, res) => {
  res.send(`<pre>Nothing to see here.
Checkout README.md to start.</pre>`);
});
  
  // GET endpoint to retrieve received messages
app.get("/messages", (req, res) => {
  res.status(200).json(receivedMessages);
});

app.post("/clearmessages", async (req, res) => {
  
   receivedMessages = [];
 
   console.log("Messages cleared successfully");

    
  res.sendStatus(200);
});


app.listen(PORT, () => {
  console.log(`Server is listening on port: ${PORT}`);
});
