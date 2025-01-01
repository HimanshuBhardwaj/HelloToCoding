package com.himanshu.coding.aug10;

public class APSPPOC {
    public static void main(String[] args) {
        Graph2 graph2 = new Graph2(5);
        graph2.insert(0,1,1);
        graph2.insert(0,3,2);
        graph2.insert(1,3,3);
        graph2.insert(1,2,1);
        graph2.insert(3,2,2);
        graph2.insert(2,4,4);
        graph2.insert(3,4,3);
        graph2.insert(4,0,2);
        graph2.print();
        graph2.allPairShortestPath();
        System.out.println("APSP");
        graph2.print();
    }
}


class Graph2 {
    long [][] adjMat;
    int n;

    public Graph2(int n) {
        this.n = n;
        adjMat = new long[n][n];

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                adjMat[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public void insert(int s, int e, int w) {
        adjMat[s][e]=w;
    }

    public void allPairShortestPath() {
        for (int k=0;k<n;k++) {
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    if (adjMat[i][k]+adjMat[k][j] < adjMat[i][j]) {
                        adjMat[i][j] = adjMat[i][k]+adjMat[k][j];
                    }
                }
            }
        }

        print();

    }

    void  print() {
        System.out.println();
        for (int i=0;i<n;i++) {
            System.out.println();
            for (int j=0;j<n;j++) {
                System.out.print(((Integer.MAX_VALUE==adjMat[i][j])?"0":adjMat[i][j])+", ");
            }
            System.out.println();
        }
        System.out.println();

    }
}