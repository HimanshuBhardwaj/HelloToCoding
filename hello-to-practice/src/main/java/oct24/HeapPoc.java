package oct24;

public class HeapPoc {
    public static void main(String[] args) {
        int [] arr = {7, 5, 3,  8, 5, 2};

        Heap heap = new Heap(arr);
        heap.print();

        while (heap.lastIndex >=0) {
            System.out.println(heap.peekMin());
        //    heap.print();
        }
    }
}

class Heap implements PriorityQueue {
    int [] heapElement;
    int lastIndex;
    int size;

    public Heap(int [] arr) {
        size = (int)Math.pow(2, Math.ceil(Math.log((arr.length +1.0)/2.0)/Math.log(2)));
        size = 2*size -1 ;

        heapElement = new int[size];

        for (int i=0;i<heapElement.length;i++) {
            if (i<arr.length) {
                heapElement[i] = arr[i];
                lastIndex = i;
            } else {
                heapElement[i] = Integer.MAX_VALUE;
            }
        }

        for (int i=(heapElement.length/2)-1;i>=0;i--) {
            heapifyIndex(i);
        }
    }

    public void heapifyIndex( int index) {

        while (index < heapElement.length) {
            int leftChild = (2*index)+1;
            int rightChild = (2*index)+2;

            if (leftChild >= heapElement.length) {
                break;
            }

            if (heapElement[leftChild] > heapElement[rightChild]) {
                if(heapElement[index] > heapElement[rightChild]) {
                    int temp = heapElement[index];
                    heapElement[index] = heapElement[rightChild];
                    heapElement[rightChild] = temp;
                    index = rightChild;
                } else {
                    break;
                }
            } else {
                if(heapElement[index] > heapElement[leftChild]) {
                    int temp = heapElement[index];
                    heapElement[index] = heapElement[leftChild];
                    heapElement[leftChild] = temp;
                    index = leftChild;
                } else {
                    break;
                }
            }
        }
    }

    public void print() {
        System.out.println("Printing Heap...");
        for (int i=0;i<heapElement.length;i++) {
            System.out.print(heapElement[i]+"\t");
        }
        System.out.println();
    }

    @Override
    public int pollMin() {
        return heapElement[0];
    }

    @Override
    public int peekMin() {
        if (lastIndex >=0) {
            int min = heapElement[0];
            heapElement[0] = heapElement[lastIndex];
            heapElement[lastIndex--] = Integer.MAX_VALUE;
            heapifyIndex(0);
            return min;
        }
        return Integer.MAX_VALUE;
    }

}

interface PriorityQueue {
    int pollMin();
    int peekMin();
}
