package com.himanshu.coding.june12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TreeOperations {
    public static void main(String[] args) {
        UTreeImpl uTree = new UTreeImpl(8);
        uTree.insert(0,1);
        uTree.insert(0,2);
        uTree.insert(1,3);
        uTree.insert(1,4);
        uTree.insert(2,5);
        uTree.insert(2,6);
        uTree.insert(5,7);

        uTree.print();
        System.out.println(uTree.height(0));
        System.out.println("BFS");
        uTree.bfs(0);
        System.out.println("DFS");
        uTree.dfs(0);
        System.out.println("\nIs Connected: "+ uTree.isConnected());
    }
}


interface UTreeI {
    void insert(int s, int d);
    void dfs(int root);
    void bfs(int root);
    int height(int root);
    int diameter();
    boolean isConnected();
    void print();
}

class UTreeImpl implements UTreeI {
    ArrayList<Integer> adjList[];
    int size;

    public UTreeImpl(int size) {
        this.size = size;
        adjList = new ArrayList[size];
        for (int i=0;i<size;i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    @Override
    public void insert(int s, int d) {
        adjList[s].add(d);
        adjList[d].add(s);
    }

    @Override
    public void dfs(int root) {
        dfsHelper(root,-1);
    }

    private void dfsHelper(int root, int parent) {
        System.out.print(root+", ");
        for (int neighbour:adjList[root]) {
            if (neighbour != parent) {
                dfsHelper(neighbour, root);
            }
        }
    }

    @Override
    public void bfs(int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        int marker = Integer.MIN_VALUE;
        int level=0;
        System.out.print(level+": ");
        queue.add(marker);
        boolean[]visited = new boolean[size];
        visited[root]=true;

        while (!queue.isEmpty()) {
            int node = queue.poll();


            if (node==marker) {
                level++;
                System.out.println();
                System.out.print(level+": ");
                if (!queue.isEmpty()) {
                    queue.add(marker);
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

    @Override
    public int height(int root) {
        int height = 0;

        for (int neighbours:adjList[root]) {
            height = Math.max(height,1+heightHelper(neighbours,root));
        }

        return height;
    }

    private int heightHelper(int root, int parent) {
        int height = 0;
        for (int neighbour:adjList[root]) {
            if (neighbour != parent) {
                height = Math.max(height,1+heightHelper(neighbour,root));
            }
        }
        return height;
    }

    @Override
    public int diameter() {
        return 0;
    }

    @Override
    public boolean isConnected() {
        boolean [] visited = new boolean[size];
        connectedHelper(0,visited);

        for (int i=0;i<visited.length;i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    private void connectedHelper(int root, boolean[] visited) {
        if (visited[root]) {
            return;
        }

        visited[root]=true;

        for (int neighbour:adjList[root]) {
            if (!visited[neighbour]) {
                connectedHelper(neighbour,visited);
            }
        }
    }

    @Override
    public void print() {
        for (int i=0;i<size;i++) {
            System.out.print(i+": ");
            for (int j=0;j<adjList[i].size();j++) {
                System.out.print(adjList[i].get(j)+", ");
            }
            System.out.println();
        }
    }
}

