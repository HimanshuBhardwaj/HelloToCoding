package Y2024.jan9;

import java.util.PriorityQueue;

/**
 * @author Himanshu Bhardwaj
 * @Date 1/4/2024
 */
public class PriorityQueuePOC {
    public static void main(String[] args) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(10));
        priorityQueue.add(new Node(-9));
        priorityQueue.add(new Node(8));
        priorityQueue.add(new Node(-7));
        priorityQueue.add(new Node(6));
        priorityQueue.add(new Node(-5));
        priorityQueue.add(new Node(4));
        priorityQueue.add(new Node(-3));

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
            System.out.println(priorityQueue.remove(new Node(10)));
        }
    }
}

class Node implements Comparable<Node> {

    @Override
    public String toString() {
        return "Node{" +
                "n=" + n +
                '}';
    }

    int n;

    public Node(int n) {
        this.n = n;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.n, o.n);
    }
}