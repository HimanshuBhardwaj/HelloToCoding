package com.himanshu.coding.june12;

import java.util.*;

public class PriorityQueuePOC {
}

class StackPOC {
    public static void main(String[] args) {
        Stack<Entity> priorityQueue = new Stack<>();
        priorityQueue.add(new Entity("100imanshu", 10));
        priorityQueue.add(new Entity("00imanshu", 0));
        priorityQueue.add(new Entity("99imanshu", 99));
        priorityQueue.add(new Entity("101imanshu", 101));
        priorityQueue.add(new Entity("1010imanshu", 1010));
        priorityQueue.add(new Entity("1010Himanshu", -1010));
        priorityQueue.add(new Entity("1010Himanshu", -11010));

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.pop());
        }
    }
}

class QueuePOC {
    public static void main(String[] args) {
        Queue<Entity> priorityQueue = new LinkedList<>();
        priorityQueue.add(new Entity("100imanshu", 10));
        priorityQueue.add(new Entity("00imanshu", 0));
        priorityQueue.add(new Entity("99imanshu", 99));
        priorityQueue.add(new Entity("101imanshu", 101));
        priorityQueue.add(new Entity("1010imanshu", 1010));
        priorityQueue.add(new Entity("1010Himanshu", -1010));
        priorityQueue.add(new Entity("1010Himanshu", -11010));

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }
    }
}

class SortingPOc {
    public static void main(String[] args) {
        LinkedList<Entity> priorityQueue = new LinkedList();
        priorityQueue.add(new Entity("100imanshu", 10));
        priorityQueue.add(new Entity("00imanshu", 0));
        priorityQueue.add(new Entity("99imanshu", 99));
        priorityQueue.add(new Entity("101imanshu", 101));
        priorityQueue.add(new Entity("1010imanshu", 1010));
        priorityQueue.add(new Entity("1010Himanshu", -1010));
        priorityQueue.add(new Entity("1010Himanshu", -11010));

        System.out.println(priorityQueue);
        Collections.sort(priorityQueue);
        System.out.println(priorityQueue);
    }
}

class PriorityQueuePOC2 {
    public static void main(String[] args) {
        PriorityQueue<Entity> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Entity("100imanshu", 10));
        priorityQueue.add(new Entity("00imanshu", 0));
        priorityQueue.add(new Entity("99imanshu", 99));
        priorityQueue.add(new Entity("101imanshu", 101));
        priorityQueue.add(new Entity("1010imanshu", 1010));
        priorityQueue.add(new Entity("1010imanshu", -1010));
        priorityQueue.add(new Entity("1010imanshu", -11010));

        System.out.println("Priority Queue");
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }
    }
}


class TreePOC {
    public static void main(String[] args) {
        TreeSet<Entity> tree = new TreeSet<>();
        tree.add(new Entity("100imanshu", 10));
        tree.add(new Entity("00imanshu", 0));
        tree.add(new Entity("99imanshu", 99));
        tree.add(new Entity("101imanshu", 101));
        tree.add(new Entity("1010imanshu", 1010));
        tree.add(new Entity("1010imanshu", -1010));
        tree.add(new Entity("1010imanshu", -11010));

        System.out.println(tree);
        Entity entity = new Entity("",100);
        System.out.println(tree.ceiling(entity));
        System.out.println(tree.floor(entity));

    }
}

class Entity implements Comparable<Entity> {
    String name;
    float marks;

    public Entity(String name, float marks) {
        this.name = name;
        this.marks = marks;
    }

    public String toString() {
        return "("+name+", "+marks+")";
    }

    @Override
    public int compareTo(Entity o) {
        return Float.compare(this.marks,o.marks);
    }
}
