package com.himanshu.coding.march19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPOC {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Enter the value\n");
        int n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        AtomicInteger atomicInteger = new AtomicInteger(0);
        List<Thread> list = new ArrayList<>();

        for (int i=0;i<n;i++) {
            list.add(new Thread(new ThreadPOC1(i,n,atomicInteger)));
        }

        for (int i=0;i<n;i++) {
            list.get(i).start();
        }
        Thread.sleep(1000);

        for (int i=0;i<n;i++) {
            list.get(i).interrupt();
        }
    }

    private static class ThreadPOC1 implements Runnable {
        private int threadId;
        private int totalThread;
        private volatile AtomicInteger atomicInteger;

        public ThreadPOC1(int threadId, int totalThread, AtomicInteger atomicInteger) {
            this.threadId = threadId;
            this.totalThread = totalThread;
            this.atomicInteger = atomicInteger;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if ((atomicInteger.intValue()%totalThread) == threadId) {
                    synchronized (atomicInteger) {
                        if ((atomicInteger.intValue()%totalThread) == threadId) {
                            System.out.println(threadId+"\t"+atomicInteger.getAndAdd(1));
                        }
                    }
                }
            }
        }
    }
}
