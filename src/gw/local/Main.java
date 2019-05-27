package gw.local;

import gw.rmi.Interfaces.IRemoteSemaphore;
import gw.rmi.Interfaces.IRemoteSemaphoreFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 2001);
            IRemoteSemaphoreFactory remoteSemaphoreFactory =  (IRemoteSemaphoreFactory) registry.lookup("SemaphoreFactory");

            int[] maxPermits = new int[]{3};
            for (int i = 0; i < maxPermits.length; i++) {
                remoteSemaphoreFactory.CreateSemaphore((long)i, maxPermits[i]);
            }

            Random r = new Random();
            while (true){
                int semaphoreId = r.nextInt(maxPermits.length);
                int permits = 1 + r.nextInt(maxPermits[semaphoreId]);
                IRemoteSemaphore semaphore = remoteSemaphoreFactory.GetSemaphore((long)semaphoreId);

                semaphore.acquire(permits);
                System.out.println( "Acquired: " + permits + " Semaphore: " + semaphoreId);

                Thread.sleep(2 * 1000);

                semaphore.release(permits);
                System.out.println( "Released: " + permits + " Semaphore: " + semaphoreId);

                Thread.sleep(r.nextInt(3) * 1000);
            }

        } catch (RemoteException | NotBoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
