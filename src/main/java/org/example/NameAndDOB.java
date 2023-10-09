package org.example;

import java.util.Objects;

public record NameAndDOB(String patientName, String DOB) {
    public NameAndDOB{
        Objects.requireNonNull(patientName);
        Objects.requireNonNull(DOB);
    }
}
