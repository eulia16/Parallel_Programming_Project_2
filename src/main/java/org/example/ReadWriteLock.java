package org.example;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReadWriteLock {
    private int readers=0, writers=0, waitingReaders=0, waitingWriters=0;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition readable=lock.newCondition();
    private final Condition writable=lock.newCondition();

    public ReadWriteLock(){
    }

    public void lockRead(){
        lock.lock();
        try{
            while(writers !=0 || waitingWriters !=0){
                ++waitingReaders;
                readable.awaitUninterruptibly();;
                --waitingReaders;

            }
            ++readers;

        }
        finally{

            lock.unlock();

        }
    }

    public void lockWrite(){
        lock.lock();
        try{
            while(readers != 0 || writers != 0){
                waitingWriters++;
                writable.awaitUninterruptibly();
                waitingWriters--;
            }
            writers = 1;


        }finally {
            lock.unlock();
        }
    }


    public void unlockRead(){
        lock.lock();

        try{

            if(readers-- == 0) {
                if (waitingWriters != 0) {
                    writable.signal();
                }
                else{
                    readable.signalAll();
                }
            }
        }finally {

            lock.unlock();
        }
    }

    public void unlockWrite(){
        lock.lock();
        try{
            writers = 0;
            if(waitingWriters != 0)
                writable.signal();
            else
                readable.signalAll();
        }finally {
            lock.unlock();
        }
    }

}


