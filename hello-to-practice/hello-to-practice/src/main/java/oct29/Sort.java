package oct29;


public class Sort {
    public static void main(String[] args) {
        int [] elements = new int[1];
        elements[0]=-1;

        QuickSort quickSort = new QuickSort();
        quickSort.sort(elements);

        for (int x:elements) {
            System.out.print(x+", ");
        }
        System.out.println();
    }
}

class QuickSort implements SortI {

    @Override
    public void sort(int[] elements) {
        sortHelper(elements,0,elements.length-1);
    }

    private void sortHelper(int [] elements, int start, int end) {
        if (start <0 || end>=elements.length || start >= end) {
            return;
        }

        int partition = partitionArray(elements, start, end);
        sortHelper(elements,start,partition-1);
        sortHelper(elements, partition+1, end);
    }

    private int partitionArray(int [] elements, int start, int end) {
        int i=start-1;

        for (int j=start;j<end;j++) {
            if (elements[j] < elements[end]) {
                i++;
                int temp = elements[j];
                elements[j]=elements[i];
                elements[i]=temp;
            }
        }

        swap(elements, i+1,end);
        return i+1;
    }

    private void swap(int[] elements, int i, int end) {
        int temp = elements[i];
        elements[i] = elements[end];
        elements[end] = temp;
    }
}

interface SortI {
    void sort(int [] elements);
}
