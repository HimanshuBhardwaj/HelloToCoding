package com.himanshu.coding.aug09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//11:35--12:25
public class TreePOC {
    public static void main(String[] args) {
        int n=13;
        Tree tree = new Tree(n);
        tree.insert(0,1);
        tree.insert(0,2);
        tree.insert(1,6);
        tree.insert(2,3);
        tree.insert(2,4);
        tree.insert(4,5);

        tree.insert(5,7);
        tree.insert(4,8);
        tree.insert(8,9);
        tree.insert(9,10);
        tree.insert(6,11);
        tree.insert(11,12);

        tree.print();
        tree.BFS(0);
        System.out.print("DFS: ");
        tree.DFS(0,-1);
        System.out.println();
        System.out.println("Last node: "+tree.lastnode);
        System.out.println("Diameter: "+tree.BFS(tree.lastnode));

    }


}

//undirected
class Tree {
    ArrayList<Integer> [] adjList;
    int n;
    int lastnode=-1;

    public Tree(int n) {
        this.n = n;
        adjList = new ArrayList[n];

        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    void insert(int s, int e) {
        adjList[s].add(e);
        adjList[e].add(s);
    }

    void print() {
        for (int i=0;i<n;i++) {
            System.out.print(i+": ");
            for(int x:adjList[i]) {
                System.out.print(x+", ");
            }
            System.out.println();
        }
    }

    int BFS(int root) {
        boolean [] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(root);
        visited[root] = true;
        int level=0;
        queue.add(-1);

        System.out.print("BFS:\t");
        while (!queue.isEmpty()) {
            int top = queue.poll();
            if (top==-1) {
                if (!queue.isEmpty()) {
                    level++;
                    queue.add(-1);
                }
                continue;
            } else {
                System.out.print(top + ", ");
                lastnode=top;
            }
            for (int n:adjList[top]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        System.out.println();

        System.out.println("Level "+level);
        return level;
    }

    void DFS(int root, int parent) {
        System.out.print(root+", ");

        for (int n:adjList[root]) {
            if (n != parent) {
                DFS(n,root);
            }
        }
    }
}
