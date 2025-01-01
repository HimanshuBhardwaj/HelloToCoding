package com.himanshu.coding.july23;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Exponentiation {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long val=23;
        long n=11;
        System.out.println(powerBruteForce(val,n)==power(val,n));
    }

    static public long powerBruteForce(long a, long b) {
        if (b==0) {
            return 1;
        }
        long po=1;
        for (int i=0;i<b;i++) {
            po *=a;
        }
        return po;
    }

    //a^b
   static public long power(long a, long b) {
        if (b==0) {
            return 1;
        }
        if (b==1) {
            return a;
        }

        if (b==2) {
            return a*a;
        }

        long pow = power(a,b/2);
        if (b%2==0) {
            return pow*pow;
        } else {
            return pow*pow*a;
        }
    }
}
