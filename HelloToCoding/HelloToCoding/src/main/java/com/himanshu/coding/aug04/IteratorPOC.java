package com.himanshu.coding.aug04;

public class IteratorPOC {
    public static void main(String[] args) {
        DLL<String> dll = new DLL<>();
        dll.insert("Himanshu");
        dll.insert("Bhardwaj");
        dll.insert("is");
        dll.insert("Great");

        dll.print();
        System.out.println();
        System.out.println();

        Iterator<String> iterator = dll.getIterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next()+", ");
        }
        System.out.println();

        Iterator<String> iterator2 = dll.getIterator();

        while (iterator2.hasNext()) {
            System.out.print(iterator2.next()+", ");
        }

    }
}

class DLL<T>{
    Node head;
    int size=0;

    void insert(T value) {
        if (head==null) {
            head = new Node(value);
            size++;
        } else {
            Node<T> node = new Node<>(value);
            node.next=head;
            head=node;
            size++;
        }
    }

    void print() {
        Node<T> tHead = head;
        while (tHead != null) {
            System.out.print(tHead.value+", ");
            tHead = tHead.next;
        }
        System.out.println();
    }

    int size() {
        return size;
    }

    Iterator<T> getIterator() {
        return  new DLLIterator<T>(head);
    }

    class DLLIterator<T> implements Iterator<T> {
        Node<T> head;

        public DLLIterator(Node<T> head) {
            this.head = head;
        }

        @Override
        public T next() {
            if (head==null) {
                return null;
            }
            Node<T> tHead = head;
            head=head.next;
            return tHead.value;
        }

        @Override
        public boolean hasNext() {
            return (head !=null);
        }

        @Override
        public T previous() {
            if (head==null || head.previous==null) {
                return null;
            } else {
                return head.previous.value;
            }
        }

        @Override
        public boolean hasPrevious() {
            if (head==null) {
                return false;
            }

            if (head.previous == null) {
                return false;
            }
            return true;
        }
    }
}

class Node<T> {
    T value;
    Node<T> next;
    Node<T> previous;

    public Node(T value) {
        this.value = value;
        this.next=null;
        this.previous=null;
    }
}

interface Iterator<T> {
    T next();
    boolean hasNext();
    T previous();
    boolean hasPrevious();
}
