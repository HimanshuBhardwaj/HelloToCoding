package Y2023.july8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class APSP {
    public static void main(String[] args) {
        APSPI graph = new DGraph(6);
        graph.insert(0,1,13);
        graph.insert(0,2,5);
        graph.insert(1,5,2);
        graph.insert(1,3,2);
        graph.insert(2,5,2);
        graph.insert(2,3,13);
        graph.insert(2,4,4);
        graph.insert(3,4,1);
        graph.insert(5,4,1);

        graph.print();

        graph.SPSP(0);
    }
}

class DGraph implements APSPI {
    LinkedList<Edge> edgeList;
    LinkedList<Edge>[] adjList;
    int numNodes;

    public DGraph(int numNodes) {
        this.numNodes = numNodes;
        edgeList = new LinkedList<>();
        adjList = new LinkedList[numNodes];

        for (int i=0;i<numNodes;i++) {
            adjList[i] = new LinkedList<>();
        }
    }


    @Override
    public void insert(int s, int d, int w) {
        Edge edge = new Edge(s, d, w);
        edgeList.add(edge);
        adjList[s].add(edge);
    }

    @Override
    public void print() {
        System.out.println("Edges");
        for (Edge edge: edgeList) {
            System.out.println(edge);
        }

        System.out.println("\n\nNodes");

        for (int i=0;i<numNodes;i++) {
            System.out.print(i+": ");
            for (Edge edge: adjList[i]) {
                System.out.print(edge.d+", ");
            }
            System.out.println();
        }
    }

    @Override
    public void SPSP(int s) {
        int [] weight = new int[numNodes];

        for (int i=0;i<numNodes;i++) {
            weight[i] = Integer.MAX_VALUE/2;

            if (i==s) {
                weight[i] = 0;
            }
        }

        for (int i=0;i<numNodes;i++) {
            for (Edge e: edgeList) {
                if (weight[e.d] > weight[e.s]+e.w) {
                    weight[e.d] = weight[e.s]+e.w;
                }
            }
        }

        System.out.println("\n\nSSSP");
        for (int i=0; i<numNodes; i++) {
            System.out.println(i+"\t"+weight[i]);
        }
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

    @Override
    public String toString() {
        return "Edge{" +
                "s=" + s +
                ", d=" + d +
                ", w=" + w +
                '}';
    }
}

interface APSPI {
    void insert(int s, int d, int w);
    void print();

    void SPSP(int s);
}