package Y2023.july27;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPOC {
    public static void main(String[] args) throws InterruptedException {
        PrintImpl<AtomicInteger> print = new PrintImpl(10, new AtomicInteger());
        print.start();
    }
}

class PrintImpl<T extends AtomicInteger> {
    List<Thread> threads;
    int numthreads;

    public PrintImpl(int numthreads, T value) {
        this.numthreads = numthreads;
        threads = new ArrayList<>();

        for (int i=0;i<numthreads;i++) {
            threads.add(new Thread(new ThreadOperation(value, i, numthreads)));
        }
    }

    public void start() throws InterruptedException {
        for (int i=0;i<numthreads;i++) {
            threads.get(i).start();
        }

        Thread.sleep(500);

        for (int i=0;i<numthreads;i++) {
            threads.get(i).interrupt();
        }
    }
}

class ThreadOperation<T extends AtomicInteger> implements Runnable {
    public ThreadOperation(T value, int index, int numthreads) {
        this.value = value;
        this.index = index;
        this.numthreads = numthreads;
    }

    T value;
    int index;
    int numthreads;


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (value.get() % numthreads == index) {
                synchronized (ThreadOperation.class) {
                    if (value.get() % numthreads == index) {
                        System.out.println(value.getAndIncrement() + "\t" + index);
                    }
                }
            }
        }
    }
}