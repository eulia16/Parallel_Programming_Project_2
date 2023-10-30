package org.example;

import javax.crypto.Cipher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class ReadWriteLockHashMap {
    //to limit the size of the data, we will max the number of entries per person to 20, and
    //when that limit is exceeded, we will append the data to the front of the list storing the data, just
    //because otherwise when JMH tests it, it will have wayyyy too much data and overload my system
    private HashMap<NameAndDOB, ArrayList<MedicalRecord>> hashMap;
    private ReadWriteLock lock = new ReadWriteLock();

    //on instantiation, create some dummy values in the hashmap
    ReadWriteLockHashMap(){
        this.hashMap = fillWithRandomData();
        //printDataPretty(this.hashMap);

    }

    //will read a persons data(their records)
    public ArrayList<MedicalRecord> getMedicalData(NameAndDOB nameAndDOB){
        return this.hashMap.get(nameAndDOB);
    }



    //will write a new medical record to a person, or create a new person and medical record if they arent in
    //the system
    public void writeToMap(NameAndDOB nameAndDOB, MedicalRecord medicalRecord){


        //memory safekeeping
        if(this.hashMap.get(nameAndDOB).size() > Constants.MAX_NUM_RECORDS){
            this.hashMap.get(nameAndDOB).clear();
        }

        if(this.hashMap.containsKey(nameAndDOB)){

            this.hashMap.get(nameAndDOB).add(medicalRecord);

        }
        else{
            this.hashMap.put(nameAndDOB, new ArrayList<>());
            this.hashMap.get(nameAndDOB).add(medicalRecord);
        }

    }

    public void performTask(int percentageOfReaders, int percentageOfWriters){
        //attempt a read, locking mechanisms are performed inside each function
        NameAndDOB randomNAndDOB = Constants.getRandomTestData();
        boolean isReader = false;
        isReader =  ThreadLocalRandom.current().nextInt(100) < percentageOfReaders;


        if (isReader){
            lock.lockRead();
            try {
                System.out.println("************Attempting to READ some medical records****************");
                ArrayList<MedicalRecord> temp = getMedicalData(randomNAndDOB);
                if(temp != null)
                    obtainReport(temp, randomNAndDOB);  //this will be the 'do something', it will sort reports on a sliding scale
            }
            finally {
                lock.unlockRead();
            }

        }




        boolean isWriter = false;
        isWriter =  ThreadLocalRandom.current().nextInt(100) < percentageOfWriters;
        try{
            if(isWriter) {
                lock.lockWrite();
                try {
                    System.out.println("************WRITING SOME DATA****************");
                    writeToMap(Constants.getRandomTestData(), Constants.getRandomMedicalRecord());
                }
                catch(Exception e){

                }
            }
        }
        finally{
            lock.unlockWrite();
        }


    }



    private HashMap<NameAndDOB, ArrayList<MedicalRecord>> fillWithRandomData(){
        HashMap<NameAndDOB, ArrayList<MedicalRecord>> tempMap = new HashMap<>();

        for (int i = 0; i < Constants.NUM_RANDOM_DATA; i++) {
            MedicalRecord record = MedicalRecord.generateRandomMedicalRecord();
            int randomName =0, randomDOB=0;

            randomName = ThreadLocalRandom.current().nextInt(Constants.listOfNames.length);
            randomDOB = ThreadLocalRandom.current().nextInt(Constants.listOfDOBs.length);

            NameAndDOB temp = new NameAndDOB(Constants.listOfNames[randomName], Constants.listOfDOBs[randomDOB]);
            //put the randomly generated humans in the list
            Constants.nameAndDOBArray[i] = temp;
            //same but add them to the hashmap now
            if(tempMap.containsKey(temp)){
                tempMap.get(temp).add(record);
            }
            else {
                tempMap.put(temp, new ArrayList<>());
                tempMap.get(temp).add(record);
            }
        }

        return tempMap;
    }


    public static void printDataPretty(HashMap<NameAndDOB, ArrayList<MedicalRecord>> hashMap){
        int numNameDOB=0, numMedRecords=0;
        for(NameAndDOB n: hashMap.keySet()){
            numNameDOB++;
            System.out.println("Name and DOB: " + n);
            for(MedicalRecord m : hashMap.get(n)){
                numMedRecords++;
                System.out.println("Medical Record: " + m.toString());
            }
        }

        System.out.println("number of name and DOB: " + numNameDOB + ", num records" + numMedRecords);
    }

    //this is the 'do something', it will generate a report and return some pertinent information
    //to the requester
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
