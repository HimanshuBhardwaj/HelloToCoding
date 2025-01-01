package com.himanshu.coding.july16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class USBFlashDrives {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[n];
        for (int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr, Comparator.reverseOrder());

        int count=0;

        while (m>0) {
            m -= arr[count];
            count++;
        }

        System.out.print(count);

    }
}
