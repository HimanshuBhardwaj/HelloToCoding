package com.himanshu.coding.july23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AliceBobTwoTeams {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long [] streangth = new long[n];
        String [] str = br.readLine().split(" ");
        for (int i=0;i<n;i++) {
            streangth[i]=Long.parseLong(str[i]);
        }
        String order = br.readLine();

        //CommulativePowerOfA
        long powerA [] = new long[n];

        //commuative power of B
        long [] powerB = new long[n];
        long totalA=0;
        long totalB=0;


        for (int i=0;i<n;i++) {
            if (order.charAt(i)=='A') {
                if (i==0) {
                    powerA[i] = streangth[i];
                    totalA=streangth[i];
                    powerB[i]=0;
                } else {
                    powerA[i] = totalA+streangth[i];
                    totalA = powerA[i];
                    powerB[i]=powerB[i-1];
                }
            } else {
                if (i==0) {
                    powerB[i] = streangth[i];
                    totalB=powerB[i];
                    powerA[i]=0;
                } else {
                    powerB[i] = totalB+streangth[i];
                    totalB=powerB[i];
                    powerA[i]=powerA[i-1];
                }
            }
        }

        long maximumBobPossible = Math.max(totalB,totalA);

        for (int i=0;i<n;i++) {
            long temp = totalB-powerB[i]+powerA[i];
            maximumBobPossible = Math.max(maximumBobPossible, temp);
            temp = powerB[i]+(totalA-powerA[i]);
            maximumBobPossible = Math.max(maximumBobPossible, temp);
        }




        System.out.print(maximumBobPossible);
    }
}
