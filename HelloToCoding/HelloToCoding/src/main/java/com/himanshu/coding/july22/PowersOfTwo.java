package com.himanshu.coding.july22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

public class PowersOfTwo {
    public static void main(String[] args) throws IOException {
        HashSet<Long> powers = allPowerOf2();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        String [] str = br.readLine().split(" ");
        long a[] = new long[str.length];
        TreeMap<Long, Long> numbers = new TreeMap<>();

        for (int i=0;i<a.length;i++) {
            a[i] = Long.parseLong(str[i]);
            if (numbers.containsKey(a[i])) {
                numbers.put(a[i],numbers.get(a[i])+1);
            } else {
                numbers.put(a[i],1l);
            }
        }

        long count=0;
        long count1=0;

        for (Map.Entry<Long, Long> entry:numbers.entrySet()) {
            for (long pow:powers) {
                long rem = pow-entry.getKey();
                if (numbers.containsKey(rem)) {
                    if (rem==entry.getKey()) {
                        count1 += (((entry.getValue()-1)*entry.getValue())/2);
                    } else {
                        count += entry.getValue()*numbers.get(rem);
                    }
                }
            }
        }

        System.out.print((count/2)+count1);
    }

    private static HashSet<Long> allPowerOf2() {
        HashSet<Long> powers = new HashSet<>();
        long p = 1l;
        powers.add(p);
        for (int i=0;i<50;i++) {
            p = p<<1;
            powers.add(p);
        }
        return powers;
    }
}
