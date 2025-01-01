package oct22;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MSTK {
    public static void main(String[] args) {
        KusashkhalMST kusashkhalMST = new KusashkhalMST(7, 5);
        kusashkhalMST.insert(0,2,1);
        kusashkhalMST.insert(0,3,1);
        kusashkhalMST.insert(0,1,2);
        kusashkhalMST.insert(1,2,3);
        kusashkhalMST.insert(1,3,3);
        kusashkhalMST.insert(2,4,2);
        kusashkhalMST.insert(3,4,1);

        List<Edge> mst = kusashkhalMST.mst();
        System.out.println(mst);
    }
}


class KusashkhalMST implements MSTI {
    ArrayList<Edge> edges;
    int [] parent;
    int clusterHeight[];
    int numEdges;
    int numNodes;

    public KusashkhalMST(int numEdges, int numNodes) {
        this.numEdges = numEdges;
        this.numNodes = numNodes;
        this.edges = new ArrayList<>();
        parent = new int[numEdges];
        clusterHeight = new int[numEdges];

        for (int i = 0; i< parent.length; i++) {
            parent[i]=i;
            clusterHeight[i] = 1;
        }
    }

    public void insert(int s, int d, int w) {
        edges.add(new Edge(s,d,w));
    }

    @Override
    public List<Edge> mst() {
        Collections.sort(edges);
        ArrayList<Edge> mst = new ArrayList<>();

        for (Edge e:edges) {
            int parentS = getRootParent(e.s);
            int parentD = getRootParent(e.d);

            if ( parentS != parentD) {
                mst.add(e);
                mergeCluster(parentS, parentD);
            }
        }

        return mst;
    }

    private void mergeCluster(int parentS, int parentD) {
        if (clusterHeight[parentS] == clusterHeight[parentD]) {
            clusterHeight[parentS]++;
            parent[parentD] = parentS;
        } else if (clusterHeight[parentS] < clusterHeight[parentD]){
            parent[parentS] = parentD;
        } else {
            parent[parentD] = parentS;
        }
    }

    private int getRootParent(int d) {
        while (parent[d] != d) {
            d = parent[d];
        }
        return d;
    }
}




interface MSTI {
    List<Edge> mst();
}

class Edge implements Comparable<Edge>{
    int s;
    int d;

    @Override
    public String toString() {
        return "{" +
                "s=" + s +
                ", d=" + d +
                ", w=" + w +
                "}\n";
    }

    int w;

    public Edge(int s, int d, int w) {
        this.s = s;
        this.d = d;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.w, o.w);
    }
}
