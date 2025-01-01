package com.himanshu.coding.july20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TheBestGift {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        int arr[] = new int[m];
        str = br.readLine().split(" ");

        for (int i=0;i<n;i++)  {
            arr[Integer.parseInt(str[i])-1]++;
        }

        System.out.println(getNumberOfWays2(arr, n));
    }

    private static long getNumberOfWays1(int[] arr, int n) {
        long count=0;
        for (int i=0;i<arr.length;i++) {
            count = count + (arr[i]*(n-arr[i]));
        }
        return count/2;
    }

    static long sumNatual(int n) {
        return ((long)n*(n+1l))/2;
    }

    private static long getNumberOfWays2(int[] arr, int n) {
        long t =  sumNatual(n);
        for (int x:arr) {
            t -= sumNatual(x);
        }
        return t;
    }
}
