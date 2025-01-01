package com.himanshu.coding.aug25;

public class QuickSort {
    public static void main(String[] args) {

    }

    void sort(int start, int end, int [] arr) {
        if (start<0 || end>=arr.length || start==end) {
            return;
        }

        if (end == (start+1)) {
            if (arr[start]> arr[end]) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }
            return;
        }

        int partition = arr[(start+end)/2 ];
        int pos = partition(start,end,arr,partition);

        sort(start,pos-1,arr);
        sort(pos+1,end,arr);
    }

    private int partition(int start, int end, int[] arr,int partitionKey) {
        return 0;
    }
}
