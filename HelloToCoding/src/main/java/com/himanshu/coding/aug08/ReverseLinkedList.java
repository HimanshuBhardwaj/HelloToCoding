package com.himanshu.coding.aug08;

public class ReverseLinkedList {
    public static void main(String[] args) {
        LinkedList linkedList  = new LinkedList();
        linkedList.insert(1);
        linkedList.insert(2);
        linkedList.insert(3);
        linkedList.print();
        System.out.println("reversing Linkedlist");
        linkedList.reverse();
        linkedList.print();
        System.out.println("reversing Linkedlist");
        linkedList.reverse();
        linkedList.print();



    }
}

class LinkedList {
    Node head;

    void insert(int value) {
        if (head==null) {
            head = new Node(value);
        } else {
            Node node = new Node(value);
            node.next=head;
            head=node;
        }
    }

    void print() {
        Node tHead = head;
        while (tHead != null) {
            System.out.print(tHead.value+", ");
            tHead = tHead.next;
        }
        System.out.println();
    }

    void reverse() {
        if (head==null) {
            return;
        }
        Node current=head;
        Node next=head.next;
        Node previous=null;

        while (current != null) {
            current.next=previous;
            previous=current;

            if (next==null) {
                head=current;
                break;
            } else {
                current=next;
                next = next.next;
            }

        }
    }

}

class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value=value;
        next=null;
    }
}
