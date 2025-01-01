package com.himanshu.coding.july20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class DinnerWithEmma {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        ArrayList<Integer> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;

        for (int i=0;i<n;i++) {
            min = Integer.MAX_VALUE;
            str = br.readLine().split(" ");
            for (int j=0;j<str.length;j++) {
                min = Math.min(min,Integer.parseInt(str[j]));
            }
            list.add(min);
        }

        Collections.sort(list);

        System.out.print(list.get(list.size()-1));
    }
}
