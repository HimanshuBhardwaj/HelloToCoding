package com.himanshu.coding.july20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class HDDIsOutdatedTechnology {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String [] str = br.readLine().split(" ");
        ArrayList<Sector> sectors = new ArrayList<>();

        for (int i=0;i<n;i++) {
            sectors.add(new Sector(i,Integer.parseInt(str[i])));
        }
        Collections.sort(sectors);

        long count=0;

        for (int i=1;i<sectors.size();i++) {
            count = count + Math.abs(sectors.get(i-1).n-sectors.get(i).n);
        }

        System.out.print(count);



    }

}


class Sector implements Comparable<Sector> {
    int n;
    int f;

    public Sector(int i, int parseInt) {
        this.n=i;
        this.f=parseInt;
    }

    @Override
    public int compareTo(Sector o) {
        return Integer.compare(this.f,o.f);
    }
}