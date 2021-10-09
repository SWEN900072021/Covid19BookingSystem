package com.example.covid19bookingsystem.datasource;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ExclusiveLockManager {

    private static ExclusiveLockManager instance;

    private ConcurrentMap<Integer, Integer> lockMap;

    public static synchronized ExclusiveLockManager getInstance() {
        if (instance == null) {
            instance = new ExclusiveLockManager();
        }
        return instance;
    }

    private ExclusiveLockManager() {
        lockMap = new ConcurrentHashMap<Integer, Integer>();
    }

    public Boolean acquireLock(Integer lockable, Integer owner) {
        if(!lockMap.containsKey(lockable)) {
            lockMap.put(lockable, owner);
            return true;
        } else {
            System.out.println("Concurrency exception, " + owner + " could not acquire lock for " + lockable);
            return false;
        }
    }

    public void releaseLock(Integer lockable) {
        lockMap.remove(lockable);
    }

}
