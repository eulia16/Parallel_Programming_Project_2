package org.example;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public record MedicalRecord(Severity severity, int nurseID , int height, int weight , String testPerformed, String prevMedicalHistory ) {
     public MedicalRecord{
         Objects.requireNonNull(severity);
         Objects.requireNonNull(nurseID);

    }

    public static MedicalRecord generateRandomMedicalRecord(){
         return new MedicalRecord(
                 Severity.severities[ThreadLocalRandom.current().nextInt(Severity.values().length)],
                 ThreadLocalRandom.current().nextInt(50),
                 ThreadLocalRandom.current().nextInt(30, 250),//in centimeters
                 ThreadLocalRandom.current().nextInt(0, 400), // in pounds
                 Constants.getRandomTestsPerformed[ThreadLocalRandom.current().nextInt(0, Constants.getRandomTestsPerformed.length)],
                 Constants.getRandomMedicalHistory[ThreadLocalRandom.current().nextInt(0, Constants.getRandomMedicalHistory.length)]
         );

    }




}
