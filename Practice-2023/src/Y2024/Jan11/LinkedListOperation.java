package Y2024.Jan11;


/**
 * @author Himanshu Bhardwaj
 * @Date 1/11/2024
 */
public class LinkedListOperation {
    public static void main(String[] args) {
        LinkedListI linkedListI = new LinkedList();
        LNode head = linkedListI.insert(null,1);
        head = linkedListI.insert(head,-1);
        head = linkedListI.insert(head,2);
        head = linkedListI.insert(head,-2);
        head = linkedListI.insert(head,3);
        head = linkedListI.insert(head,-3);

        linkedListI.print(head);
        linkedListI.size(head);
        System.out.println("Reversing-----------------");

        head = linkedListI.reverse(head);
        linkedListI.print(head);
        linkedListI.size(head);
        System.out.println("Reversing-----------------");
    }
}


class LinkedList implements LinkedListI {


    @Override
    public LNode insert(LNode head, int value) {
        LNode node = new LNode(value);
        node.next =  head;
        return node;
    }

    @Override
    public void print(LNode head) {
        while (head!= null) {
            System.out.print(head.value+", ");
            head = head.next;
        }
        System.out.println();
    }

    @Override
    public void size(LNode head) {
        int count=0;

        while (head != null) {
            count++;
            head = head.next;
        }
        System.out.println(count);
    }

    @Override
    public LNode reverse(LNode head) {
        if (head==null || head.next==null) {
            return head;
        }

        LNode prev = null;
        LNode next = head.next;
        while (head != null) {
            head.next = prev;
            prev=head;
            head = next;
            if (next != null) {
                next = next.next;
            }
        }

        return prev;
    }
}

interface LinkedListI {
    LNode insert(LNode head, int value);
    void print(LNode head);
    void size(LNode node);

    LNode reverse(LNode head);
}

class LNode {
    int value;

    public LNode(int value) {
        this.value = value;
        this.next= null;
    }

    LNode next;
}