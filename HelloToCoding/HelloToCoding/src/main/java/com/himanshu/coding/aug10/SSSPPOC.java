package com.himanshu.coding.aug10;

import java.util.ArrayList;
import java.util.TreeSet;

class SSSPPOC {
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.insert(0,1,5);
        graph.insert(0,2,1);
        graph.insert(1,3,2);
        graph.insert(1,4,2);
        graph.insert(2,3,3);
        graph.insert(2,4,9);
        graph.insert(3,5,3);
        graph.insert(1,5,4);
        graph.insert(4,5,15);

        graph.print();
        System.out.println("Computing SSSP....");
        graph.getSSSP(0);
    }
}

//directed graph
class Graph implements SSSP{
    ArrayList<Edge> [] adjList;
    int n;
    Node [] nodes;

    public Graph(int n) {
        this.n = n;
        adjList = new ArrayList[n];
        nodes = new Node[n];

        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList<>();
            nodes[i] = new Node(i);
        }
    }

    public void insert(int s, int d, int weight) {
        Edge edge = new Edge(nodes[s],nodes[d],weight);
        adjList[s].add(edge);
    }

    @Override
    public int getSSSP(int source) {
        nodes[source].distance=0;
        TreeSet<Node> treeSet = new TreeSet<>();
        treeSet.add(nodes[source]);

        while (!treeSet.isEmpty()) {
         Node nearest = treeSet.first();
         treeSet.remove(nearest);

         if (nearest.distance == Integer.MAX_VALUE) {
             break;
         }

         for (Edge n:adjList[nearest.index]) {
             if ((n.d.distance == Integer.MAX_VALUE) ||
                     (n.d.distance > nearest.distance+n.weight)) {
                 treeSet.remove(n.d);
                 n.d.distance = Math.min(n.d.distance,nearest.distance+n.weight);
                 treeSet.add(n.d);
             }
         }
        }
        for (Node n:nodes) {
            System.out.println(n.index+", "+n.distance);
        }


        return 0;
    }

    void print() {
        for (int i=0;i<n;i++) {
            System.out.print(i+":\t");
            for (Edge e:adjList[i]) {
                System.out.print("("+e.d.index+", "+e.weight+"), ");
            }
            System.out.println();
        }
    }
}

class  Node implements Comparable<Node>{
    int index;
    int distance;

    public Node(int index) {
        this.index = index;
        this.distance = Integer.MAX_VALUE;
    }

    @Override
    public int compareTo(Node o) {
        if(Integer.compare(this.distance, o.distance) != 0) {
            return Integer.compare(this.distance, o.distance);
        } else {
            return Integer.compare(this.distance, o.distance);
        }
    }
}

class Edge {
    Node s;
    Node d;
    int weight;

    @Override
    public String toString() {
        return "Edge{" +
                "s=" + s +
                ", d=" + d +
                ", weight=" + weight +
                "}\t";
    }

    public Edge(Node s, Node d, int weight) {
        this.s = s;
        this.d = d;
        this.weight = weight;
    }
}

interface SSSP {
    int getSSSP(int source);
}
