package com.himanshu.coding.aug08;

public class BellmonfordSSSP {
    public static void main(String[] args) {
        int n=4;
        Graph graph = new Graph(n);
        graph.insert(0,1,1);
        graph.insert(0,2,5);
        graph.insert(0,3,15);
        graph.insert(2,3,10);
        graph.insert(1,2,3);

        graph.printGraph();
        System.out.println();
        for (long c:graph.bellmonFordSSSP(0)) {
            System.out.print(c+" ");
        }

    }
}


class Graph {
    int [][]adjMat;
    int n;

    public Graph(int numNode) {
        this.n=numNode;
        adjMat = new int[n][n];
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                adjMat[i][j]=Integer.MAX_VALUE;
            }
        }
    }

    void insert(int s, int d, int w) {
        adjMat[s][d]=w;
    }

    void printGraph() {
        for (int i=0;i<n;i++) {
            System.out.print(i+": ");
            for (int j=0;j<n;j++) {
                System.out.print(adjMat[i][j]+", ");
            }
            System.out.println();
        }
    }

    long[] bellmonFordSSSP(int source) {
        long[] SSSP = new long[n];
        SSSP[source] = 0;

        for (int i = 0; i < SSSP.length; i++) {
            SSSP[i] = (i == source) ? (0) : Integer.MAX_VALUE;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if ( (SSSP[j] + adjMat[j][k]) < SSSP[k]) {
                        SSSP[k] = SSSP[j] + adjMat[j][k];
                    }
                }
            }
        }
    return SSSP;
    }
}