package com.himanshu.coding.june11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DAGOperations {
    public static void main(String[] args) {
        DAGOperationsImpl dagOperations = new DAGOperationsImpl(5);
        dagOperations.insert(0,1);
        dagOperations.insert(1,2);
        dagOperations.insert(1,3);
        dagOperations.insert(2,3);
        dagOperations.insert(3,4);
        dagOperations.insert(1,4);

        dagOperations.print();
        System.out.println("IS Connected: "+ dagOperations.isConnected(0));
        System.out.println("Has cycle: "+dagOperations.hasCycle(0));
        dagOperations.BFS(0);
        System.out.println();
        System.out.println("DFS...");
        dagOperations.DFS(0);
        dagOperations.transitiveClosure();
        System.out.println("Topological sort");
        dagOperations.topologicalSort(0);
    }
}

class DAGOperationsImpl implements DAGOperationsI {
    int size;
    ArrayList<Integer> adjList[];
    boolean [][] adjMat;

    public DAGOperationsImpl(int n) {
        this.size = n;
        adjList = new ArrayList[n];
        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList<>();
        }
        adjMat = new boolean[size][size];
    }

    @Override
    public boolean isConnected(int root) {
        boolean visited[] = new boolean[size];

        isConnectedHelper(visited, root);

        for (int i=0;i<visited.length;i++) {
            if (visited[i]==false) {
                return false;
            }
        }
        return true;
    }

    private void isConnectedHelper(boolean[] visited, int root) {
        if (root<0 || root>=size) {
            return;
        }

        if (visited[root]) {
            return;
        }

        visited[root] = true;

        for (int neighbour:adjList[root]) {
            if (!visited[neighbour]) {
                isConnectedHelper(visited, neighbour);
            }
        }
    }

    @Override
    public boolean hasCycle(int root) {
        int visited[] = new int[size];

        if(hasCycleHelper(root,visited)) {
            return true;
        }
        return false;
    }

    private boolean hasCycleHelper(int root, int[] visited) {
        if (root<0 || root >= size) {
            return false;
        }
        if (visited[root]==1) {
            return true;
        }
        visited[root]=1;

        for (int neighbour:adjList[root]) {
            if (visited[neighbour] != 2) {
                if (hasCycleHelper(neighbour, visited)) {
                    return true;
                }
            }
        }
        visited[root]=2;

        return false;
    }

    @Override
    public void DFS(int root) {
        boolean [] visited = new boolean[size];
        DFSHelper(visited,root);
    }

    private void DFSHelper(boolean[] visited, int root) {
        if (visited[root]) {
            return;
        }
        System.out.print(root+", ");
        visited[root]=true;

        for (int neighbour:adjList[root]) {
            if (!visited[neighbour]) {
                DFSHelper(visited, neighbour);
            }
        }
    }

    @Override
    public void BFS(int root) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(root);
        int level=1;
        System.out.print(level+": ");
        queue.add(Integer.MIN_VALUE);
        boolean[] visited = new boolean[size];
        visited[root]=true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node==Integer.MIN_VALUE) {
                System.out.println();
                level++;
                System.out.print(level+": ");
                if (!queue.isEmpty()) {
                    queue.add(Integer.MIN_VALUE);
                }
                continue;
            }

            System.out.print(node+", ");

            for (int neighbour:adjList[node]) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
    }

    //assume DAG
    @Override
    public void topologicalSort(int root) {
        boolean [] visited = new boolean[size];
        topologicalSortHelper(root,visited);
    }

    void topologicalSortHelper(int root, boolean[] visited) {
        visited[root] = true;
        for (int neighbour:adjList[root]) {
            if (!visited[neighbour]) {
                topologicalSortHelper(neighbour,visited);
            }
        }
        System.out.print(root+", ");
    }



    @Override
    public void insert(int s, int d) {
        adjList[s].add(d);
        adjMat[s][d]=true;
    }

    @Override
    public void print() {
        for (int i=0;i<adjList.length;i++) {
            System.out.print(i+": ");
            for (int j=0;j<adjList[i].size();j++) {
                System.out.print(adjList[i].get(j)+", ");
            }
            System.out.println();
        }
    }

    @Override
    public void transitiveClosure() {
        for (int i=0;i<size;i++) {
            for (int k=0;k<size;k++) {
                for (int j=0;j<size;j++) {
                    if (adjMat[i][k]&&adjMat[k][j]) {
                        adjMat[i][j]=true;
                    }
                }
            }
        }

        System.out.println();
        System.out.println("Transitive closure");
        for (int i=0;i<size;i++) {
            System.out.print(i+": ");
            for (int j=0;j<size;j++) {
                if (adjMat[i][j]) {
                   System.out.print(j+",");
                }
            }
            System.out.println();
        }
    }
}

interface DAGOperationsI {
    boolean isConnected(int root);
    boolean hasCycle(int root);
    void DFS(int root);
    void BFS(int root);
    void topologicalSort(int root);
    void insert(int s, int d);
    void print();
    void transitiveClosure();
}
