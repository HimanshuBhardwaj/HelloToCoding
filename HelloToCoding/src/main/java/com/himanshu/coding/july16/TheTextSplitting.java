package com.himanshu.coding.july16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TheTextSplitting {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int p = Integer.parseInt(str[1]);
        int q = Integer.parseInt(str[2]);
        String string = br.readLine();
        int pa=-1;
        int qb=-1;

        for (int i=0;(p*i<=n);i++) {

            if ((n-i*p)%q==0) {
                pa=i;
                qb = (n- pa*p)/q;
            }
            if (pa != -1) {
                break;
            }
        }

        if (pa==-1) {
            System.out.print("-1");
            return;
        }

/*
        System.out.println("(pa,p),(" +pa+","+p+")");
        System.out.println("(qb,q),(" +qb+","+q+")");
*/

        StringBuilder sb = new StringBuilder();
        int start=0;

        for (int i=0;i<pa;i++) {
            for (int j=0;j<p;j++) {
                sb.append(string.charAt(start++));
                if (j==p-1) {
                    sb.append("\n");
                }
            }
        }

        for (int i=0;i<qb;i++) {
            for (int j=0;j<q;j++) {
                sb.append(string.charAt(start++));
                if (j==q-1) {
                    sb.append("\n");
                }
            }
        }

        System.out.println(pa+qb);
        System.out.print(sb.toString());

    }
}
