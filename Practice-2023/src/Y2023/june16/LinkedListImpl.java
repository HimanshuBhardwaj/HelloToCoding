package Y2023.june16;

public class LinkedListImpl {
    public static void main(String[] args) {
        List list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(-3);

        list.print();
        System.out.println(list.size());

        list.remove(-3);
        list.print();
        System.out.println(list.size());
    }
}

class LinkedList implements List {
    int elements;
    Node head;

    public LinkedList() {
        this.head = null;
        this.elements = 0;
    }

    @Override
    public void add(int v) {
        Node newNode = new Node(v);

        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        elements++;
    } // tested

    @Override
    public boolean remove(int v) {
        if(head == null) {
            return false;
        }

        if (head.next == null) {
            if (head.value == v) {
                head = null;
                elements--;
                return true;
            }
            return false;
        }

        if (head.value == v) {
            head  = head.next;
            elements--;
        }

        Node current = head;
        Node next  = head.next;

        while (next!= null && next.value != v) {
            current = current.next;
            next = next.next;
        }

        if (next != null) {
            current.next = next.next;
            elements--;
            return true;
        }

        return false;
    }

    @Override
    public int size() {
        return elements;
    } // tested

    @Override
    public boolean isPresent(int value) {
        Node headInternal = head;

        while (headInternal != null) {
            if (headInternal.value == value) {
                return true;
            }
            headInternal = headInternal.next;
        }
        return false;
    } //tested

    @Override
    public void print() {
        Node headInternal = head;

        while (headInternal != null) {
            System.out.print(headInternal.value+", ");
            headInternal = headInternal.next;
        }
        System.out.println();
    } // tested
}

class Node {
    int value;
    Node next;

    public Node(int v) {
        this.value = v;
        next = null;
    }
}

interface List {
    void add(int v);
    boolean remove(int v);
    int size();
    boolean isPresent(int value);
    void print();
}
