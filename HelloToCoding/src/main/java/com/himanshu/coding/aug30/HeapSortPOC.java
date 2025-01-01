package com.himanshu.coding.aug30;

import java.net.http.HttpRequest;

public class HeapSortPOC {
    public static void main(String[] args) {
        int arr [] = {111,-111,1};
        Heap heap = new Heap(arr);

        heap.print();

        while (heap.hasElement()) {
            System.out.print(heap.deletion()+", ");
        }
        System.out.println();
    }
}


class Heap implements  PriorityQueue {
    int[] heap;
    int size;
    int pos;

    public Heap(int [] arr) {
        this.size = 2*(int)Math.pow(2,(int)Math.ceil(Math.log(arr.length)/Math.log(2))) -1;
        heap = new int[size];

        for (int i=0;i<heap.length;i++) {
            if (i < arr.length) {
                heap[i] = arr[i];
                pos=i;
            } else {
                heap[i] = Integer.MAX_VALUE;
            }
        }
        heapify(heap);
    }

    public void heapify(int [] arr) {
        for (int i=(arr.length/2)-1;i>=0;i--) {
            settleHeap(arr,i);
        }
    }

    @Override
    public void insertion(int value) {
        heap[++pos]=value;
        int parent=(pos-1)/2;

        while (parent >=0) {
            int leftChild= 2*parent+1;
            int rightChild = 2*parent+2;
            if (heap[parent] < Math.min(heap[leftChild],heap[rightChild])) {
                break;
            } else {
                if (heap[leftChild] < heap[rightChild]) {
                    int temp = heap[leftChild];
                    heap[leftChild]=heap[parent];
                    heap[parent]=temp;
                } else {
                    int temp = heap[rightChild];
                    heap[rightChild]=heap[parent];
                    heap[parent]=temp;
                }
                parent = (parent-1)/2;
            }
        }
    }

    void settleHeap(int []heap, int position) {
        while (position < heap.length && heap[position] != Integer.MAX_VALUE) {
            int leftChild = 2*position+1;
            int rightChild = 2*position+2;

            if (!isValid(leftChild)) {
                break;
            }

            if (!isValid(rightChild)) {
                if (heap[position] > heap[leftChild]) {
                    int temp = heap[leftChild];
                    heap[leftChild] = heap[position];
                    heap[position] = temp;
                }
                break;
            }

            if (heap[position] < Math.min(heap[leftChild],heap[rightChild])) {
                break;
            } else {
                if (heap[leftChild] < heap[rightChild]) {
                    int temp = heap[leftChild];
                    heap[leftChild] = heap[position];
                    heap[position] = temp;
                    position=leftChild;
                } else {
                    int temp = heap[rightChild];
                    heap[rightChild] = heap[position];
                    heap[position] = temp;
                    position=rightChild;
                }
            }
        }
    }

    public boolean isValid(int pos) {
        return (pos < heap.length);
    }

    @Override
    public int deletion()
    {
        int value=heap[0];
        heap[0]=heap[pos];
        heap[pos] = Integer.MAX_VALUE;
        pos--;
        settleHeap(heap,0);
        return value;

    }

    @Override
    public int peek() {
        return heap[0];
    }

    @Override
    public void print() {
        for (int i=0;i<heap.length;i++) {
            System.out.print(heap[i]+", ");
        }
        System.out.println();
    }

    @Override
    public boolean hasElement() {
        return (pos>=0);
    }
}
interface PriorityQueue {
    void insertion(int value);
    int deletion();
    int peek();
    void print();
    boolean hasElement();
}