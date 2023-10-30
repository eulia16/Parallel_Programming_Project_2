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

    public static ArrayList<MedicalRecord> readFromMap(NameAndDOB nameAndDOB){
        return Constants.ccMap.get(nameAndDOB);
    }

    public void writeToMap(NameAndDOB nameAndDOB, MedicalRecord medicalRecord){


        //memory safekeeping
        if(Constants.ccMap.get(nameAndDOB).size() > Constants.MAX_NUM_RECORDS){
            Constants.ccMap.get(nameAndDOB).clear();
        }

        if(Constants.ccMap.containsKey(nameAndDOB)){
            Constants.ccMap.get(nameAndDOB).add(medicalRecord);
        }
        else{
            Constants.ccMap.put(nameAndDOB, new ArrayList<>());
            Constants.ccMap.get(nameAndDOB).add(medicalRecord);
        }

    }

    public static void obtainReport(ArrayList<MedicalRecord> records, NameAndDOB name){
        Severity severity = Constants.getIndividualsRiskLevel(records);
        System.out.println();
        System.out.println("This is a report generated regarding the information pertitent to patient: "
                + name.patientName() + " and this patients dats of birth: " + name.DOB());
        System.out.println("This patient is a " + severity.name() + " risk ");
        System.out.println("Cases this patient has had in the past are listed from most to least severe: ");
        Constants.getMostSevereCases(records);
        System.out.println("the patients average weight and height over the course of reports we've obtained is: "
                + Constants.getAverageWeight(records) + "lbs, " + Constants.getAverageHeight(records) + " cm");
        System.out.println("For more specific information and more detailed information, you must go through" +
                " the files yourself");
        System.out.println();

    }


    }







