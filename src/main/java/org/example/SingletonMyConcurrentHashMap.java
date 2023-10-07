package org.example;


import java.util.concurrent.ConcurrentHashMap;

public enum SingletonMyConcurrentHashMap {
    INSTANCE;
    private MyConcurrentHashMap<Integer, Room> concurrentHashMap;

    SingletonMyConcurrentHashMap(){
        this.concurrentHashMap = new MyConcurrentHashMap<>();
    }

    public MyConcurrentHashMap getInstance(){
        return this.concurrentHashMap;

    }


}