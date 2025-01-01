package com.himanshu.coding.june10;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class UndirectedGraph {
    public static void main(String[] args) {
        UndirectedGraphImpl undirectedGraph = new UndirectedGraphImpl(7);
        undirectedGraph.insert(0,5);
        undirectedGraph.insert(0,6);
        undirectedGraph.insert(5,1);
        undirectedGraph.insert(5,2);
        undirectedGraph.insert(6,3);
        undirectedGraph.insert(6,4);
        undirectedGraph.insert(3,0);
        undirectedGraph.print();
        System.out.println(undirectedGraph.hasCycle(0));
        undirectedGraph.BFS(0);

    }

}

interface  IGraph {
    void insert(int s, int e);
    void print();
    void DFS(int root);
    void BFS(int root);
    int size(int root);
    boolean hasCycle(int root);
}

/**
 *
 */
class UndirectedGraphImpl implements IGraph {
    ArrayList<Integer> adjList[];
    int size;

    public UndirectedGraphImpl(int size) {
        adjList = new ArrayList[size];
        this.size = size;

        for (int i=0;i<size;i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    //Tested
    //assuming it is from 0...size-1
    @Override
    public void insert(int s, int e) {
        adjList[s].add(e);
        adjList[e].add(s);
    }

    //Tested
    @Override
    public void print() {
        for (int i=0;i<size;i++) {
            System.out.print(i+": ");
            for (int x:adjList[i]) {
                System.out.print(x+", ");
            }
            System.out.println();
        }
    }

    @Override
    public void DFS(int root) {


    }

    @Override
    public void BFS(int root) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(root);
        boolean isVisited[] = new boolean[size];
        isVisited[root]=true;
        int level=0;
        queue.add(Integer.MIN_VALUE);

        while (!queue.isEmpty()) {
            int node=queue.poll();

            if (node==Integer.MIN_VALUE) {
                level++;
                if (!queue.isEmpty()) {
                    queue.add(Integer.MIN_VALUE);
                }
                continue;
            }

            System.out.print(node+", ");

            for (int neighbour:adjList[node]) {
                if (!isVisited[neighbour]) {
                    isVisited[neighbour]=true;
                    queue.add(neighbour);
                }
            }
        }

        System.out.println("\nLevel of BFS "+ level);


    }

    @Override
    public int size(int root) {
        return size;
    }

    @Override
    public boolean hasCycle(int root) {
        boolean[] hasVisited = new boolean[size];

        for (int i=0;i<size;i++) {
            if (!hasVisited[i]) {
                if(hasCycleHelper(hasVisited,i,-1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycleHelper(boolean[] hasVisited, int root, int parent) {
        if (root <0 || root>=size) {
            return false;
        }
        hasVisited[root]=true;

        for (int neightbours:adjList[root]) {
            if (neightbours!=parent) {
                if (hasVisited[neightbours]) {
                    return true;
                }

                if(hasCycleHelper(hasVisited, neightbours, root)){
                    return true;
                }
            }
        }
        return false;
    }
}