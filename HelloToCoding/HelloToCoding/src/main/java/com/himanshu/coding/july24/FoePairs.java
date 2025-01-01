package com.himanshu.coding.july24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FoePairs {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n,m;
        String [] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);

        int a[] = new int[n];
        str = br.readLine().split(" ");

        for (int i=0;i<a.length;i++) {
            a[i] = Integer.parseInt(str[i]);
        }

        //Arrays.sort(a);

        ArrayList<Pair> pairs = new ArrayList<>();

        for (int i=0;i<m;i++) {
            str = br.readLine().split(" ");
            pairs.add(new Pair(Integer.parseInt(str[0]), Integer.parseInt(str[1])));
        }

        Collections.sort(pairs);

        System.out.println("------------------------------");
        int count=0;
        for (int i=0;i<n;i++) {
            for (int j=i;j<n;j++) {
                if (!contains(a[i],a[j],pairs)) {
                    System.out.println(a[i]+" "+a[j]);
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static boolean contains(int s, int e, ArrayList<Pair> pairs) {
        for (Pair p:pairs) {
            if ( (p.s >=s && p.e<=e)) {
                return true;
            }
        }
        return false;
    }
}

class Pair implements Comparable<Pair> {
    int s;
    int e;

    public Pair(int s, int e){
        this.s = Math.min(s,e);
        this.e = Math.max(s,e);
    }

    @Override
    public int compareTo(Pair o) {
        return Integer.compare(this.s,o.s);
    }
}
