package com.himanshu.coding.july17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GabrielAndCaterpillar {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");
        int h1 = Integer.parseInt(str[0]);
        int h2 = Integer.parseInt(str[1]);
        str = br.readLine().split(" ");

        int a = Integer.parseInt(str[0]);
        int b = Integer.parseInt(str[1]);
        int day=1;

        long l = h2-h1;

        l = l-(8*a);

        if (l<=0) {
            System.out.print(0);
            return;
        }

        l=l+(2*b);

        for (;;day++) {

            l = l+(10*b);
            l=l-(12*a);

            if (l <=0) {
                System.out.print(day);
                return;
            }
            l = l+(2*b);

            if (day== 100004) {
                System.out.println(-1);
                return;
            }
        }
    }
}
