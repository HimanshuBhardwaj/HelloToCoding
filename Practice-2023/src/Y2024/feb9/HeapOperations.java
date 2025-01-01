package Y2024.feb9;

import Y2024.feb6.A;

import java.util.ArrayList;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/9/2024
 */
public class HeapOperations {
    public static void main(String[] args) {
        int [] arr =  {-1,1,3,4,-4};
        HeapI heapI = new HeapImpl(arr);
        System.out.println(heapI.heapSort());
    }
}

class HeapImpl implements HeapI {
    int [] heap;
    int numElement;
    int n;
    int lastPos;

    public HeapImpl(int a[]) {
        this.numElement = a.length;
        this.n = (int)Math.pow(2,Math.ceil(Math.log(a.length+1)/Math.log(2))) -1;
        this.heap = new int[n];

        for (int i=0;i<n;i++) {
            if (i<a.length) {
                heap[i] = a[i];
                lastPos = i;
            } else {
                heap[i] = Integer.MIN_VALUE;
            }
        }

        heapifyAll();
    }

    @Override
    public ArrayList<Integer> heapSort() {
        ArrayList<Integer> sortI = new ArrayList<>();
        while (hasElement()) {
            sortI.add(poll());
        }
        return sortI;
    }

    @Override
    public void heapifyAll() {
        for (int i=((n/2)-1);i>=0;i--) {
            heapifyTopDown(i);
        }
    }

    @Override
    public int poll() {
        int max = heap[0];
        heap[0] = heap[lastPos];
        heap[lastPos] = Integer.MIN_VALUE;
        lastPos--;
        heapifyTopDown(0);
        return max;
    }

    private void heapifyTopDown(int index) {
        if (index >= heap.length) {
            return;
        }

        int leftP = 2*index+1;
        int rightP = 2*index+2;

        if (leftP >= heap.length) {
            return;
        }

        if (heap[leftP] > heap[rightP]) {
            if (heap[index]  < heap[leftP]) {
                int temp = heap[leftP];
                heap[leftP]  = heap[index];
                heap[index] = temp;
                heapifyTopDown(leftP);
            }
        } else {
            if (heap[index]  < heap[rightP]) {
                int temp = heap[rightP];
                heap[rightP]  = heap[index];
                heap[index] = temp;
                heapifyTopDown(rightP);
            }
        }
    }

    @Override
    public boolean hasElement() {
        return lastPos>=0;
    }
}

interface HeapI {
    ArrayList<Integer> heapSort();
    void heapifyAll();

    int poll(); // this will retrive and remove element

    boolean hasElement();
}
