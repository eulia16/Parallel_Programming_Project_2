package org.example;

/**
 * Hello world!
 *
 */

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.*;


public class App 
{

    //read chapter 6-7-8 to ensure this is the type of thread pool we want
    static ExecutorService clients =  Executors.newFixedThreadPool(Constants.NUM_THREADS);


    //lets toss in error prone and JMH for this project
    public static void main( String[] args ) throws RunnerException, IOException {

        //handle user input shit
//        if(args[0].equalsIgnoreCase("-C")) {
//            activeUserIdsToRoomCCMP = SingletonConcurrentHashmap.INSTANCE.getInstance();
//            //activeUserIdsToRoomCCMP.
//            System.out.println("You created a concurrent hashmap");
//        }
//        else if(args[0].equalsIgnoreCase("-U")) {
//            activeUserIdsToRoomMyCCMP = SingletonMyConcurrentHashMap.INSTANCE.getInstance();
//            System.out.println("You created My concurrent hashmap, lets see how good it can perform");
//
//        }
//        else{
//            System.out.println("You need to define what type of data structure you would like to use");
//            System.exit(0);
//        }

        org.openjdk.jmh.Main.main(args);
        //perform the many reads/writes on the data structures and compare the JMH findings

    }

}
