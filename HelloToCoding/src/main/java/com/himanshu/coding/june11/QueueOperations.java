package com.himanshu.coding.june11;

public class QueueOperations {
    public static void main(String[] args) {
        LinkedListQueue linkedListQueue = new LinkedListQueue();
        linkedListQueue.insert(1);
        linkedListQueue.insert(2);
        linkedListQueue.insert(3);
        linkedListQueue.insert(4);
        linkedListQueue.insert(5);
        System.out.println(linkedListQueue.size());
        linkedListQueue.print();
        System.out.println("Removing 5"+ linkedListQueue.remove(5));
        linkedListQueue.print();
        System.out.println("Removing 3"+ linkedListQueue.remove(3));
        linkedListQueue.print();
        System.out.println("Removing 1"+ linkedListQueue.remove(1));
        linkedListQueue.print();
        System.out.println("Removing 2"+ linkedListQueue.remove(2));
        linkedListQueue.print();
        System.out.println("Removing 4"+ linkedListQueue.remove(4));
        linkedListQueue.print();
    }
}



class LinkedListQueue implements  QueueI {
    private Node head=null;
    private Node tail=null;
    private int size=0;

    @Override
    public void insert(int x) {
        if (head==null) {
            head = new Node(x);
            tail=head;
            size=1;
            return;
        }

        Node node = new Node(x);

        tail.next=node;
        tail=node;
        size++;

    }

    @Override
    public int peek() {
        return head.value;
    }

    @Override
    public int poll() {
        Node tHead=head;
        head=head.next;
        if (head==null) {
            tail=null;
        }
        size--;
        return tHead.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void print() {
        Node tHead = head;

        while (tHead != null) {
            System.out.print(tHead.value+", ");
            tHead = tHead.next;
        }
        if (head!=null) {
            System.out.println("\t Head:" + head.value + "\tTail:" + tail.value);
        } else {
            System.out.println("\t Head:" + head + "\tTail:" + tail);
        }
    }

    //implement it tommorow
    @Override
    public boolean remove(int x) {
        if(head==null) {
            return false;
        }

        if (head.value==x) {
            head=head.next;
            if (head==null) {
                tail=null;
            }
            return true;
        }


        Node tHead = head;
        Node next=head.next;

        while (next != null && next.value!=x) {
            tHead=tHead.next;
            next=next.next;
        }

        if (next==null) {
            return false;
        }

        tHead.next=next.next;
        if (tHead.next == null) {
            tail=tHead;
        }
        return true;
    }
}

class Node {
    int value;
    Node next;

    public Node(int x){
        this.value = x;
    }
}

interface QueueI {
    void insert(int x);
    // peel will only return top but not remove it
    int peek();
    //poll will return top and remove it
    int poll();
    int size();
    void print();

    boolean remove(int x);
}

