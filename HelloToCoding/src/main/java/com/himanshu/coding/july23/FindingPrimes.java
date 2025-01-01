package com.himanshu.coding.july23;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class FindingPrimes {
    public static void main(String[] args) {
        int n=10000000;
        Date now = new Date();
        long s1 = now.getTime();
        Set<Integer> allPrimes = new PrimeFinderBruteForce().findAllPrimes(n);
        now = new Date();
        long s2 = now.getTime();
        Set<Integer> allPrimeSmart = new PrimeFinderSeive().findAllPrimes(n);
        now = new Date();
        long s3 = now.getTime();
        System.out.println(allPrimes.equals(allPrimeSmart));

        System.out.println(((long)s2-s1)+"\t"+((long)s3-s2));
    }
}

class PrimeFinderSeive implements PrimeFinder {

    @Override
    public Set<Integer> findAllPrimes(int n) {
        TreeSet<Integer> prime = new TreeSet<>();
        boolean [] arr = new boolean[n+1];
        arr[0]=true;
        arr[1]=true;


        for (int i=0;i<arr.length;i++) {
            if (arr[i]==false) {
                prime.add(i);

                int count = 2;

                while ( (count * i) < arr.length) {
                    arr[count * i] = true;
                    count++;
                }
            }
        }

        return prime;
    }
}



class PrimeFinderBruteForce implements PrimeFinder{
    @Override
    public Set<Integer> findAllPrimes(int n) {
        TreeSet<Integer> set = new TreeSet<>();

        for (int i=0;i<=n;i++) {
            if (isPrime(i)) {
                set.add(i);
            }
        }
        return set;
    }

    private boolean isPrime(int n) {
        if (n<=1) {
            return false;
        }

        if (n <=3) {
            return true;
        }

        int sqrt = (int)(Math.sqrt(n)+1);

        for (int i=2;i<=sqrt;i++) {
            if (n%i ==0) {
                return false;
            }
        }
        return true;
    }
}

interface PrimeFinder {
    Set<Integer> findAllPrimes(int n);
}