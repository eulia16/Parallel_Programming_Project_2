package org.example;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;



public class LibraryConcurrentHashMap{

    @Setup
    public void setup(){
        //initialize the ccMap with some data
        // Generate data and put it into the ConcurrentHashMap
        for (int i = 0; i < Constants.NUM_RANDOM_DATA; i++) {
            MedicalRecord record = MedicalRecord.generateRandomMedicalRecord();
            int randomName =0, randomDOB=0;

            randomName = ThreadLocalRandom.current().nextInt(Constants.listOfNames.length);
            randomDOB = ThreadLocalRandom.current().nextInt(Constants.listOfDOBs.length);

            NameAndDOB temp = new NameAndDOB(Constants.listOfNames[randomName], Constants.listOfDOBs[randomDOB]);
            //put the randomly generated humans in the list
            Constants.nameAndDOBArray[i] = temp;
            //same but add them to the hashmap now
            Constants.ccMap.put(temp, new ArrayList<>());
            Constants.ccMap.get(temp).add(record);
        }

    }


    public  ArrayList<MedicalRecord> read(NameAndDOB key){
        return Constants.ccMap.get(key);
    }

    public boolean write(NameAndDOB key, MedicalRecord value){



        //makes me run out of memory cause the hashmap gets too big with all the simulated data. we'll just overwrite
        //it instead
        if(Constants.ccMap.get(key).size() > 100){
            Constants.ccMap.get(key).clear();
        }

        if (!Constants.ccMap.containsKey(key)) {
            ArrayList<MedicalRecord> temp = new ArrayList();
            temp.add(value);
            Constants.ccMap.put(key, temp);
        }
        Constants.ccMap.get(key).add(value);

        return true;


        }



}
