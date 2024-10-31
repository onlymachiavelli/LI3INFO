package schema;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorIterfaceImplementation extends UnicastRemoteObject implements CalculatorInterface {

    public CalculatorIterfaceImplementation() throws RemoteException {
        super();
    }

    public double addition(double a, double b) throws RemoteException {
        return a + b;
    }

    public double soustraction(double a, double b) throws RemoteException {
        return a - b;
    }

    public double mult(double a, double b) throws RemoteException {
        return a * b;
    }
}
