package org.example;

import java.util.Objects;

public record MedicalRecord(Severity severity, int nurseID , int height, int weight , String[] testPerformed, String prevMedicalHistory ) {
     public MedicalRecord{
         Objects.requireNonNull(severity);
         Objects.requireNonNull(nurseID);

    }

}
