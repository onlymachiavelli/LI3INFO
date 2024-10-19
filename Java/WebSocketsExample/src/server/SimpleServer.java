package server;
import Schema.Message;
import utils.Composers;

import java.io.*;
import java.net.*;
public class SimpleServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Waiting to listen on 5000...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client is Connected -> Info: " + clientSocket.getInetAddress().getHostAddress());

            // Creating a BufferedReader and DataOutputStream
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            // Read the message from the client
            String message = in.readLine();
            System.out.println("Client's message: " + message);

            // Create a message object
            Message msg = new Message(1, "Hey there", "Server", "sent from the server");

            // Serialize the message
            byte[] data = Composers.serialize(msg);
            int messageLength = data.length;

            // First, send the length of the serialized message
            out.writeInt(messageLength);
            // Then, send the serialized message
            out.write(data);

            // Show the message on the server side
            System.out.println("Message sent to the client: " + msg.toString());

            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error while connecting: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
