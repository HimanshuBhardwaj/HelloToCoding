package com.himanshu.coding.july20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NewSkateboard {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char [] character = br.readLine().toCharArray();
        int sumC[] = new int[4];

        int sum=character[0]-'0';
        sumC[sum%4]=1;

        for (int i=1;i<character.length;i++) {
            sum+=character[i]-'0';
            sumC[sum%4]++;
        }

        System.out.println(C2(sumC[0]) +
                C2(sumC[1])+
                C2(sumC[2])+
                C2(sumC[3]));
        int cbf=0;
        for (int i=0;i<character.length;i++) {
            for (int j=i;j<character.length;j++) {
                long ss=0;
                for (int k=i;k<=j;k++) {
                    ss += character[k]-'0';
                }
                if ( (ss%4) ==0) {
                  cbf++;
                }
            }
        }

        System.out.println(cbf);

    }




static    long C2(int n) {
        return ((long)n*(n-1l))/2;
    }
}
