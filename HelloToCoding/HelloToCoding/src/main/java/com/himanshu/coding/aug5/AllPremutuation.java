package com.himanshu.coding.aug5;

public class AllPremutuation {
    static  int count=0;
    public static void main(String[] args) {
        int n =     5;
        int a[] = new int[n];
        a[0]=0;
        a[1]=1;
        a[3]=3;
        a[4]=4;
        a[2]=2;

        allPremutuation(0,a.length-1,a);
        System.out.println(count);
    }

    private static void allPremutuation(int s, int e, int[] a) {
        if (s>= a.length-1) {
            count++;
            for (int x:a) {
                System.out.print(x+", ");
            }
            System.out.println();
            return;
        }

            for (int j=s;j<=e;j++) {
                swap(s,j,a);
                allPremutuation(s+1,e,a);
                swap(s,j,a);
            }
    }

    private static void swap(int s, int e, int []a) {
        int temp = a[s];
        a[s]=a[e];
        a[e]=temp;
    }
}


