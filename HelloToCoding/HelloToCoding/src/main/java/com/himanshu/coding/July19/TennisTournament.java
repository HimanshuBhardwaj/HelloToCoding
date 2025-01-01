package com.himanshu.coding.July19;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TennisTournament {
    static long bottle=0;
    static long towel=0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String []str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int b = Integer.parseInt(str[1]);
        int p = Integer.parseInt(str[2]);

        findSumBottles(n,b,p);

        System.out.print(bottle+" "+n*p);
    }

    private static void findSumBottles(int n, int b, int p) {
        if (n==1) {
            return;
        }
        int k = maxPow2(n);
        bottle+= (k/2)*((2*b)+1);

        findSumBottles((k/2) + n-k,b,p);
    }

    public static int maxPow2(int n) {
        int a=1;
        while ((2*a) <= n ) {
            a=a*2;
        }
        return a;
    }
}
