package com.himanshu.coding.july23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class NearestVectors {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Number> numbers = new ArrayList<>();

        for (int i=0;i<n;i++) {
            String [] str = br.readLine().split(" ");
            numbers.add(new Number(Integer.parseInt(str[0]), Integer.parseInt(str[1]), i+1));
        }

        Collections.sort(numbers);

        double minDistance = Double.MAX_VALUE;
        int pos=-1;

        for (int i=0;i<n;i++) {
            double degreediff = Math.abs(numbers.get(i).degree-numbers.get((i-1+n)%n).degree);
            double diff = Math.min(degreediff, (2*Math.PI)-degreediff);


            if (Double.compare(minDistance,diff)>0){
                minDistance=diff;
                pos=i;
            }
        }

        System.out.print(numbers.get(pos).pos+" "+numbers.get((pos-1+n)%n).pos);
    }
}

class Number implements Comparable<Number>{
    int x;
    int y;
    int pos;
    double degree;


    public Number(int x, int y, int pos) {
        this.x=x;
        this.y=y;
        this.pos=pos;
        this.degree=Math.atan2(this.y,this.x);
    }

    @Override
    public int compareTo(Number o) {
        return Double.compare(Math.atan2(y,x),Math.atan2(o.y,o.x));
    }
}