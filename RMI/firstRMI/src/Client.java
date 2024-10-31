import schema.CalculatorInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) {
        // Looking for the RMI Registry
        try {
            // Use the correct name as defined in the server: "Calculator"
            CalculatorInterface c = (CalculatorInterface) Naming.lookup("rmi://localhost/Calculator");

            // Now you can call the methods
            double total = c.addition(12, 12);
            System.out.println("Total is : " + total);

        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (RemoteException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
