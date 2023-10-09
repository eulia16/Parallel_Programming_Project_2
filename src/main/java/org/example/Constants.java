package org.example;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Constants {

    public static final int NUM_THREADS = 20;
    public static final int DEFAULT_HASHMAP_SIZE = 16;
    public static final int NUM_RANDOM_DATA = 10;
      //public static final ExecutorService clients =  Executors.newFixedThreadPool(Constants.NUM_THREADS);
    public static ConcurrentHashMap<Integer, Room> ccMap = SingletonConcurrentHashmap.INSTANCE.getInstance();


    public static final int PERCENTAGE_OF_WRITER_THREADS = 5;//5 percent will be writer threads
    public static final long WRITER_WAIT_TIME = 1L;//in terms of milliseconds
    public static final int numProcessors = Runtime.getRuntime().availableProcessors();
    public static final int numProcessorsHardcoded = 8;
    public static final long READER_WAIT_TIME= 0L;
    public static final int READER_WAIT_TIME_NANOSECONDS = 100_000;

}
