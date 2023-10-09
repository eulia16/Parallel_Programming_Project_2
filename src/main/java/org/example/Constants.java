package org.example;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Constants {

    public static final int NUM_THREADS = 20;
    public static final int DEFAULT_HASHMAP_SIZE = 16;
    public static final int NUM_RANDOM_DATA = 10;
      //public static final ExecutorService clients =  Executors.newFixedThreadPool(Constants.NUM_THREADS);
    public static ConcurrentHashMap<NameAndDOB, ArrayList<MedicalRecord>> ccMap = SingletonConcurrentHashmap.INSTANCE.getInstance();


    public static final int PERCENTAGE_OF_WRITER_THREADS = 5;//5 percent will be writer threads
    public static final long WRITER_WAIT_TIME = 1L;//in terms of milliseconds
    public static final int numProcessors = Runtime.getRuntime().availableProcessors();
    public static final int numProcessorsHardcoded = 8;
    public static final long READER_WAIT_TIME= 0L;
    public static final int READER_WAIT_TIME_NANOSECONDS = 100_000;

    public static final String[] listOfNames = {"Emma Johnson", "Liam Smith", "Olivia Davis", "Noah Anderson", "Ava Brown", "Isabella Wilson", "Sophia Martinez", "Mia Taylor", "Charlotte White", "Amelia Harris"};

    public static final String[] listOfDOBs = {"15/04/1990", "23/07/1985", "09/12/2000", "30/05/1978", "18/09/1995", "07/03/1982", "12/11/1999", "26/08/1972", "03/06/1988", "21/10/2005"};




}
