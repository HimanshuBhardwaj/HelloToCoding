package com.himanshu.coding.june6;

public class MatricTranspose {
    public static void main(String[] args) {
        Metric metric = new Metric(2,3);

        for (int i=0;i<2;i++)
        {
            for (int j=0;j<3;j++)
            {
                metric.insert(i,j,(int)(Math.random()*1000));
            }
        }

        System.out.println("Printing first metric");
        metric.print();
        Metric transpose =  metric.getTransPose();
        System.out.println();
        System.out.println("Printing first metric");
        transpose.print();
    }
}

class Metric
{
    int r;
    int c;
    int [][]arr;

    public Metric(int r, int c)
    {
        this.r = r;
        this.c = c;
        arr = new int[r][c];
    }

    void insert(int r,int c, int value)
    {
        arr[r][c]=value;
    }

    Metric getTransPose()
    {
        Metric metric = new Metric(c,r);
        for (int i=0;i<c;i++)
        {
            for (int j=0;j<r;j++)
            {
                metric.insert(i,j,arr[j][i]);
            }
        }
        return  metric;
    }

    boolean areEqual(Metric metric) {
        if (metric == null) {
            return false;
        }

        int [][] arr2 = metric.arr;

        if (arr2 == null)
        {
            return  (arr == null);
        }

        if ( (arr2.length != arr.length))
        {
            return false;
        }

        for (int i=0;i<arr.length;i++)
        {
            if (arr2[i].length != arr[i].length)
            {
                return false;
            }

            for (int j=0;j<arr[0].length;j++)
            {
                if (arr2[i][j] != arr[i][j])
                {
                    return false;
                }
            }
        }
        return true;

    }

    void print()
    {
        for (int i=0;i<arr.length;i++)
        {
            System.out.println();
            for (int j=0;j<arr[i].length;j++)
            {
                System.out.print(arr[i][j]+"\t");
            }
        }
    }
}
