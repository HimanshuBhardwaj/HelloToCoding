package com.himanshu.coding.july23;

import java.util.ArrayList;

public class EulerPhiDunction {
    public static void main(String[] args) {
        System.out.println(phi(5));
        System.out.println(phiBrute(5));

    }

    static long phi(int x) {
        long phi=x;
        ArrayList<Integer> factors = allFactors(x);

        for (int factor:factors) {
            if (factor<x) {
                phi = (phi - (phi / factor));
            }
        }

        return phi;
    }

    static ArrayList<Integer> allFactors(long x) {
        int a=(int)Math.sqrt(x);
        ArrayList<Integer> allPrimes = getAllPrimes(x);

        ArrayList<Integer> allFactos = new ArrayList<>();
        //allFactos.add(1);

        for (int p:allPrimes) {
            if (x%p == 0) {
                allFactos.add(p);
            }
        }

        System.out.println("All factors:"+ x+"\t"+allFactos);
        return allPrimes;
    }

   static private ArrayList<Integer> getAllPrimes(long x) {
        boolean [] a = new boolean[(int)x+1];

        a[0]=true;
        a[1]=true;

        ArrayList<Integer> primes = new ArrayList<>();

        for (int i=2;i<a.length;i++) {
            if (a[i]==false) {
                primes.add(i);
                int count=2;
                while (i*count <a.length) {
                    a[i*count]=true;
                    count++;
                }
            }
        }
       System.out.println("All primes: "+x+"\t"+primes);
        return primes;
    }

    //number which are coprime to x and are less than x
   static long phiBrute(int x) {
        int count=0;

        for (int i=2;i<=x;i++) {
            if (x%i!=0) {
                count++;
            }
        }
        return count;
    }
}
