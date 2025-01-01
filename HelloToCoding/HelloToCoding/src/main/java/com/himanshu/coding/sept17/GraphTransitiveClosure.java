package com.himanshu.coding.sept17;

public class GraphTransitiveClosure {
    public static void main(String[] args) {
        Ggraph graph = new Ggraph(3);
        graph.insert(0,1);
        graph.insert(1,2);
        graph.insert(2,2);
        graph.printGraph();

        System.out.println();
        System.out.println();
        System.out.println();
        graph.transitiveClosure();
        graph.printGraph();
    }
}

class Ggraph {
    boolean[][] adjMatrix;
    int numNodes;

    public Ggraph(int numNodes) {
        this.numNodes = numNodes;
        adjMatrix = new boolean[numNodes][numNodes];
    }

    public  void  insert(int r, int c) {
        adjMatrix[r][c] = true;
    }

    public void printGraph() {
        for(int i=0;i<adjMatrix.length;i++) {
            for (int j=0;j<adjMatrix[0].length;j++) {
                System.out.print(adjMatrix[i][j]+", ");
            }
            System.out.println();
        }
    }

    public  void  transitiveClosure() {
            for (int i=0;i<numNodes;i++) {
                for (int j=0;j<numNodes;j++) {
                    for(int k=0;k<numNodes;k++) {
                    adjMatrix[i][j] = (adjMatrix[i][j]||adjMatrix[i][k]||adjMatrix[k][j]);
                }
            }
        }
    }
}