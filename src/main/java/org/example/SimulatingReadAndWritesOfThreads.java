package org.example;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
Creation of thread pool and assigning them to a future task? will happen in @setup
Then in each method it will have the look of threads either read or writer/use the data for both data structures
        The results of the mebdhmarking is the comparison between the efficiency of them
 */

@BenchmarkMode(Mode.Throughput)
@Threads(Constants.numProcessorsHardcoded)//should be runttime.getruntime.getnumAvailProcessors, rn we'll assign it to 8(my # of cores)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SimulatingReadAndWritesOfThreads {

    //maybe create a statc subclass that holds a few random NameandDOB objects for reading?
    private static class someData{


    }

    @Benchmark
    //we'd never benchmark this but its a test
    public void simulateCCHSMPReads(){
        LibraryConcurrentHashMap s = new LibraryConcurrentHashMap();
        s.setup();

        LibraryConcurrentHashMap.read()
        System.out.println(Constants.ccMap);
    }

    public void simulateCCHSMPWrites(){

    }

    public void simulateMyCCHSMPReads(){

    }

    public void simulateMyCCHSMPWrites(){

    }


}
