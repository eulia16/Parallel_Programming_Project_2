package org.example;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class LibraryConcurrentHashMap {
    private ConcurrentHashMap<Integer, Room> ccMap;
    @Param({"1", "2"})
    private Integer Key1, Key2;

    private Room room;

    @Setup
    public void setup(){
        this.ccMap = SingletonConcurrentHashmap.INSTANCE.getInstance();
        //initialize the ccMap with some data
        // Generate data and put it into the ConcurrentHashMap
        for (int i = 1; i <= Constants.NUM_RANDOM_DATA; i++) {
            Room room = new Room(i);
            ccMap.put(i, room);
        }

    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public Room read(){
        return this.ccMap.get(Key1);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public Integer write(){
        //this.ccMap.put(ThreadLocalRandom.current().nextInt(Constants.NUM_RANDOM_DATA), new Room(1));
        this.ccMap.put(1, new Room(1));
        return ThreadLocalRandom.current().nextInt(Constants.NUM_THREADS);
    }


}
