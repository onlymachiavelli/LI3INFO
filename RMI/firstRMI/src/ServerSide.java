import schema.CalculatorInterface;
import schema.CalculatorIterfaceImplementation;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerSide {

    public static void main(String[] args) {
        try {
            // Create the registry on port 1099
            LocateRegistry.createRegistry(1099);

            // Create an instance of the CalculatorInterface implementation
            CalculatorInterface c = new CalculatorIterfaceImplementation();

            // Bind the Calculator object to the registry with the name "Calculator"
            Naming.rebind("rmi://localhost/Calculator", c);

            System.out.println("Server side is ready and bound to 'Calculator'");

        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
