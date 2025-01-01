package Y2023.aug8;

import java.util.ArrayList;

public class WaitNotify {
    public static void main(String[] args) throws InterruptedException {
 //       Elemant elemant = new Elemant();
        ArrayList<Thread> threads = new ArrayList<>();

        int n=50;
//        for (int i=0;i<n;i++) {
//            threads.add(new Thread(new OperationTh(elemant, i, n)));
//        }
//
//        for (int i=0;i<n;i++) {
//            threads.get(i).start();
//        }
//
//        Thread.sleep(5);
//
//        for (int i=0;i<n;i++) {
//            threads.get(i).interrupt();
//        }


        Number elemant = new Number();
        threads = new ArrayList<>();

        n=5000;
        for (int i=0;i<n;i++) {
            threads.add(new Thread(new WaiterNotify( n, i, elemant)));
        }

        for (int i=0;i<n;i++) {
            threads.get(i).start();
        }

        Thread.sleep(5000);

        for (int i=0;i<n;i++) {
            threads.get(i).interrupt();
        }

    }
}

class Elemant {
    int number = 0;

    void increse() {
        this.number++;
    }
}

class WaiterNotify implements Runnable {
    private int threadIndex;
    private int numThread;
    private volatile Number number;


    public WaiterNotify(int n, int index, Number number) {
        this.threadIndex = index;
        this.numThread = n;
        this.number = number;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                synchronized (number) {
                    if (number.getNum() % numThread != threadIndex) {
                        number.wait();
                    }
                    if (number.getNum() % numThread == threadIndex) {
                        System.out.println(this.threadIndex + "\t" + Thread.currentThread().getId() + "\t" + number.getNum());
                        number.increment();
                        number.notifyAll();
//                    number.notify();
                    } else {
                        number.wait();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}



class OperationTh implements Runnable {
    public OperationTh(Elemant elemant, int index, int numThread) {
        this.elemant = elemant;
        this.index = index;
        this.numThread = numThread;
    }

    volatile Elemant elemant;
    int index;
    int numThread;


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (elemant.number % numThread == index) {
                synchronized (elemant) {
                    if (elemant.number % numThread == index) {
                        System.out.println(elemant.number+" ");
                     elemant.increse();
                    }
                }
            }
        }
    }
}


class Number {
    private volatile int num;

    public Number() {
        this.num = 0;
    }

    void increment() {
        //increment is not atomic
        num++;
    }

    public int getNum() {
        return num;
    }
}
