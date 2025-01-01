package com.himanshu.coding.july20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GrandfatherDovletsCalculator {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int [] arr  = {6,2,5,5,4,5,6,3,7,6};

        String [] str = br.readLine().split(" ");
        int a = Integer.parseInt(str[0]);
        int b = Integer.parseInt(str[1]);

        long count=0;

        for (int i=a;i<=b;i++) {
            count+=getLightingLights(i,arr);
        }

        System.out.println(count);
    }

    private static long getLightingLights(int i, int[] arr) {
        long sum=0;
        while (i>0) {
            sum += arr[i % 10];
            i = i/10;
        }
        return sum;
    }
}
