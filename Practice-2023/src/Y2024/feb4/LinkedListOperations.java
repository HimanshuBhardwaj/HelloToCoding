package Y2024.feb4;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/4/2024
 */
public class LinkedListOperations {
    public static void main(String[] args) {
        LinkedListI ll = new LinkedListImpl();
        ll.insert(-1);
        ll.insert(1);
        ll.insert(-2);
        ll.insert(21);
        ll.insert(-21);
        ll.insert(121);
        ll.insert(-121);
        ll.print();
        ll.sort();
        ll.print();
    }
}

class LinkedListImpl implements LinkedListI {
    Node head;

    public LinkedListImpl() {
        head = null;
    }

    @Override
    public void insert(int value) {
        if (head == null) {
            head = new Node(value);
        } else {
            Node node = new Node(value);
            node.next = head;
            head = node;
        }
    }

    @Override
    public int size() {
        int count = 0;
        Node th = head;
        while (th != null) {
            count++;
            th = th.next;
        }
        return count;
    }

    @Override
    public void print() {
        Node th = head;
        while (th != null) {
            System.out.print(th.value+", ");
            th = th.next;
        }
        System.out.println();
    }

    @Override
    public void reverse() {
        if (head == null || head.next == null) {
            return;
        }

        Node prev = null;
        Node cur = head;
        Node next = cur.next;

        while (next != null) {
            cur.next = prev;
            prev = cur;
            cur = next;
            next = next.next;

            if (next== null) {
                cur.next = prev;
                head = cur;
            }
        }
    }

    @Override
    public void sort() {
        head = sortHelper(head);
    }

    private Node sortHelper(Node head) {
        if (head== null || head.next == null) {
            return head;
        }

        Node prev = null;
        Node c1 = head;
        Node mid = head;

        while (c1!= null && mid!= null && mid.next!=null) {
            prev = c1;
            c1 = c1.next;
            mid = mid.next;
            mid = mid.next;
        }

        prev.next = null;
        Node h1 = head;
        Node h2 = c1;

        h1 = sortHelper(h1);
        h2 = sortHelper(h2);
        return merge(h1, h2);
    }

    private Node merge(Node h1, Node h2) {
        if (h1 == null) {
            return h2;
        }

        if (h2 == null) {
            return h1;
        }
        Node nh = null;

        if (h1.value < h2.value) {
            nh = h1;
            h1 = h1.next;
            nh.next = merge(h1,h2);
        } else {
            nh = h2;
            h2 = h2.next;
            nh.next = merge(h1,h2);
        }

        return nh;
    }

    @Override
    public void delete(int v) {
        if (head == null) {
            return;
        }
        if (head.value == v) {
            head = head.next;
            return;
        }

        Node curr = head;
        Node next = head.next;

        while (next != null) {
            if (next.value == v) {
                curr.next = next.next;
                break;
            } else {
                curr = next;
                next = next.next;
            }
        }
    }
}

interface LinkedListI {
    void insert(int value);
    int size();
    void print();
    void reverse();
    void sort();
    void delete(int v);
}

class Node {
    int value;
    Node next;


    public Node(int v) {
        this.value = v;
        this.next = null;
    }
}