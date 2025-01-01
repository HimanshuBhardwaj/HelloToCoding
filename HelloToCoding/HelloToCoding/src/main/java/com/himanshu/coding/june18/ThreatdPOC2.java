package com.himanshu.coding.june18;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreatdPOC2 {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);
        ArrayList<Thread1> arrayList = new ArrayList<>();
        int numThread=15;

        for (int i=0;i<numThread;i++) {
            Thread1 thread = new Thread1(integer, i, numThread);
                arrayList.add(thread);
        }

        for (int i=0;i<numThread;i++) {
            arrayList.get(i).start();
        }
        Thread.sleep(5000);

        for (int i=0;i<numThread;i++) {
            arrayList.get(i).interrupt();
        }
    }
}

class Thread1 extends Thread {
    AtomicInteger value;
    int threadId;
    int numThread;

    public Thread1(AtomicInteger atomicInteger, int threadId, int numThread) {
        this.value = atomicInteger;
        this.threadId = threadId;
        this.numThread = numThread;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if ( (value.intValue()%numThread) == threadId ) {
                synchronized (value) {
                    System.out.println("Value: "+value.intValue()+", ThreadId: "+threadId+" Numthread: "+numThread+" Remainder"+(value.intValue()%numThread-threadId) );
                    if ( (value.intValue()%numThread) == threadId ) {
                     value.incrementAndGet();
                    }
                }
            }

            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Thread "+threadId+" is interrupted");
            }
        }
    }
}
