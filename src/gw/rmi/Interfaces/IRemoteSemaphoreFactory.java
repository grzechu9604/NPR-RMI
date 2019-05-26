package gw.rmi.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteSemaphoreFactory extends Remote {
    IRemoteSemaphore GetSemaphore(Long id) throws RemoteException;
    boolean CreateSemaphore(Long id, int max_permits) throws RemoteException;
}
