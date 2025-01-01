package com.himanshu.coding.aug5;

import java.util.LinkedList;

public class AllSubsets {
    public static void main(String[] args) {
        int n=5;
        int [] a= new int[n];

        for (int i=0;i<n;i++) {
            a[i]=i+1;
        }

        Subset subset1 = new SubsetPrint1();
        subset1.printAllSubsets(a);
        System.out.println(subset1.countSubsetPrinted());

    }
}

class SubsetPrint1 implements Subset {
    public int count=0;

    @Override
    public void printAllSubsets(int[] a) {
        printAllSubsetsInternal(new LinkedList<Integer>(),0,a);
    }

    @Override
    public int countSubsetPrinted() {
        return count;
    }

    private void printAllSubsetsInternal(LinkedList<Integer> integers, int index, int[] a) {
        if (index >= a.length) {
            System.out.println(integers);
            count++;
            return;
        }

        integers.addLast(a[index]);
        printAllSubsetsInternal(integers,index+1,a);
        integers.removeLast();
        printAllSubsetsInternal(integers,index+1,a);
    }
}


interface Subset {
    void printAllSubsets(int []a);
    int countSubsetPrinted();
}