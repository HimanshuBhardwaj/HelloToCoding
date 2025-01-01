package com.himanshu.coding.july22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ZSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int [] ar = toIntArray(br.readLine().split(" "));
        int sa[] = new int[ar.length];

        int pos=0;
        for (int i=0;i<sa.length;i=i+2) {
            sa[i]=ar[pos++];
        }

        for (int i=1;i<sa.length;i=i+2) {
            sa[i]=ar[pos++];
        }

        for (int x:sa) {
           System.out.print(x+" ");
        }
    }

    private static int[] toIntArray(String[] s) {
        int [] a = new int[s.length];
        for (int i=0;i< s.length;i++) {
            a[i]= Integer.parseInt(s[i]);
        }
        //System.out.println(a.length);
        Arrays.sort(a);
        return a;
    }
}
