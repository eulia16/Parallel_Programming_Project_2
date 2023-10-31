package org.example;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;



public class LibraryConcurrentHashMap{

    @Setup
    public void setup() throws InterruptedException {
        //initialize the ccMap with some data
        // Generate data and put it into the ConcurrentHashMap
        int counter=0;
        for(int i=0; i<10; ++i){
            String name = Constants.listOfNames[i];
            for(int j=0; j<10; ++j){
                MedicalRecord record = MedicalRecord.generateRandomMedicalRecord();
                String DOB = Constants.listOfDOBs[j];
                NameAndDOB nameAndDOB = new NameAndDOB(name, DOB);
                Constants.nameAndDOBArray[counter] = nameAndDOB;
                //same but add them to the hashmap now
                Constants.ccMap.put(nameAndDOB, new ArrayList<>());
                Constants.ccMap.get(nameAndDOB).add(record);
                counter++;
            }
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







