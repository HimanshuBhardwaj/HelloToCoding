package com.himanshu.coding.june19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuffer output = new StringBuffer();

        while (t-- > 0) {
            String[] str = br.readLine().split(" ");
            long value = minimumPath(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
            output.append(value);
            if (t!=0) {
                output.append("\n");
            }
        }
        System.out.print(output.toString());
    }

    private static long minimumPath(int n, int m) {


        int arr[][] =   new int[n][m];

        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                arr[i][j] = (i*m)+j+1;
            }
        }


        if (n==1 && m==1) {
            return arr[0][0];
        }

        long cost=0l;

        int posr=0;
        int posc=0;

        while (posr<n || posc<m) {
            if ((posr==n-1) && (posc==m-1)) {
                break;
            }
            if (posr== (n-1) && posc != (m-1)) {
                cost+= arr[posr][posc] + arr[posr][posc+1];
                posc++;
            } else if (posc == m-1 && posr != (n-1)) {
                cost+=arr[posr][posc]+arr[posr+1][posc];
                posr++;
            } else {
                if ((arr[posr][posc+1]) < arr[posr+1][posc]) {
                    cost+= arr[posr][posc+1]+arr[posr][posc];
                    posc++;
                } else {
                    cost+= arr[posr+1][posc]+arr[posr][posc];
                    posr++;
                }
            }
        }
        return cost;
    }
}
