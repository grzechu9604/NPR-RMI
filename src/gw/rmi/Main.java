package gw.rmi;

import gw.rmi.RemoteObjects.RemoteSemaphoreFactory;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

    public static void main(String[] args) {

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "SemaphoreFactory";
            RemoteSemaphoreFactory factory = new RemoteSemaphoreFactory();
            Registry registry = LocateRegistry
                    .createRegistry(2001);
            registry.rebind(name, factory);
            System.out.println("SemaphoreFactory bound");
        } catch (Exception e) {
            System.err.println("SemaphoreFactory exception:");
            e.printStackTrace();
        }
    }
}
