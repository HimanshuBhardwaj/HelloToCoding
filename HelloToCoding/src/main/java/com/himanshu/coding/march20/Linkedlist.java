package com.himanshu.coding.march20;

public class Linkedlist {
    public static void main(String[] args) {
        Linkedlist linkedlist  = new Linkedlist();
        Node1 head = linkedlist.insert(null,1);
        head = linkedlist.insert(head,11);
        head = linkedlist.insert(head,111);
        head = linkedlist.insert(head,11111);
        head = linkedlist.insert(head,111111);
        head = linkedlist.insert(head,111111);
        head = linkedlist.insert(head,111111);
        head = linkedlist.insert(head,111111);
        head = linkedlist.insert(head,111110);


        linkedlist.print(head);
        System.out.println();
       head= linkedlist.delete(head,1);
        linkedlist.print(head);
        System.out.println();

    }

    public Node1  delete(Node1 head, int value) {
        if (head==null) {
            return null;
        }

        if (head.value == value) {
            return head.next;
        } else {
            head.next = delete(head.next,value);
        }
        return head;
    }

    public Node1 insert(Node1 head,int value) {
        if (head == null) {
            return new Node1(value);
        }
        else {
            Node1 newHead = new Node1(value);
            newHead.value = value;
            newHead.next = head;
            return newHead;
        }
    }

    public void print(Node1 node1) {
        if (node1 == null) {
            return;
        } else {
            System.out.print(node1.value+", ");
            print(node1.next);
        }
    }


}






class Node1 {
    int value;
    Node1 next;

    public Node1(int value) {
        this.value = value;
        next = null;
    }
}