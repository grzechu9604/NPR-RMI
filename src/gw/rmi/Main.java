package gw.rmi;

import gw.rmi.Interfaces.IRemoteSemaphoreFactory;
import gw.rmi.RemoteObjects.RemoteSemaphoreFactory;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main {

    public static void main(String[] args) {

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "SemaphoreFactory";
            RemoteSemaphoreFactory factory = new RemoteSemaphoreFactory();
            IRemoteSemaphoreFactory stub =
                    (IRemoteSemaphoreFactory) UnicastRemoteObject.exportObject(factory, 2001);
            System.setProperty("java.rmi.server.hostname","localhost");
            Registry registry = LocateRegistry.getRegistry();
            System.setProperty("java.rmi.server.hostname","localhost");
            registry.rebind(name, stub);
            System.out.println("SemaphoreFactory bound");
        } catch (Exception e) {
            System.err.println("SemaphoreFactory exception:");
            e.printStackTrace();
        }
    }
}
