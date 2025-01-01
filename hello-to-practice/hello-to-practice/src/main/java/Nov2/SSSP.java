package Nov2;

import java.util.ArrayList;
import java.util.TreeSet;

public class SSSP {
    public static void main(String[] args) {
        Dijkstras dijkstras = new Dijkstras(6);
        dijkstras.insert(0,1,1);
        dijkstras.insert(0,2,1);
        dijkstras.insert(1,4,5);
        dijkstras.insert(2,4,1);
        dijkstras.insert(2,3,3);
        dijkstras.insert(4,5,1);
        dijkstras.insert(3,5,2);

        dijkstras.print();

        int [] sssp = dijkstras.sssp(5);

        for (int x:sssp) {
            System.out.print(x+", ");
        }


    }
}



class Dijkstras implements SSSPI {
    ArrayList<Edge> [] adjList;
    int numNodes;

    public Dijkstras(int numNodes) {
        this.numNodes = numNodes;
        adjList = new ArrayList[numNodes];

        for (int i=0;i<numNodes;i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    @Override
    public int[] sssp(int source) {
        Node[] ssp = new Node[numNodes];

        for (int i=0;i<numNodes;i++) {
            ssp[i] = new Node(i, Integer.MAX_VALUE);
        }

        ssp[source].distance = 0;

        TreeSet<Node> treeSet = new TreeSet<>();
        treeSet.add(ssp[source]);

        while (!treeSet.isEmpty()) {
            Node nearest = treeSet.first();
            treeSet.remove(nearest);

            for (Edge neighbourEdge:adjList[nearest.index]) {
                int sideNode = getOtherNode(neighbourEdge, nearest);

                if (ssp[sideNode].distance >= (nearest.distance+neighbourEdge.w) ) {
                    treeSet.remove(ssp[sideNode]);
                    ssp[sideNode].distance = nearest.distance+neighbourEdge.w;
                    treeSet.add(ssp[sideNode]);
                }
            }
        }

        int [] ssspA = new int[numNodes];

        for (int i=0;i<numNodes;i++) {
            ssspA[i] = ssp[i].distance;
        }
        return ssspA;
    }

    private int getOtherNode(Edge neighbourEdge, Node nearest1) {
        if (neighbourEdge.s == nearest1.index) {
            return neighbourEdge.d;
        }
        return neighbourEdge.s;
    }

    void insert(int s, int d, int w) {
        Edge edge = new Edge(s, d, w);
        adjList[s].add(edge);
        adjList[d].add(edge);
    }

    void print() {
        for (int i=0;i<numNodes;i++) {
            System.out.print(i+": ");
            for (Edge neighbour: adjList[i]) {
                System.out.print(getOtherNode(neighbour, new Node(i,0))+", ");
            }
            System.out.println();
        }
    }
}

class Node  implements  Comparable<Node>{
    int index;
    int distance;
    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
        if (this.distance != o.distance){
            return Integer.compare(this.distance, o.distance);
        }     else {
            return Integer.compare(this.index, o.index);
        }
    }
}

class Edge implements Comparable<Edge> {
    int s;
    int d;
    int w;

    public Edge(int s, int d, int w) {
        this.s = s;
        this.d = d;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.w == o.w) {
            return Integer.compare(this.s, o.s);
        } else {
            return Integer.compare(this.w, o.w);
        }
    }
}

interface SSSPI {
    int [] sssp(int source);
}