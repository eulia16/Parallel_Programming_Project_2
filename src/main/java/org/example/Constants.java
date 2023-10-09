package org.example;

public class Constants {

    public static final int NUM_THREADS = 20;
    public static final int NUM_RANDOM_DATA = 10;
    public static final int PERCENTAGE_OF_WRITER_THREADS = 5;//5 percent will be writer threads
    public static final long WRITER_WAIT_TIME = 1L;//in terms of milliseconds
    public static final int numProcessors = Runtime.getRuntime().availableProcessors();
    public static final long READER_WAIT_TIME= 0L;
    public static final int READER_WAIT_TIME_NANOSECONDS = 100_000;

}
