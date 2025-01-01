package oct29;

import java.util.ArrayList;

public class Bellmanford {
    public static void main(String[] args) {
        BellmanfordSSSP bellmanfordSSSP = new BellmanfordSSSP(6);
        bellmanfordSSSP.insert(0, 1, 1);
        bellmanfordSSSP.insert(0, 2, 6);
        bellmanfordSSSP.insert(0, 3, 3);
        bellmanfordSSSP.insert(1, 2, 1);
        bellmanfordSSSP.insert(3, 2, 1);
        bellmanfordSSSP.insert(2, 5, 5);
        bellmanfordSSSP.insert(3, 5, 5);

        int [] sssp = bellmanfordSSSP.sssp(0);

        for (int i=0;i<bellmanfordSSSP.numNodes;i++) {
            System.out.println(i+": "+sssp[i]);
        }
        System.out.println();
    }
}

class BellmanfordSSSP implements SSSP {
    ArrayList<Edge1> edge1s;
    ArrayList<Edge1> [] adjList;

    int numNodes;

    public BellmanfordSSSP(int numNodes) {
        this.numNodes = numNodes;
        edge1s = new ArrayList<>();
        adjList = new ArrayList[numNodes];

        for (int i=0;i<numNodes;i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    void insert(int s, int d, int w) {
        Edge1 e = new Edge1(s,d,w);

        edge1s.add(e);
        adjList[s].add(e);
        adjList[d].add(e);
    }

    @Override
    public int[] sssp(int root) {
        int [] ssspA = new int[numNodes];

        for (int i=0;i<numNodes;i++) {
            ssspA[i] = Integer.MAX_VALUE;
        }
        ssspA[root] = 0;

        for (int i=0;i<numNodes;i++) {
            for (Edge1 edge1:edge1s) {
                int n1 = edge1.s;
                int n2 = edge1.d;
                int w = edge1.w;

                if (ssspA[n1] != Integer.MAX_VALUE) {
                    ssspA[n2] = Math.min(ssspA[n1]+w, ssspA[n2]);
                }

                if (ssspA[n2] != Integer.MAX_VALUE) {
                    ssspA[n1] = Math.min(ssspA[n2]+w, ssspA[n1]);
                }
            }
        }

        return ssspA;
    }
}

class Edge1{
    int s;
    int d;
    int w;

    public Edge1(int s, int d, int w) {
        this.s = s;
        this.w = w;
        this.d = d;
    }
}

interface SSSP {
    int [] sssp(int root);
}
