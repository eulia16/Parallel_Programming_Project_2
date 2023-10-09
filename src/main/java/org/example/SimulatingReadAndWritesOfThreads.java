package org.example;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Threads;

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



    public void simulateCCHSMPReads(){


    }

    public void simulateCCHSMPWrites(){

    }

    public void simulateMyCCHSMPReads(){

    }

    public void simulateMyCCHSMPWrites(){

    }


}
