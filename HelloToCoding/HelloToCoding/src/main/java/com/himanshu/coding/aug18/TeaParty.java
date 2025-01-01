package com.himanshu.coding.aug18;

import com.himanshu.coding.june19.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TeaParty {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int w = Integer.parseInt(str[1]);

        str = br.readLine().split(" ");

        ArrayList<Cup> cups = new ArrayList<>();

        for (int i=0;i<n;i++) {
            Cup cup =new Cup(i,Integer.parseInt(str[i]));
            cups.add(cup);
            int teaToPour = (cup.maxCapacity/2)+ (cup.maxCapacity%2);
            cup.poured = teaToPour;
            w-=teaToPour;
        }

        if (w<0) {
            System.out.println(-1);
            return;
        }

        Comparator<Cup> comparator = new Comparator<Cup>() {
            @Override
            public int compare(Cup o1, Cup o2) {
                return Integer.compare(o1.maxCapacity,o2.maxCapacity)*-1;
            }
        };

        Collections.sort(cups,comparator);

        for (Cup c: cups) {
            int remainingCapacity  = c.maxCapacity-c.poured;

            if (w <= remainingCapacity) {
                c.poured+=w;
                break;
            } else {
                w -= remainingCapacity;
                c.poured+=remainingCapacity;
            }
        }

        comparator = new Comparator<Cup>() {
            @Override
            public int compare(Cup o1, Cup o2) {
                return Integer.compare(o1.index,o2.index);
            }
        };
        Collections.sort(cups,comparator);

        StringBuffer sb = new StringBuffer();
        for (Cup c: cups) {
            sb.append(c.poured+" ");
        }

        System.out.println(sb.toString());



    }
}

class Cup {
    int index;
    int poured;
    int maxCapacity;

    public Cup(int i, int mc) {
        index = i;
        maxCapacity=mc;
    }
}
