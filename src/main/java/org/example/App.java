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
    //lets toss in error prone and JMH for this project
    public static void main( String[] args ) throws RunnerException, IOException {
        org.openjdk.jmh.Main.main(args);
    }

}
