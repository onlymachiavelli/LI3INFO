package client;

import Schema.Message;
import utils.Composers;

import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) {
        try {
            //initialize the socket message
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Server is Listening on port 5000...");

            //creating a DataInputStream and PrintWriter
            DataInputStream in = new DataInputStream(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send a message to the server
            String message = "Hello from the client";
            out.println(message);

            // Read the length of the incoming serialized message
            int messageLength = in.readInt();
            byte[] data = new byte[messageLength];  // allocate the exact size
            in.readFully(data);  // read the serialized message bytes

            // Deserialize the message
            Message receivedMessage = Composers.deserialize(data);
            if (receivedMessage != null) {
                System.out.println("Message received from the server: " + receivedMessage.toString());
            } else {
                System.out.println("Failed to deserialize message");
            }

            socket.close();
        } catch (IOException e) {
            System.out.println("Error socket connecting: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
