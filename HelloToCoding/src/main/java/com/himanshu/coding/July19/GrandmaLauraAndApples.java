package com.himanshu.coding.July19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GrandmaLauraAndApples {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int p = Integer.parseInt(str[1]);

        str = new String[n];

        for (int i=0;i<n;i++) {
            str[i]=br.readLine();
        }

        long current=1;
        double sold=0.5;
        for (int i=str.length-2;i>=0;i--) {
            if (str[i].equals("halfplus")) {
                current=current*2+1;
                sold = sold + current/2.0;
            } else {
                current = current*2;
                sold = sold+ current/2.0;
            }
        }
        //System.out.println("Sold"+ sold);

        System.out.print((long)(sold*p));



    }
}
