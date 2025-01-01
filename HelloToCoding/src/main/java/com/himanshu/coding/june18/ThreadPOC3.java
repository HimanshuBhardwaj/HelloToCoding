package com.himanshu.coding.june18;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPOC3 {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger value = new AtomicInteger(0);
        int numThread=15;
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i=0;i<numThread;i++) {
            Thread2 thread2 = new Thread2(value, i, numThread);
            threads.add(new Thread(thread2));
        }

        for (int i=0;i<numThread;i++) {
            threads.get(i).start();
        }

        Thread.sleep(5000);

        for (int i=0;i<numThread;i++) {
            threads.get(i).interrupt();
        }
    }
}

class Thread2 implements Runnable {
    AtomicInteger value;
    int threadId;
    int totalNumThreads;

    public Thread2(AtomicInteger value, int threadId, int totalNumThreads) {
        this.value = value;
        this.threadId=threadId;
        this.totalNumThreads=totalNumThreads;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if ((value.intValue()%totalNumThreads) == threadId) {
                synchronized (value) {
                    System.out.println("Value: "+value.intValue()+" Thread Id: "+
                            threadId+" Total threads"+totalNumThreads);
                    value.incrementAndGet();
                }
            }
        }
    }
}
