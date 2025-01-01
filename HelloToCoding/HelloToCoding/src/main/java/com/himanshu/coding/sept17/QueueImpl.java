package com.himanshu.coding.sept17;

public class QueueImpl {
    public static void main(String[] args) {
        QueueI queueI = new ArrayQueue();
        queueI.enQueue(10);
        queueI.enQueue(11);


        System.out.println(queueI.dequeue());
        queueI.enQueue(12);
        System.out.println(queueI.dequeue());
        queueI.enQueue(13);
        queueI.enQueue(14);
        System.out.println(queueI.dequeue());


        queueI.enQueue(15);

    }
}


class ArrayQueue implements  QueueI {
    int [] queueDS;
    int MAX_SIZE=3; //assuming this is infinite capacity array
    int start=0;
    int end=-1;

    public ArrayQueue() {
        queueDS=new int[MAX_SIZE];
    }

    @Override
    public void enQueue(int value) {
        if ( (end-start+1)>=MAX_SIZE ) {
            throw  new RuntimeException("Queue size exceeded");
            //this could be proper exception
        }
        queueDS[((++end)%MAX_SIZE) ]=value ; //we will add modulo here later
    }

    @Override
    public int dequeue() {

        if (start>end) {
            throw new RuntimeException("Empty queue");
        }
        else {

            return queueDS[(start++)%MAX_SIZE];
        }

    }
}



interface QueueI {
    //this will add element to queue
    public void enQueue(int value);

    public int dequeue();
    //size
    //conversion
    //
    //
}
