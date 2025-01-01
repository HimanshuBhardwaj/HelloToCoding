package dec25;

public class A {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insert(11);

        list.print();
        System.out.println();
        list.sort();
        list.print();
    }
}

class LinkedList {
    Node head;

    public LinkedList() {
        this.head = null;
    }

    public void insert(int value) {
        if (this.head == null) {
            this.head = new Node(value);
        } else {
            Node node = new Node(value);
            node.next=this.head;
            this.head=node;
        }
    }

    public void print() {
        Node tHead = head;
        while (tHead != null) {
            System.out.print(tHead.value+"->");
            tHead= tHead.next;
        }
    }

    public void sort() {
        head = sortHelper(head);
    }

    // 10:18
    private Node sortHelper(Node head) {
        if (head==null || head.next==null) {
            return head;
        }
        Node sh = splitHalf(head);

        Node a = sortHelper(head);
        Node b = sortHelper(sh);

        return merge(a,b);
    }

    private Node splitHalf(Node head) {
        Node t=head;
        Node h=head;
        Node prev=null;

        while (h != null) {
            h=h.next;
            if (h != null) {
                h = h.next;
            }
            prev=t;
            t = t.next;
        }
        prev.next=null;
        return t;
    }

    private Node merge(Node a, Node b) {
        Node head=null;
        Node tHead=null;

        while (a != null && b!= null) {
            if (a.value < b.value) {
                if (head==null) {
                    head=a;
                    tHead=a;
                    a=a.next;
                } else {
                    tHead.next=a;
                    a=a.next;
                    tHead=tHead.next;
                }
            } else {
                if (head==null) {
                    head=b;
                    tHead=b;
                    b=b.next;
                } else {
                    tHead.next=b;
                    b=b.next;
                    tHead=tHead.next;
                }
            }
        }

        if (a!=null) {
            tHead.next=a;
        } else {
            tHead.next=b;
        }

        return head;
    }
}

class Node {

    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }
}
