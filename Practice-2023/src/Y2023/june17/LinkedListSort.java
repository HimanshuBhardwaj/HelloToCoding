package Y2023.june17;

public class LinkedListSort {
    public static void main(String[] args) {
        LNode root = new LNode(5);
        LNode root1 = new LNode(11);
        LNode root2 = new LNode(10);
        LNode root3 = new LNode(1);
        LNode root4 = new LNode(5);
        LNode root5 = new LNode(0);
        LNode root6 = new LNode(2);
        LNode root7 = new LNode(20);

        root.next = root1;
        root1.next  = root2;
        root2.next = root3;
        root3.next = root4;
        root4.next = root5;
        root5.next = root6;
        root6.next = root7;




        LNode root222 = new LNode(-5);
        LNode root21 = new LNode(-11);

        LNode root22 = new LNode(-10);
        LNode root23 = new LNode(-1);
        /*LNode root24 = new LNode(-5);
        LNode root25 = new LNode(-0);
        LNode root26 = new LNode(-2);
        LNode root27 = new LNode(-20);*/

        root222.next = root21;
        //root21.next  = root22;
        // root22.next = root23;
        /*root23.next = root24;
        root24.next = root25;
        root25.next = root26;
        root26.next = root27;*/


        LNode mergedRoor = root222.mergeLL(root222, root);
        mergedRoor.print(mergedRoor);

        LNode sortedList = mergedRoor.mergeSort(mergedRoor);
        sortedList.print(sortedList);
    }
}


class LNode {
    int value;
    LNode next = null;

    public LNode(int v) {
        this.value = v;
        this.next = null;
    }

    public void print(LNode head) {
        while (head != null) {
            System.out.print(head.value+", ");
            head = head.next;
        }
        System.out.println();
    }

    public LNode mergeSort(LNode l1) {
        if (l1 == null || l1.next == null) {
            return l1;
        }

        LNode l2 = l1.next;

        if (l2.next == null) {
            int min = Math.min(l1.value, l2.value);
            int max = Math.max(l1.value, l2.value);
            l1.value = min;
            l2.value = max;
            return l1;
        }

        l2 = splitHalf(l1);
        mergeSort(l1);
        mergeSort(l2);
        return mergeLL(l1, l2);
    }

    public LNode mergeLL(LNode l1, LNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        LNode newP = null;
        LNode newHead = null;

        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {

                if (newHead==null) {
                    newHead = l1;
                    newP = l1;
                } else {
                    newP.next = l1;
                    newP = newP.next;
                }

                l1 = l1.next;
            } else {
                if (newHead==null) {
                    newHead = l2;
                    newP = l2;
                } else {
                    newP.next = l2;
                    newP = newP.next;
                }

                l2 = l2.next;
            }
        }

        if (l1 == null) {
            newP.next = l2;
        }

        if(l2 == null) {
            newP.next = l1;
        }

        return newHead;
    }

    public LNode splitHalf(LNode l1) {
        if (l1 == null || l1.next == null) {
            return null;
        }
        LNode hare = l1;
        LNode tortoise = l1;

        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
        }

        LNode nextHead = tortoise.next;
        tortoise.next = null;
        return nextHead;
    }
}