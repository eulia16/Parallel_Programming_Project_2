package org.example;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class LibraryConcurrentHashMap{

    @Param({"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"})
    private static Integer iteratorKey;


    @Setup
    public void setup(){
        //initialize the ccMap with some data
        // Generate data and put it into the ConcurrentHashMap
        for (int i = iteratorKey; i <= Constants.NUM_RANDOM_DATA; i++) {
            MedicalRecord record = new MedicalRecord( Severity.severities[ThreadLocalRandom.current().nextInt(Severity.values().length)],  1,-1,-1,null, null);
            NameAndDOB temp = new NameAndDOB("John Hancock", "11/08/2001");
            Constants.ccMap.put(temp, new ArrayList<>());
            Constants.ccMap.get(temp).add(record);
        }

    }

    public static ArrayList<MedicalRecord> read(Integer key){
        return Constants.ccMap.get(key);
    }

    public static void write(NameAndDOB key, MedicalRecord value){
        if(Constants.ccMap.containsKey(key))
            Constants.ccMap.get(key).add(value);
        else{
            ArrayList<MedicalRecord> temp = new ArrayList();
            Constants.ccMap.put(key, temp);
            Constants.ccMap.get(key).add(value);
        }

        }

}
