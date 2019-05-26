package gw.rmi.RemoteObjects;

import gw.rmi.Interfaces.IRemoteSemaphore;
import gw.rmi.Interfaces.IRemoteSemaphoreFactory;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;

public class RemoteSemaphoreFactory implements IRemoteSemaphoreFactory, Serializable {

    private HashMap<Long, RemoteSemaphore> _semaphores;

    public RemoteSemaphoreFactory() throws RemoteException
    {
        super();
        _semaphores = new HashMap<>();
    }

    @Override
    public IRemoteSemaphore GetSemaphore(Long id) {
        return _semaphores.getOrDefault(id, null);
    }

    @Override
    public synchronized boolean CreateSemaphore(Long id, int max_permits) {
        if (_semaphores.containsKey(id))
        {
            return false;
        }
        else
        {
            RemoteSemaphore semaphore = new RemoteSemaphore(max_permits);
            _semaphores.put(id, semaphore);
            return true;
        }
    }
}
