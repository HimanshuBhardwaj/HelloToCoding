package com.himanshu.coding.june18;

import com.sun.source.tree.WhileLoopTree;

public class ThreadvisThread {
    public static void main(String[] args) {
        ThreadPOC threadPOC[] = new ThreadPOC[10];

        for (int i=0;i<threadPOC.length;i++) {
            threadPOC[i] = new ThreadPOC();
        }

        for (int i=0;i<threadPOC.length;i++) {
            threadPOC[i].start();
        }
    }
}

class  ThreadPOC extends Thread {

    public void run() {
        int count=0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(Thread.currentThread().getId()+": "+10);
            count++;

            if (count>=10) {
                System.out.println("Interuppting current thread: "+this.getThreadGroup()+": "+this.getId());
                Thread.currentThread().interrupt();
            }
        }
    }
}
