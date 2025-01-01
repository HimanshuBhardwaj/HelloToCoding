package com.himanshu.coding.aug18;

public class MatricExponentiation {

    public static void main(String[] args) {
        int [][]a = {{1,1},{1,0}};

        int n=30;
        int fab[][]={{0,1}};

        int c[][] = mateixExponentiation(a,n-1);

        print(mateixExponentiation(fab,n));

        int aa[] = new int[n+1];
        aa[0]=0;
        aa[1]=1;

        for (int i=2;i<=n;i++) {
            aa[i]= aa[i-1]+aa[i-2];
        }

        System.out.println();
        print1(aa);
    }

   static  int[][] mateixExponentiation(int [][] a, int n) {
        if (n==0) {
            int [][] c = new int[a.length][a[0].length];
            for (int i=0;i<a.length;i++) {
                c[i][i]=1;
            }
            return c;
        }
        if (n==1) {
            return a.clone();
        }

        int m = n/2;

        int c[][] = mateixExponentiation(a,m);

        int d[][] = matrixMultiplication(c,c);

        if (n%2 != 0) {
            d = matrixMultiplication(d,a);
        }

        return d;
   }

   static int [][] matrixMultiplication(int [][]a, int [][]b) {
        int [][]c = new int[a.length][b[0].length];
        for (int i=0;i<a.length;i++) {
            for (int j=0;j<b[0].length;j++) {
                for (int k=0;k<b.length;k++) {
                    c[i][j]+=(a[i][k]*b[k][j]);
                }
            }
        }
            return c;
    }

    static void print1(int []a) {
        for (int i=0;i<a.length;i++) {
            System.out.print(a[a.length-1-i]+", ");
        }
        System.out.println();
    }

    static void print(int [][]a) {
        System.out.println();
        for (int i=0;i<a.length;i++) {
            for (int j=0;j<a[0].length;j++) {
                System.out.print(a[i][j]+", ");
            }
            System.out.println();
        }
    }
}

