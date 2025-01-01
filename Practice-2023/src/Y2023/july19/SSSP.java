package Y2023.july19;

import java.util.ArrayList;
import java.util.TreeSet;

public class SSSP {
    public static void main(String[] args) {
        SSSPI graph = new Graph(2);
        graph.insert(0, 1, 2);
//        graph.insert(0, 2, 1);
//        graph.insert(0, 3, 3);
//        graph.insert(1, 3, 9);
//        graph.insert(1, 4, 11);
//        graph.insert(2, 3, 1);
//        graph.insert(2, 5, 11);
//        graph.insert(3, 4, 5);
//        graph.insert(4, 5, 1);

        graph.print();
        System.out.println();
        int [] ssd = graph.SSSP(0);

        for (int i=0 ; i<2 ; i++) {
            System.out.println(i+"\t"+ssd[i]);
        }

    }
}

class Graph implements SSSPI{
    ArrayList<Edge> [] adjList;
    Node[] nodes;

    Edge edge[];

    int numNodes;

    int MAX_DISTANCE;

    public Graph(int n) {
        this.numNodes = n;
        adjList = new ArrayList[n];
        nodes = new Node[n];

        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList<>();
            nodes[i] = new Node(i);
        }

        MAX_DISTANCE = Integer.MAX_VALUE / 2;
    }


    @Override
    public int[] SSSP(int source) {
        nodes[source].sd = 0;

        TreeSet<Node> treeSet = new TreeSet<>();
        treeSet.add(nodes[source]);

        while (!treeSet.isEmpty()) {
            Node root = treeSet.last();
            treeSet.remove(root);

            for (Edge adj: adjList[root.index]) {
                if (nodes[adj.d].sd > root.sd + adj.w) {
                    treeSet.remove(nodes[adj.d]);
                    nodes[adj.d].sd = root.sd + adj.w;
                    treeSet.add(nodes[adj.d]);
                }
            }
        }

        int [] ssd = new int[numNodes];

        for (Node n: nodes) {
            ssd[n.index] = n.sd;
        }

        return ssd;
    }

    @Override
    public void print() {
        for (int i=0;i<numNodes;i++) {
            System.out.print(i+": ");
            for (Edge e: adjList[i]) {
                System.out.print("("+e.d+", "+e.w+"), ");
            }
            System.out.println();
        }
    }

    @Override
    public void insert(int s, int d, int w) {
        Edge edge = new Edge(s,d,w);
        adjList[s].add(edge);
    }
}

class Node implements Comparable<Node>{
    int index;
    int sd;

    public Node(int index) {
        this.index = index;
        this.sd = Integer.MAX_VALUE/2;
    }

    @Override
    public int compareTo(Node o) {
        if (o.sd != this.sd) {
            return Integer.compare(o.sd, this.sd);
        }
        return Integer.compare(o.index, this.index);
    }
}
class Edge {
    int s;
    int d;
    int w;

    public Edge(int s, int d, int w) {
        this.s = s;
        this.d = d;
        this.w = w;
    }
}

interface SSSPI {
    int [] SSSP(int source);
    void print();

    void insert(int s, int d, int w);
}