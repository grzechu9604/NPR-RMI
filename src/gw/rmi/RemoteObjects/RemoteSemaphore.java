package gw.rmi.RemoteObjects;

import gw.rmi.Interfaces.IRemoteSemaphore;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.concurrent.Semaphore;

public class RemoteSemaphore implements IRemoteSemaphore, Serializable {
    private final Semaphore semaphore;

    RemoteSemaphore(int max_permits)
    {
        super();
        semaphore = new Semaphore(max_permits, true);
    }

    @Override
    public void acquire(int permits) throws RemoteException, InterruptedException {
        semaphore.acquire(permits);
    }

    @Override
    public void release(int permits) throws RemoteException {
        semaphore.release(permits);
    }
}
