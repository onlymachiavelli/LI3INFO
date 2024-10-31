package schema;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculatorInterface extends Remote {

    double addition(double a, double b) throws RemoteException;
    double soustraction(double a, double b) throws RemoteException;
    double mult(double a, double b) throws RemoteException;
}