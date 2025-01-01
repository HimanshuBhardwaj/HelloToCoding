package com.himanshu.coding.aug30;

import java.util.ArrayList;

public class Knapsack {
    public static void main(String[] args) {
        //KP(28);

        for (int i=1;i<=30;i++) {
            BF(i);
            KP(i);
        }


    }

    private static void KP(int value) {
        MaximumValue bruteForce = new KS(5);
        bruteForce.insert(new Element(7,3));
        bruteForce.insert(new Element(3,5));
        bruteForce.insert(new Element(2,9));
        bruteForce.insert(new Element(5,5));
        bruteForce.insert(new Element(11,7));

        System.out.println("KS: "+bruteForce.maxValue(value));
    }

    static void BF(int value) {
        MaximumValue bruteForce = new BruteForce(5);
        bruteForce.insert(new Element(7,3));
        bruteForce.insert(new Element(3,5));
        bruteForce.insert(new Element(2,9));
        bruteForce.insert(new Element(5,5));
        bruteForce.insert(new Element(11,7));
        System.out.print(value+"\tBruteForce: "+bruteForce.maxValue(value)+"\t");
    }
}

class KS implements MaximumValue {
    int n;
    ArrayList<Element> elements;

    public KS(int n) {
        this.n = n;
        this.elements = new ArrayList<>();
    }

    public void insert(Element value) {
        elements.add(value);
    }

    @Override
    public long maxValue(int totalWeight) {
        int dp[][] = new int[n][totalWeight+1];

        for (int i=0;i<=totalWeight;i++) {
            if (i>=elements.get(0).weight) {
                dp[0][i] = elements.get(0).value;
            }
        }

        int x=5;

        for (int i=1;i<elements.size();i++) {
            for (int j=0;j<=totalWeight;j++) {
                dp[i][j] = Math.max(dp[i-1][j],
                        (j>=elements.get(i).weight)?(dp[i-1][j-elements.get(i).weight]+elements.get(i).value):0);
            }
        }

        int y=10;

        return dp[n-1][totalWeight];
    }
}

class BruteForce implements MaximumValue {
    int n;
    ArrayList<Element> elements;

    public BruteForce(int n) {
        this.n = n;
        this.elements = new ArrayList<>();
    }

    public void insert(Element value) {
        elements.add(value);
    }

    @Override
    public long maxValue(int totalWeight) {
        long maxValue = Integer.MAX_VALUE;

        return maxValueHelper(elements.size()-1,totalWeight);
    }

    private long maxValueHelper(int index, int totalWeight) {
        if (index <0 || index>=elements.size()|| totalWeight<=0) {
            return 0;
        }

        long tMax2 = maxValueHelper(index-1, totalWeight);

        if (elements.get(index).weight <= totalWeight) {
            long tMax1 = maxValueHelper(index - 1, totalWeight - elements.get(index).weight) + elements.get(index).value;
            tMax2 = Math.max(tMax1,tMax2);
        }
        return tMax2;
    }
}

class Element {
    int weight;
    int value;

    public Element(int w, int v) {
        this.weight = w;
        this.value = v;
    }
}

interface MaximumValue {
    public long maxValue(int totalWeight);
    public void insert(Element value);
}