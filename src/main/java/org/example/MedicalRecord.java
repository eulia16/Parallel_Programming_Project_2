package org.example;

public record MedicalRecord(Severity severity, int nurseID ,int height, int weight , String[] testPerformed, String prevMedicalHistory ) {
}
