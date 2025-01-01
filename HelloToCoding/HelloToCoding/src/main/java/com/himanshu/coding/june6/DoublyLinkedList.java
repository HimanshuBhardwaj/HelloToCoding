package com.himanshu.coding.june6;

public class DoublyLinkedList {
    public static void main(String[] args) {
        IDoublyLinkedList doublyLinkedList = new DoublyLinkedListImpl();
        doublyLinkedList.insert(1);
        doublyLinkedList.insert(11);


        doublyLinkedList.insert(111);
        doublyLinkedList.insert(1111);
        doublyLinkedList.insert(11111);

        doublyLinkedList.print();
        System.out.println(doublyLinkedList.isPresent(1));

        System.out.println(doublyLinkedList.size());
        System.out.println(doublyLinkedList.isPresent(11111));
        doublyLinkedList.remove(11111);
        doublyLinkedList.print();

        System.out.println(doublyLinkedList.isPresent(1111));
        doublyLinkedList.remove(1111);
        doublyLinkedList.print();


        doublyLinkedList.remove(111);
        doublyLinkedList.print();


        doublyLinkedList.remove(11);
        doublyLinkedList.print();

        doublyLinkedList.remove(1);
        doublyLinkedList.print();


       /* System.out.println(doublyLinkedList.isPresent(1111));
        /*System.out.println("-----------Before removing-----------");
        doublyLinkedList.print();
        System.out.println("Removing 1");
        doublyLinkedList.remove(1);
        System.out.println("After 1");
        doublyLinkedList.print();*/


    }
}

interface  IDoublyLinkedList
{
    void insert(int value);
    boolean isPresent(int value);
    int size();
    void  print();
    void remove(int value);
}

class Node1
{
    int value;
    Node1 next;
    Node1 previous;

    public Node1(int value)
    {
        this.value=value;
        next=null;
        previous=null;
    }
}

class DoublyLinkedListImpl implements IDoublyLinkedList
{
    Node1 head=null;
    Node1 tail=null;


    @Override
    public void insert(int value) {
        Node1 node = new Node1(value);

        if (head == null && tail == null)
        {
            head = node;
            tail = node;
        } else
        {
            node.next=head;
            head.previous=node;
            head=node;
        }
    }

    //tested
    @Override
    public boolean isPresent(int value) {
        if (head==null)
        {
            return false;
        }
        Node1 temp=head;

        do {
            if (temp.value==value)
            {
                return true;
            }
            temp = temp.next;
        }while (temp != null);

        return false;
    }

    //Tested
    @Override
    public int size() {
        int size=0;
        if (head==null)
        {
            return size;
        }
        Node1 temp=head;

        do {
            size++;
            temp = temp.next;
        }while (temp != null);
        return size;
    }

    //Tested
    @Override
    public void print() {
        if (head==null)
        {
            System.out.println("Empty linked list");
            return;
        }
        Node1 temp=head;

        do {
            System.out.print(temp.value+", ");
            temp = temp.next;
        }while (temp != null);
        System.out.println();
    }

    @Override
    public void remove(int value) {
        if (head==null)
        {
            return;
        }
        if (head==tail)
        {
            System.out.println("head==tail");
            if (head.value==value)
            {
                head=null;
                tail=null;
            } else {
                return;
            }
        }

        Node1 thead = head;

        while ((thead != null) && (thead.value != value))
        {
            thead = thead.next;
        }

        if (thead == null)
        {
            return;
        }

        if (thead.value==value)
        {
            //if next is null
            if(thead.next==null && thead.previous != null) {
                thead.previous.next = null;
            } else if ( (thead.next != null) && thead.previous==null )
            {
                head = head.next;
            }
            else {
                Node1 tempN = thead.previous;
                thead.previous.next=thead.next;

                thead.next.previous=tempN;
            }

        }
    }
}