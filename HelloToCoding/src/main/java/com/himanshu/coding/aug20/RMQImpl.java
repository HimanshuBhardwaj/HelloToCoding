package com.himanshu.coding.aug20;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RMQImpl {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n =2;

        int [] arr = new int[2];

        arr[0]=2;
        arr[1]=3;
/*        arr[2]=1;
        arr[3]=5;
        arr[4]=7;
        arr[5]=9;
        arr[6]=5;
        arr[7]=-1;
        */

        SQRTDecomposition sqrtDecomposition = new SQRTDecomposition(arr);
        System.out.println(sqrtDecomposition.getMinimum(1,1));
    }
}

class SQRTDecomposition implements RMQ {
    int [] arr;
    int decompositionArraySize;
    int numDecomposition;
    int [] sqrtArray;

    public SQRTDecomposition(int [] arr) {
        this.arr = arr;
        preprocess(arr);
    }

    private void preprocess(int[] arr) {
        decompositionArraySize = (int)Math.sqrt(arr.length);
        numDecomposition = (arr.length/decompositionArraySize) + ((arr.length % decompositionArraySize==0)?0:1);
        sqrtArray  = new int[numDecomposition];
        for (int i=0;i<sqrtArray.length;i++) {
            sqrtArray[i] = Integer.MAX_VALUE;
        }
        for (int i=0;i<arr.length;i++) {
            sqrtArray[getIndex(i)] = Math.min(sqrtArray[getIndex(i)], arr[i]);
        }
    }

    private int getIndex(int pos) {
        return  pos/decompositionArraySize;
    }

    @Override
    public int getMinimum(int start, int end) {
        int startIndex = getIndex(start);
        int endIndex = getIndex(end);

        int minimum = Integer.MAX_VALUE;

        for (int i=startIndex+1;i<=endIndex-1;i++) {
            minimum = Math.min(sqrtArray[i],minimum);
        }

        for (int i=0;getIndex(i) == startIndex;i++) {
            minimum = Math.min(arr[i],minimum);
        }

        for (int i=end;getIndex(i) == endIndex;i--) {
            minimum = Math.min(arr[i],minimum);
        }

        return minimum;
    }
}

interface RMQ {
    int  getMinimum(int start, int end);
}
