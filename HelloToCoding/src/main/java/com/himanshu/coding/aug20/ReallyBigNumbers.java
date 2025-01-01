package com.himanshu.coding.aug20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReallyBigNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");

        long n = Long.parseLong(str[0]);
        long s = Long.parseLong(str[1]);

        if (!isReallyBig(n,s)) {
            System.out.print(0);
            return;
        }

        long sirdtRBN = firstReallyBigNum(0,n,s);

        if (sirdtRBN == -1) {
            System.out.print(0);
        } else {
            System.out.println(n-sirdtRBN+1);
        }
    }

    private static long firstReallyBigNum(long start, long end, long s) {
        if (isReallyBig(start, s)) {
            return start;
        }

        if (start == end || (end<=9)) {
            return -1;
        }

        if (start == (end - 1)){
            if (isReallyBig(end, s)) {
                return end;
            } else {
                return -1;
            }
        }

        long mid = (start+end)/2;

        if (isReallyBig(mid,s)) {
            return firstReallyBigNum(start,mid,s);
        } else {
            return firstReallyBigNum(mid+1,end,s);
        }
    }

    static boolean isReallyBig(long n, long s) {
        long sumD = sumdigit(n);
        return (n-sumD)>=s;
    }

   static  private long sumdigit(long n) {
        long sd=0;

        while (n>0) {
            sd = sd + (n%10);
            n = n/10;
        }
        return sd;
    }
}
