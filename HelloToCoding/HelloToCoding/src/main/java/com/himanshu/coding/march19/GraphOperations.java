package com.himanshu.coding.march19;

import java.util.ArrayList;

public class GraphOperations {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.insert(0,1);
        graph.insert(0,2);
        graph.insert(2,1);
        graph.insert(2,4);
        graph.insert(1,3);
        graph.print();
        System.out.println();
        graph.DFS(0,new boolean[graph.numNodes],-1);
        System.out.println("\n..");


    }
}



//undirected graph
class Graph {
    private ArrayList<Integer> [] adjMat;
    int numNodes;

    public Graph(int n) {
        this.numNodes = n;
        adjMat = new ArrayList[n];

        for (int i=0;i<n;i++) {
            adjMat[i] = new ArrayList<>();
        }
    }

    void  insert(int a, int b) {
        adjMat[a].add(b);
        adjMat[b].add(a);
    }

    void print() {
        for (int i=0;i<numNodes;i++) {
            System.out.print(i+": ");
            for (int node:adjMat[i]) {
                System.out.print(node+",");
            }
            System.out.println();
        }
    }


    void DFS(int root, boolean [] visited, int parent) {
        if (visited[root]) {
            return;
        }
        visited[root] = true;
        System.out.println(root+", "+parent);

        for (int neighbout: adjMat[root]) {
            if (!visited[neighbout]) {
                DFS(neighbout,visited,root);
            }
        }
    }


}