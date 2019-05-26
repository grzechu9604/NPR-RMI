package gw.rmi.Interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteSemaphore extends Remote, Serializable {
    void acquire(int permits) throws RemoteException, InterruptedException;
    void release(int permits) throws RemoteException;
}
