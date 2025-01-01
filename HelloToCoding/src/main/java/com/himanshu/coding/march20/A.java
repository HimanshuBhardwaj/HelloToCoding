package com.himanshu.coding.march20;

public class A {
    public static void main(String[] args) {
        LinkedList2 list = new LinkedList2();
        list.add(5);
        list.add(51);
        list.add(25);
        list.add(53);
        list.add(45);
        list.add(55);

        list.print();
        list.delete(5);
        list.print();

    }
}

class LinkedList2 {
    Node2 head;

    void  add(int value) {
        if (head == null) {
            head = new Node2(value);
        } else {
            Node2 temp = new Node2(value);
            temp.next = head;
            head=temp;
        }
    }

    void print() {
        Node2 tHead = head;
        while (tHead != null) {
            System.out.print(tHead.value+", ");
            tHead = tHead.next;
        }
        System.out.println();
    }

    void delete(int value) {
        if (head == null) {
            return;
        }
        if (head.value == value) {
            head = head.next;
            return;
        }
        Node2 previous = head;
        Node2 current = head.next;


        while (current!= null && current.value != value) {
            current = current.next;
            previous = previous.next;
        }
        if (current==null) {
            return;
        } else {
            previous.next = current.next;
        }
    }
}


class Node2 {
    Node2 next;
    int value;

    public Node2(int x) {
        this.value = x;
        next = null;
    }
}