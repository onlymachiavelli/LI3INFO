package utils;



//importing my package message
import Schema.Message;


//importing all the methods from the io package
import java.io.*;


public class Composers {

    //in this file we're going to do the serialization and deserialization of the message object
    //we're going to use the object input and output streams to do so

    public static byte[] serialize(Message message) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(message);

            return baos.toByteArray();


        } catch (IOException e) {
            System.out.println("Error while serializing the message : ");
            e.printStackTrace();
            return null;
        }



    }

    public static Message deserialize(byte[] data) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Message receivedMessage = (Message) ois.readObject();

            return receivedMessage;

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error while deserializing the message : ");

            e.printStackTrace();
            return null;
        }
    }
}
