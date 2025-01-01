package dec24;

// 12:20
public class HeapSort {
    public static void main(String[] args) {
        SortI sortI = new HeapSortImpl();
        int [] arr = {5};
        sortI.sort(arr);

        for (int c:arr) {
            System.out.print(c+"\t");
        }
    }
}

class HeapSortImpl implements SortI {

    @Override
    public void sort(int[] arr) {
        HeapI heapI = new HeapImpl(arr);
        for (int i=0;i<arr.length;i++) {
            arr[i]=heapI.poll();
        }
    }
}

class HeapImpl implements HeapI {
    int arr[];
    int [] heap;
    int heapSize;
    int endIndex;


    public HeapImpl(int arr[]) {
        this.arr = arr.clone();
        heapSize = 2*((int) Math.pow(2,Math.ceil(Math.log(arr.length)/Math.log(2))))-1;
        heap = new int[heapSize];
        endIndex = arr.length-1;

        for (int i=0;i<heapSize;i++) {
            heap[i] = (i<arr.length)?arr[i]:Integer.MAX_VALUE;
        }

        heapifyAll();
    }

    private void heapifyAll() {

        for (int i=(heap.length/2)-1;i>=0;i--) {
            heapify(i);
        }
    }

    private void heapify(int index) {
        int lp = 2*index+1;
        int rp = lp+1;

        if (rp >= heap.length) {
            return;
        }

        if (heap[lp] < heap[rp]) {
            if (heap[index]<heap[lp]) {

            } else {
                swapHeap(lp,index);
                heapify(lp);
            }
        } else {
            if (heap[index]<heap[rp]) {

            } else {
                swapHeap(rp,index);
                heapify(rp);
            }
        }
    }

    private void swapHeap(int lp, int index) {
        int temp = heap[lp];
        heap[lp] = heap[index];
        heap[index] = temp;
    }


    @Override
    public int poll() {
        int top = heap[0];
        heap[0] = heap[endIndex];
        heap[endIndex] = Integer.MAX_VALUE;
        endIndex--;
        heapify(0);
        return top;
    }
}

interface HeapI {
    int poll();
}
