package Y2023.july11;

import java.util.Arrays;

public class HeapOperations {
    public static void main(String[] args) {
        int [] arr = new int [] {10, 1, 3, -5, 2, 7, 11, -11};
        HeapI minHeap = new MinHeapImpl(arr);
        minHeap.print();
        for (int i=0;i<10000;i++) {
            minHeap.insert(minHeap.pop());
            minHeap.insert(minHeap.pop());
            minHeap.insert(minHeap.pop());
            minHeap.insert(minHeap.pop());
            minHeap.insert(minHeap.pop());
            minHeap.insert(minHeap.pop());
            minHeap.insert(minHeap.pop());
            minHeap.insert(minHeap.pop());
        }
        minHeap.print();
    }
}


class MinHeapImpl implements HeapI {
    int [] heap;
    int heapSize;
    int arrSize;
    int lastPos;

    public MinHeapImpl(int [] arr) {
        this.arrSize = arr.length;
        heapSize = (2*(int)Math.pow(2, Math.ceil(Math.log(arr.length)/Math.log(2)))) - 1;
        heap = new int[heapSize];
        lastPos=-1;
        generateHeap(arr);
    }


    @Override
    public void insert(int x) {
        heap[++lastPos] = x;
        int parent = (lastPos-1)/2;

        while (parent >= 0) {
            int leftC = 2*parent+1;
            int rightC = 2*parent+2;

            if (heap[leftC] > heap[rightC]) {
                if (heap[rightC] < heap[parent]) {
                    int temp = heap[parent];
                    heap[parent] = heap[rightC];
                    heap[rightC] = temp;
                }
                else {
                    break;
                }
            } else {
                if (heap[leftC] < heap[parent]) {
                    int temp = heap[parent];
                    heap[parent] = heap[leftC];
                    heap[leftC] = temp;
                }
                else {
                    break;
                }
            }
            if (parent==0) {
                break;
            }
            parent = (parent-1)/2;
        }
    }

    @Override
    public int poll() {
        return heap[0];
    }

    @Override
    public int pop() {
        if (lastPos >= 0) {
            int min = heap[0];
            heap[0] = heap[lastPos];
            heap[lastPos] = Integer.MAX_VALUE;
            lastPos--;
            heapify(0);
            return min;
        } else
        {
            return Integer.MAX_VALUE;
        }
    }

    @Override
    public int size() {
        return lastPos>=0 ? lastPos : 0;
    }

    @Override
    public void print() {
        System.out.println("Printing Heap");
        System.out.println("Size: "+heap.length+"\tElements: "+(lastPos+1));
        for (int x: heap) {
            System.out.print(x+", ");
        }
        System.out.println();
    }

    private void heapify(int index) {
        while (index <= heap.length) {
            int leftC = 2*index+1;
            int rightC = 2*index+2;

            if (leftC >= heap.length) {
                break;
            } else if (rightC >= heap.length) {
                if (heap[leftC] > heap[index]) {
                    int temp = heap[index];
                    heap[index] = heap[leftC];
                    heap[leftC] = temp;
                }
                break;
            }
            else {
                if (heap[leftC] > heap[rightC]) {
                    if (heap[rightC] < heap[index]) {
                        int temp = heap[index];
                        heap[index] = heap[rightC];
                        heap[rightC] = temp;
                    }
                    index = rightC;
                } else {
                    if (heap[leftC] < heap[index]) {
                        int temp = heap[index];
                        heap[index] = heap[leftC];
                        heap[leftC] = temp;
                    }
                    index = leftC;
                }
            }
        }
    }

    private void generateHeap(int [] arr) {

        for (int i=0 ; i<heapSize ; i++) {
            if (i< arr.length) {
                heap[i] = arr[i];
                lastPos = i;
            }
            else {
                heap[i] = Integer.MAX_VALUE;
            }
        }

        for (int i=(heapSize/2 - 1);i>=0;i--) {
            heapify(i);
        }
    }
}

interface HeapI {
    void insert(int x);
    int poll();
    int pop();
    int size();

    void print();
}