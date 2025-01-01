package com.himanshu.coding.aug09;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPOC {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        int totalThread=3;
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i=1;i<=totalThread;i++) {
            threads.add(new Thread(new NumberPOC(atomicInteger, i, totalThread)));
        }

        for (Thread thread:threads) {
            thread.start();
        }

        Thread.sleep(5500);
        for (Thread thread:threads) {
            thread.interrupt();
        }
    }
}

class NumberPOC implements Runnable {
    final AtomicInteger number;
    int threadId;
    int numthreads;

    public NumberPOC(AtomicInteger number, int id, int numThreads) {
        this.number = number;
        this.threadId = id;
        this.numthreads = numThreads;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (number) {
                if ((number.intValue() % 10 == 0)) {
                    if (threadId==3) {
                        printAndIncrement(threadId,number);
                    }
                } else if(((number.intValue()%2)==0)) {
                    if (threadId==1) {
                        printAndIncrement(threadId,number);
                    }
                } else {
                    if (threadId==2) {
                        printAndIncrement(threadId,number);
                    }
                }
            }
        }
    }

    void printAndIncrement(int threadId, AtomicInteger number) {
        System.out.println(threadId+":\t"+number.get());
        number.incrementAndGet();
    }
}