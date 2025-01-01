package com.himanshu.coding.aug18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//https://codeforces.com/contest/803/problem/C
public class MaximalGCD {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String [] str = br.readLine().split(" ");
        long n = Long.parseLong(str[0]);
        long k = Long.parseLong(str[1]);

        ArrayList<Long> divisors = (ArrayList<Long>) getAllDivisors(n);

        for(long d:divisors) {
            if(proceed(d,k, n)) {
                return;
            }
        }

        System.out.print(-1);

    }

    private static boolean proceed(long d, long k, long n) {
        long sum = (k*(k-1)*d)/2;
        long remaining = n-sum;

        if (k!=1) {
            if ((remaining>0) && (sum>0) && (sum<n) && (remaining > (k - 1) * d)) {
                for (int i = 1; i < k; i++) {
                    System.out.print(i * d + " ");
                }
                System.out.print(remaining);
                return true;
            }
        } else {
            System.out.println(n);
            return true;
        }
        return false;
    }

    static List<Long> getAllDivisors(long n) {
        ArrayList<Long> divisors = new ArrayList<>();

            divisors.add(1l);


        long sqrt = (long)Math.sqrt(n);

        for (int i=2;i<=sqrt+1;i++) {
            if ((n%i) == 0) {
                divisors.add((long)i);
                divisors.add(n/i);
            }
        }
        Collections.sort(divisors, Comparator.reverseOrder());
        return divisors;
    }

    long gcd(long a, long b) {
        if (a>b) {
            return gcd(b,a);
        }

        if (a==0) {
            return b;
        }

        if (a==1) {
            return 1;
        }

        return gcd(b%a,a);
    }
}
