package org.example;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class LibraryConcurrentHashMap implements Runnable{

    @Param({"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"})
    private static Integer iteratorKey;


    @Setup
    public void setup(){
        //initialize the ccMap with some data
        // Generate data and put it into the ConcurrentHashMap
        for (int i = iteratorKey; i <= Constants.NUM_RANDOM_DATA; i++) {
            Room room = new Room(i);
            Constants.ccMap.put(i, room);
        }

        //then create and submit some read operations for the threads to handle
        for(int i=0; i<Constants.NUM_THREADS; ++i) {
            //Constants.clients.submit();
        }



    }

    public static Room read(Integer key){
        return Constants.ccMap.get(key);
    }

    public static void write(Integer key, Room value){ Constants.ccMap.put(key, value);}


    @Override
    public void run(){



    }


}
