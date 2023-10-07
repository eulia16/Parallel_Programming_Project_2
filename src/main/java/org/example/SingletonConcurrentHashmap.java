package org.example;

import java.util.concurrent.ConcurrentHashMap;

public enum SingletonConcurrentHashmap {
    INSTANCE;
    private ConcurrentHashMap<Integer, Room> concurrentHashMap;

    SingletonConcurrentHashmap(){
        this.concurrentHashMap = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap getInstance(){
        return this.concurrentHashMap;

    }


}
