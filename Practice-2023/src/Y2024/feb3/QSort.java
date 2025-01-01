package Y2024.feb3;

import java.util.Collections;


/**
 * @author Himanshu Bhardwaj
 * @Date 2/3/2024
 */
public class QSort {
    public static void main(String[] args) {
        int arr [] = {10,1,9};
        Sort sort = new QuickSort();
        sort.sort(arr);

        for (int z: arr) {
            System.out.print(z+", ");
        }
        System.out.println();
    }
}

class QuickSort implements Sort {

    @Override
    public void sort(int[] arr) {
        sortHelper(0,arr.length-1,arr);
    }

    private void sortHelper(int s, int e, int[] arr) {
        if (s>=e) {
            return;
        }

        // this is position of pivot
        int pivot = partition(arr,s,e);
        sortHelper(s,pivot-1,arr);
        sortHelper(pivot+1,e,arr);
    }

    int partition(int [] arr, int start, int end) {
        int j=start-1;
        int piv = arr[end];
        for (int i=start;i<end;i++) {
            if (arr[i] < piv) {
                j++;
                swap(arr,j,i);
            }
        }
        swap(arr,j+1,end);
        return j+1;
    }

    private void swap(int[] arr, int j, int i) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}

interface Sort {
    void sort(int [] arr);
}