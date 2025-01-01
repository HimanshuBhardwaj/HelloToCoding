package com.himanshu.coding.june11;

public class StackOperationsLinkedlist {
    public static void main(String[] args) {
        StackIL<String> nodeS = new StackImpl<>();
        nodeS.push("Himanshu");
        nodeS.push("Bhardwaj");
        nodeS.push("great");
        nodeS.push("man");

        System.out.println(nodeS.size());
        System.out.println(nodeS.pop());
        System.out.println(nodeS.pop());
        System.out.println(nodeS.pop());
        nodeS.push("timepass");
        System.out.println(nodeS.pop());
        System.out.println(nodeS.pop());


        System.out.println();
        System.out.println();

        System.out.println("Array impl ....................\n\n");

        StackIL<String> nodeS1 = new ArrayImpl<>();
        nodeS1.push("Himanshu");
        nodeS1.push("Bhardwaj");
        nodeS1.push("great");
        nodeS1.push("man");

        System.out.println(nodeS1.size());
        System.out.println(nodeS1.pop());
        System.out.println(nodeS1.pop());
        System.out.println(nodeS1.pop());
        nodeS1.push("timepass");
        System.out.println(nodeS1.pop());
        System.out.println(nodeS1.pop());

    }
}

class NodeS<T> {
    T value;
    NodeS<T> next;

    public NodeS(T t) {
        this.value=t;
        next=null;
    }
}

class ArrayImpl<T> implements StackIL<T> {
    T [] stack;
    int size;
    int head;
    int MAX_SIZE;

    public ArrayImpl() {
        MAX_SIZE=100;
        size=0;
        head=0;
        stack = (T[])new Object[MAX_SIZE];
    }

    @Override
    public void push(T t) {
        if (size<MAX_SIZE) {
            size++;
            stack[head]=t;
            head++;
            return;
        }
        System.out.println("Value "+t+" is not added");
    }

    @Override
    public T pop() {
        if (size>0) {
            T t = stack[--head];
            size--;
            return t;
        }
        return null;
    }

    @Override
    public T peek() {
        if (size>0) {
            return stack[head-1];
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}

class StackImpl<T> implements StackIL<T> {
    NodeS<T> head;
    int size;

    public StackImpl() {
        head=null;
        size=0;
    }


    @Override
    public void push(T t) {
        NodeS nodeS = new NodeS(t);

        if (head==null) {
            head=nodeS;
            size++;
        }
        else {
            nodeS.next=head;
            head=nodeS;
            size++;
        }
    }

    @Override
    public T pop() {
        if (head != null) {
            size--;
            T value = head.value;
            head = head.next;
            return value;
        }
        return null;
    }

    @Override
    public T peek() {
        if (head != null) {
            return head.value;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}

interface StackIL<T> {
    void push(T t);
    T pop();
    T peek();
    int size();
}
