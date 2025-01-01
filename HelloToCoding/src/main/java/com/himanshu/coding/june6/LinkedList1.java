package com.himanshu.coding.june6;

public class LinkedList1 {
    public static void main(String[] args) {
        ILinkedList iLinkedList = new LinkedListImpl1();
        iLinkedList.insert(1);
        iLinkedList.insert(10);
        iLinkedList.insert(100);
        iLinkedList.insert(99);
        iLinkedList.insert(9);

        iLinkedList.print();

        System.out.println(iLinkedList.isPresent(99));
        System.out.println(iLinkedList.isPresent(999));

    }
}

interface ILinkedList
{
    void print();
    void insert(int value);
    boolean isPresent(int value);
}

class Node
{
    int value;
    Node next;

    public Node(int value)
    {
        this.value = value;
    }
}

class  LinkedListImpl1 implements  ILinkedList
{
    Node head=null;


    @Override
    public void print() {

        Node tHead = head;

        while (tHead != null)
        {
            System.out.print(tHead.value+", ");
            tHead = tHead.next;
        }
        System.out.println();
    }

    @Override
    public void insert(int value) {
        if (head==null)
        {
            head = new Node(value);
        } else {
            Node node = new Node(value);
            node.next = head;
            head = node;
        }
    }

    @Override
    public boolean isPresent(int value) {

        Node tHead = head;

        while (tHead != null)
        {
            if (tHead.value == value)
            {
                return true;
            }
            tHead = tHead.next;
        }
        return false;
    }
}