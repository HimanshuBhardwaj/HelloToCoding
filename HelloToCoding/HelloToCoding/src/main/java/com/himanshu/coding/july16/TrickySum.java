package com.himanshu.coding.july16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TrickySum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(getSum(n));

            if (t != 0) {
                sb.append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    private static long getSum(long n) {
        long greatestPower2 = (long)Math.floor(Math.log10(n)/Math.log10(2));

        if (Math.pow(2, greatestPower2+1) <= n) {
            greatestPower2++;
        }

        return (long) ((((n + 1) * (long) n) / 2) - ((long)(2 * (Math.pow(2, greatestPower2 + 1) - 1))));
    }

    private static long getsum2(long n) {
        long sum=0;
        long val=1;

        while (val <= n) {
            sum+=val;
            val=val*2;
        }

        System.out.println("Sum2: 2*getsum2: "+2*sum);
        System.out.println("Sum2: n(n+1)/2: "+((n*(n+1))/2));
        System.out.println("Sum2: "+(((n*(n+1))/2)-(2*sum)));
        return ((n*(n+1))/2)-(2*sum);
    }
}
/*

1
1000000000

 */