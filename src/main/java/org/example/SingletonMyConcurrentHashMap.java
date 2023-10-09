package org.example;


import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public enum SingletonMyConcurrentHashMap {
    INSTANCE;
    private MyConcurrentHashMap<NameAndDOB, ArrayList<MedicalRecord>> concurrentHashMap;

    SingletonMyConcurrentHashMap(){
        this.concurrentHashMap = new MyConcurrentHashMap<>();
    }

    public MyConcurrentHashMap getInstance(){
        return this.concurrentHashMap;

    }


}