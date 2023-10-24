package org.example;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

/**
Creation of thread pool and assigning them to a future task? will happen in @setup
Then in each method it will have the look of threads either read or writer/use the data for both data structures
        The results of the mebdhmarking is the comparison between the efficiency of them
 */

@Threads(14)//Constants.numProcessorsHardcoded)//should be runttime.getruntime.getnumAvailProcessors, rn we'll assign it to 8(my # of cores)
@OutputTimeUnit(TimeUnit.SECONDS)
public class SimulatingReadAndWritesOfThreads {

    //@State(Scope.Thread)//each thread will get an instance of these values, we use threads = # of cores available
     @State(Scope.Benchmark)
    //maybe create a statc subclass that holds a few random NameandDOB objects for reading?
    public static class SomeDataToBeRead{
        public static LibraryConcurrentHashMap libCCMP;
        public static MyConcurrentHashMap myCCMP;

        public static Logger logger;
        public static FileHandler fileHandler;

        @Setup(Level.Iteration)
        public void doSetup() throws IOException {
            libCCMP = new LibraryConcurrentHashMap();
            myCCMP = new MyConcurrentHashMap();
            logger = Logger.getLogger("MyLogger");
            fileHandler = new FileHandler("myLog.log");
            logger.addHandler(fileHandler);
            logger.setLevel(java.util.logging.Level.ALL);
            // Log a simple INFO message.
            logger.info("Logger Initialized");
            libCCMP.setup();
            //myCCMP.setup();
            System.out.println("Setup done");
        }

        @TearDown(Level.Iteration)
        public void doTearDown() {
            System.out.println("teardown done");
        }

        public static NameAndDOB getRandomTestData(){
            return Constants.nameAndDOBArray[ThreadLocalRandom.current().nextInt(Constants.nameAndDOBArray.length-1)];
        }
        public static MedicalRecord getRandomMedicalRecord(){
           return MedicalRecord.generateRandomMedicalRecord();
        }

    }


    public ArrayList<MedicalRecord> readers(){

         return new ArrayList<>();
    }

     //@Benchmark @BenchmarkMode(Mode.Throughput)
     @Benchmark @BenchmarkMode(Mode.Throughput)
    //we'd never benchmark this but its a test
     //this will be a high read/low writer cchsmp
    public void simulateCCHSMPReads(SomeDataToBeRead setupClass, Blackhole blackhole){


         boolean isWriter = false;
         if(ThreadLocalRandom.current().nextInt(100) < Constants.lowPercentageWriters){
             isWriter = true;
         }
         if(isWriter){

             NameAndDOB nameAndDOB = setupClass.getRandomTestData();
             MedicalRecord medicalRecord = setupClass.getRandomMedicalRecord();
             Boolean blah = setupClass.libCCMP.write(nameAndDOB, medicalRecord);

             blackhole.consume(blah);//this is the doing something with the data
             //the doing something with the data can be synthesizing a report of all of the nurses that
             //have seen this patient and then summarizing a report of the patient, including graphs potentially
             //based on weight dirstubition/height over time, etc

             //maybe log in a logging file that a record has been filed
               //setupClass.logger.log(java.util.logging.Level.FINE,  "Thread: " + Thread.currentThread().threadId() + " has written a new record to the shared data store");
         }
         else{
             NameAndDOB nameAndDOB = setupClass.getRandomTestData();
             ArrayList<MedicalRecord> temp =  setupClass.libCCMP.read(nameAndDOB);
              //setupClass.logger.log(java.util.logging.Level.FINE,  "Thread: " + Thread.currentThread().threadId() + " has requested and read the files of patient: " + nameAndDOB.patientName());
             blackhole.consume(temp);
         }

    }


}
