package com.himanshu.coding.june10;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class BubbleSort {
    public static void main(String[] args) {
        int [] arr = new int[10];
        int [] arrC = new int[10];
        int [] arrB = new int[10];
        int [] arrI = new int[10];
        int [] arrMerge = new int[10];

        for (int i=0;i<arr.length;i++){
            arr[i]=(int)(Math.random()*10000);
        }
        arrC = arr.clone();
        arrB = arr.clone();
        arrI = arr.clone();
        arrMerge = arr.clone();

        SortUtil.print(arr);
        System.out.println("Bubble Sort");
        SortUtil.bubbleSort(arrB);
        SortUtil.print(arrB);

        System.out.println("Array Sort");
        Integer [] arrCO= new Integer[arrC.length];
        for (int i=0;i<arrCO.length;i++)
            arrCO[i]=arr[i];


        Arrays.sort(arrCO, Collections.reverseOrder());
        for (Integer X:arrCO) {
            System.out.print(X+", ");
        }
        System.out.println();

        System.out.println("Insertion sort");
        SortUtil.insertionSort(arrI);
        SortUtil.print(arrI);

        System.out.println("Merge Sort");
        SortUtil.mergeSort(arrMerge);
        SortUtil.print(arrMerge);
    }
}

class SortUtil {

    public static void print(int[] arr) {
        System.out.println("--ARR Leanth--"+arr.length);
        for (int x:arr) {
            System.out.print(x+", ");
        }
        System.out.println();
    }

    public static void bubbleSort(int[] arr) {

        for (int i=0;i<arr.length;i++){
            for (int j=1;j<arr.length-i;j++)
            {
                if (arr[j]>arr[j-1]) {
                    int temp=arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=temp;
                }
            }
        }
    }

    public static void insertionSort(int [] arr) {
        for (int i=0;i<arr.length;i++) {
            for (int j=i;j>0;j--) {
                if (arr[j]>arr[j-1]) {
                    int temp=arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=temp;
                } else {
                    break;
                }

            }
        }
    }

    public static void mergeSort(int arr[]) {
        System.out.println("Before merge sort");
        print(arr);
        int arrCopy[]= mergeSortInternal(0, arr.length-1, arr);
        for (int i=0;i<arr.length;i++) {
            arr[i] = arrCopy[i];
        }
    }

    private static int[] mergeSortInternal(int start, int end, int[] arr) {
        if (start>end || arr==null || start<0 || end>=arr.length) {
            return null;
        }

        if (start==end) {
            int [] arrC = new int[1];
            arrC[0]=arr[start];
            return arrC;
        }

        if (end == (start+1) ) {
            int [] arrC = new int[2];
            arrC[0] = Math.min(arr[start],arr[end]);
            arrC[1] = Math.max(arr[start],arr[end]);
            return arrC;
        }

        int mid = (start+end)/2;

        int arrL[] = mergeSortInternal(start,mid,arr);
        int arrR[] = mergeSortInternal(mid+1,end,arr);

        return merge(arrL,arrR);
    }

    private static int[] merge(int[] arrL, int[] arrR) {
        if (arrL==null ||arrL.length==0){
            return arrR.clone();
        }

        if (arrR==null || arrR.length==0) {
            return arrL.clone();
        }

        int temp[] = new int[arrL.length+arrR.length];

        int a=0;int b=0;
        int pos=0;

        while ( (a<arrL.length) && (b<arrR.length) ) {
            if (arrL[a] <= arrR[b]) {
                temp[pos++]=arrL[a++];
            } else {
                temp[pos++] = arrR[b++];
            }
        }

        if (a==arrL.length) {
            for (;b<arrR.length;b++){
                temp[pos++]=arrR[b];
            }
        }

        if (b==arrR.length) {
            for (;a<arrL.length;a++){
                temp[pos++]=arrL[a];
            }
        }
        return temp;
    }
}