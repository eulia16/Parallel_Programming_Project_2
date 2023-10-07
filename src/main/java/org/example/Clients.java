package org.example;

import java.util.concurrent.ThreadLocalRandom;

public class Clients implements Runnable{


    private boolean clientType=false;//false = reader, true = writer

    //will write to the data structure every so often
    private void performWriterTasks() throws InterruptedException {
        //this.wait(Constants.WRITER_WAIT_TIME);
        System.out.println("Inside Writer Method");

    }

    //will read data from the data structure every so often
    private void performReaderTasks() throws InterruptedException {
        Thread.sleep(Constants.WRITER_WAIT_TIME * 100);
        System.out.println("Inside Reader Method");

    }


    @Override
    public void run() {
        if(ThreadLocalRandom.current().nextInt(100) < Constants.PERCENTAGE_OF_WRITER_THREADS)
            clientType = true;
        if(clientType) {
            try {
                performWriterTasks();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                performReaderTasks();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
