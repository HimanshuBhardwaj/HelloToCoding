package com.himanshu.coding.june11;

public class LinkedListArray {
    public static void main(String[] args) {
        QueueAImpl queueA = new QueueAImpl();
        queueA.insert(1);
        queueA.insert(2);
        queueA.insert(3);




        queueA.print();
        System.out.println("Head: "+queueA.peek()+" Size"+queueA.size+" Removing head"+queueA.poll());
        queueA.insert(11);
        queueA.print();
        System.out.println("Head: "+queueA.peek()+" Size"+queueA.size+" Removing head"+queueA.poll());
        queueA.insert(12);
        queueA.print();
        System.out.println("Head: "+queueA.peek()+" Size"+queueA.size+" Removing head"+queueA.poll());
        queueA.insert(13);
        queueA.print();
        System.out.println("Head: "+queueA.peek()+" Size"+queueA.size+" Removing head"+queueA.poll());
        System.out.println("Head: "+queueA.peek()+" Size"+queueA.size+" Removing head"+queueA.poll());
        System.out.println("Head: "+queueA.peek()+" Size"+queueA.size+" Removing head"+queueA.poll());
        System.out.println("Head: "+queueA.peek()+" Size"+queueA.size+" Removing head"+queueA.poll());
        queueA.print();


    }
}

class QueueAImpl implements QueueAI {
    int [] array;
    int MAX_SIZE;
    int size;
    int head=0;
    int tail=0;//position which is ready to be inserted.

    public QueueAImpl()
    {
        MAX_SIZE=3;
        size=0;
        array = new int[MAX_SIZE];
    }

    @Override
    public void insert(int x) {
        if (size < MAX_SIZE) {
            array[tail]=x;
            tail =( tail +1 )%MAX_SIZE;
            size++;
        }
    }

    @Override
    public int peek() {
        if (size > 0) {
            return array[head];
        } else {
            return Integer.MIN_VALUE;
        }
    }

    @Override
    public int poll() {
        if (size>0) {
            size--;
            int value=array[head];
            head = (head+1)%MAX_SIZE;
            return value;
        }
        return Integer.MIN_VALUE;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void print() {
        for (int i=0;i<size;i++) {
            System.out.print(array[(i+head)%MAX_SIZE]+", ");
        }
        System.out.println();
    }
}

interface QueueAI {
    void insert(int x);
    // peel will only return top but not remove it
    int peek();
    //poll will return top and remove it
    int poll();
    int size();
    void print();
}
