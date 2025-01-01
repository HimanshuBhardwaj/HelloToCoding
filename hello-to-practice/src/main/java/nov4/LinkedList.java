package nov4;

public class LinkedList {
    public static void main(String[] args) {
        Node head = new Node(1);
        head = head.insert(head,2);
        head = head.insert(head,3);
        head = head.insert(head,4);
        head = head.insert(head,5);
        head = head.insert(head,6);
        head.print(head);
        System.out.println();

        head = head.reverse(head,2);
        head.print(head);
    }
}



class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }

    Node insert(Node head, int value) {
        if (head == null) {
            return new Node(value);
        }

        Node node = new Node(value);
        node.next = head;
        return node;
    }

    void print(Node head) {
        if (head == null) {
            return;
        }

        while (head != null) {
            System.out.print(head.value +"->");
            head = head.next;
        }
        System.out.println();
    }

    Node reverse(Node head, int k) {

        Node nextHead = null;//
        Node newHead = null;
        Node previousHead=null;

        do {
            nextHead = nextHead(head,k);
            head = head.reverse(head);

            if (newHead == null) {
                newHead = head;
            }

            Node tail = tail(head);
            tail.next = nextHead;
            previousHead = head;
            head=nextHead;

        }while (head != null && nextHead != null);

        return newHead;
    }


    Node nextHead(Node head, int k) {
        Node previous = null;
        for (int i=1;i<=k;i++) {
            previous = head;
            head = head.next;

            if (head == null) {
                return null;
            }
        }
        previous.next = null;
        return head;
    }

    Node tail(Node head) {
        if (head==null || head.next == null) {
            return head;
        }

        while (head.next != null) {
            head = head.next;
        }

        return head;
    }

    Node reverse(Node head) {
        if (head == null || head.next==null) {
            return head;
        }

        Node headN = head.next;
        head.next = null;

        while (headN != null) {
            Node headNN = headN.next;
            headN.next = head;
            if (headNN==null) {
                return headN;
            } else {
                head =headN;
                headN=headNN;
            }
        }
        return null;
    }
}
