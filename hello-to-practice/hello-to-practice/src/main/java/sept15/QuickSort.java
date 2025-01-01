package sept15;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {0,5,11,1,-1,20,-111111111,-111};
        /*QuickSortImpl quickSort = new QuickSortImpl(arr);
        quickSort.sort();
        quickSort.print();*/

        HeapSort heapSort = new HeapSort(arr);
        heapSort.printHeap();

        System.out.println();

        while (heapSort.hasNext()) {
            System.out.print(heapSort.peek()+", ");
        }
        System.out.println();
    }
}


class HeapSort implements Sort {

    int [] arr;
    int [] heap;
    int lastElement;

    boolean hasNext() {
        return lastElement>=0;
    }
    int peek() {
        int min = heap[0];

        heap[0]=heap[lastElement];
        heap[lastElement] = Integer.MAX_VALUE;
        heapConsistent(0);
        lastElement--;

        return min;
    }

    int poll() {
        return heap[0];
    }

    public HeapSort(int [] arr) {
        this.arr = arr;
        int heapSize = (2*(int)Math.pow(2,Math.ceil(Math.log(arr.length)/Math.log(2))))-1;
        heap = new int[heapSize];

        for (int i=0;i<heapSize;i++) {
            if (i< arr.length) {
                heap[i] = arr[i];
                lastElement = i;
            } else {
                heap[i] = Integer.MAX_VALUE;
            }
        }

        for (int i=(heapSize/2)-1;i>=0;i--) {
            int leftParent = (2*i + 1);
            int rightParent = (2*i + 2);
            makeHeapConsistentL(i,leftParent,rightParent);
        }
    }

    void heapConsistent(int index) {
        while (index < heap.length ) {
            int leftChild = (2*index + 1);
            int rightChild = (2*index + 2);

            if (leftChild >= heap.length ) {
                break;
            }

            if (rightChild >= heap.length) {
                if (heap[leftChild] < heap[index]) {
                    makeHeapConsistentL(index,leftChild,rightChild);
                }
                break;
            }

            if (heap[leftChild] < heap[rightChild]) {
                makeHeapConsistentL(index,leftChild,rightChild);
                index=leftChild;
            } else {
                makeHeapConsistentL(index,leftChild,rightChild);
                index=rightChild;
            }
        }
    }

    private void makeHeapConsistentL(int index, int leftChild, int rightChild) {
        if (heap[index] > Math.min(heap[leftChild], heap[rightChild])) {
            if (heap[leftChild] < heap[rightChild]) {
                int temp = heap[leftChild];
                heap[leftChild] = heap[index];
                heap[index] = temp;
            } else {
                int temp = heap[rightChild];
                heap[rightChild] = heap[index];
                heap[index] = temp;
            }
        }
    }

    void printHeap() {
        for (int x:heap) {
            System.out.print(x+", ");
        }
        System.out.println();
    }

    @Override
    public void sort() {

    }


}

class QuickSortImpl implements Sort {
    int [] arr;

    public QuickSortImpl(int [] arr) {
        this.arr = arr;
    }

    @Override
    public void sort() {
        sortHelper(0,arr.length-1,arr);
    }

    private void sortHelper(int s, int e, int[] arr) {
        if (s==e || s>=e || s<0 || e<0) {
            return;
        }

        int pivotPos = partition(s,e,arr);
        sortHelper(s,pivotPos-1,arr);
        sortHelper(pivotPos+1,e,arr);

    }

    public int partition(int s, int e, int[] arr) {
        if (s==e) {
            return s;
        }

        if (s<0 || e<0) {
            return -1;
        }

        int pivot = arr[e];
        int l=s-1;

        for (int i=s;i<e;i++) {
            if (arr[i] <= pivot) {
                l++;
                swap(l, i, arr);
            }
        }

         swap(++l,e,arr);


        return l;
    }

    void swap(int a, int b, int []arr) {
        int temp = arr[a];
        arr[a]=arr[b];
        arr[b] = temp;
    }

    void print() {
        for (int x:arr) {
            System.out.print(x+", ");
        }
        System.out.println();
    }
}

interface Sort {
    void sort();
}
