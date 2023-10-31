package org.example;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

import static org.example.LibraryConcurrentHashMap.obtainReport;


//@Threads(8)
@OutputTimeUnit(TimeUnit.SECONDS)
public class SimulatingReadAndWritesOfThreads {

    @State(Scope.Benchmark)
    //maybe create a statc subclass that holds a few random NameandDOB objects for reading?
    public static class SomeDataToBeRead {
        public static LibraryConcurrentHashMap libCCMP;
        public static ReadWriteLockHashMap RWHashMap;
        public static ReadWriteLock lock;

        public static Logger logger;
        public static FileHandler fileHandler;

        @Setup(Level.Iteration)
        public void doSetup() throws IOException, InterruptedException {
            libCCMP = new LibraryConcurrentHashMap();
            RWHashMap = new ReadWriteLockHashMap();
            lock = new ReadWriteLock();
            //logger = Logger.getLogger("MyLogger");
            //fileHandler = new FileHandler("myLog.log");
            //logger.addHandler(fileHandler);
            //logger.setLevel(java.util.logging.Level.ALL);
            // Log a simple INFO message.
            //logger.info("Logger Initialized");
            libCCMP.setup();
            System.out.println("Setup done");
        }

        @TearDown(Level.Iteration)
        public void doTearDown() {
            System.out.println("teardown done");
        }

        public static NameAndDOB getRandomTestData() {
            return Constants.nameAndDOBArray[ThreadLocalRandom.current().nextInt(Constants.nameAndDOBArray.length - 1)];
        }

        public static MedicalRecord getRandomMedicalRecord() {
            return MedicalRecord.generateRandomMedicalRecord();
        }

    }

    /**
     * Concurrent HashMap benchmarking methods
     */

    //High read low write testing of concurrent Hashmap
    //@Benchmark @BenchmarkMode(Mode.Throughput)
//    public void simulateCCHSMPLowWritersHighReaders(SomeDataToBeRead setupClass, Blackhole blackhole) {
//        //attempt a read, locking mechanisms are performed inside each function
//
//        NameAndDOB randomNAndDOB = Constants.getRandomTestData();
//        boolean isReader = false;
//        isReader = ThreadLocalRandom.current().nextInt(100) < Constants.highPercentageOfReaders;
//
//        if (isReader) {
//            ArrayList<MedicalRecord> temp = setupClass.libCCMP.readFromMap(randomNAndDOB);
//            obtainReport((ArrayList<MedicalRecord>) temp.clone(), randomNAndDOB);  //this will be the 'do something', it will sort reports on a sliding scale
//            blackhole.consume(temp);
//        }
//
//        boolean isWriter = false;
//        isWriter = ThreadLocalRandom.current().nextInt(100) < Constants.lowPercentageWriters;
//
//        if (isWriter) {
//            setupClass.libCCMP.writeToMap(Constants.getRandomTestData(), Constants.getRandomMedicalRecord());
//        }
//
//    }

    //High write low read testing of concurrent Hashmap
     //@Benchmark @BenchmarkMode(Mode.Throughput)
//    public void simulateCCHSMPHighWritersLowReaders(SomeDataToBeRead setupClass, Blackhole blackhole) {
//        //attempt a read, locking mechanisms are performed inside each function
//
//        NameAndDOB randomNAndDOB = Constants.getRandomTestData();
//        boolean isReader = false;
//        isReader = ThreadLocalRandom.current().nextInt(100) < Constants.lowPercentageOfReaders;
//
//
//        if (isReader) {
//            ArrayList<MedicalRecord> temp = setupClass.libCCMP.readFromMap(randomNAndDOB);
//            obtainReport((ArrayList<MedicalRecord>) temp.clone(), randomNAndDOB);  //this will be the 'do something', it will sort reports on a sliding scale
//        }
//
//        boolean isWriter = false;
//        isWriter = ThreadLocalRandom.current().nextInt(100) < Constants.highPercentageWriters;
//
//        if (isWriter) {
//            setupClass.libCCMP.writeToMap(Constants.getRandomTestData(), Constants.getRandomMedicalRecord());
//
//        }
//
//    }

    /**
     * Read Write Locking HashMap benchmarking methods
     */

    //High read low write testing of read write lock
     @Benchmark @BenchmarkMode(Mode.Throughput)@Threads(100)
     public void simulateReadWriteLockLowWritersHighReaders(SomeDataToBeRead setupClass, Blackhole blackhole) {

        //attempt a read, locking mechanisms are performed inside each function
        NameAndDOB randomNAndDOB = Constants.getRandomTestData();
        boolean isReader = true;

        if (isReader){
            SomeDataToBeRead.lock.lockRead();
            try {
                ArrayList<MedicalRecord> copy = SomeDataToBeRead.RWHashMap.getMedicalData(randomNAndDOB);

                //instead of obtain report, we'll just compute the number of people inside the datastructure


                System.out.println("Reading from hashmap");

                ArrayList<MedicalRecord> temp = new ArrayList<>(setupClass.RWHashMap.getMedicalData(randomNAndDOB));
                obtainReport(temp, randomNAndDOB);  //this will be the 'do something', it will sort reports on a sliding scale
            }
            finally {
                setupClass.lock.unlockRead();
            }
        }

        boolean isWriter;
        isWriter =  ThreadLocalRandom.current().nextInt(100) < 50;
        try{
            if(isWriter) {
                setupClass.lock.lockWrite();
                try {
                    final NameAndDOB randomPerson = Constants.getRandomTestData();
                    final MedicalRecord randomMedicalRecord = Constants.getRandomMedicalRecord();
                    setupClass.RWHashMap.writeToMap(randomPerson, randomMedicalRecord);
                      System.out.println("");
                }
                catch(Exception e){

                }
            }
        }
        finally{
            setupClass.lock.unlockWrite();
        }

    }


    //High write low read testing of read write lock
    //@Benchmark @BenchmarkMode(Mode.Throughput)
//    public void simulateReadWriteLockHighWritersLowReaders(SomeDataToBeRead setupClass, Blackhole blackhole) {
//        //setupClass.RWHashMap.performTask(Constants.lowPercentageOfReaders, Constants.highPercentageWriters);
//    }


}



