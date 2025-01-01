package Y2023.aug1;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class MSTAlgos {
    public static void main(String[] args) {
        UGraph graph = new UGraph(6);
        graph.insert(0, 1, 10);
        graph.insert(0, 2, 1);
        graph.insert(0, 3, 1);
        graph.insert(1, 2, 2);
        graph.insert(2, 3, 3);
        graph.insert(0, 4, 5);
        graph.insert(3, 4, 3);
        graph.insert(1, 5, 99);
        graph.insert(2, 5, 100);

        graph.print();
        System.out.println(graph.prismMST());
        System.out.println("\n\n");
        System.out.println(graph.kurashkalMST());
    }
}

class UGraph extends GraphOperation implements MSTI {
    ArrayList<Edge> [] adjList;
    ArrayList<Edge> edges;

    public UGraph(int numNodes) {
        adjList = new ArrayList[numNodes];

        for(int i=0;i<numNodes;i++) {
            adjList[i] = new ArrayList<>();
        }

        edges = new ArrayList<>();
    }


    @Override
    void insert(int s, int d, int w) {
        Edge edge = new Edge(s, d, edges.size(), w);
        adjList[s].add(edge);
        adjList[d].add(edge);
        edges.add(edge);
    }

    @Override
    void print() {
        for (int i=0;i<adjList.length;i++) {
            System.out.print(i+": ");
            for (int j=0;j<adjList[i].size();j++) {
                Edge e= adjList[i].get(j);
                int d = (e.s != i)?e.s:e.d;
                System.out.print("("+d+", "+e.w+"), ");
            }
            System.out.println();
        }
    }

    @Override
    public List<Edge> prismMST() {
        TreeSet<Edge> mst = new TreeSet<>();
        TreeSet<Edge> edgeSet = new TreeSet<>();
        TreeSet<Integer> unexploredNodes = new TreeSet<>();
        TreeSet<Integer> exploredNodes = new TreeSet<>();

        for (int i=1;i<adjList.length;i++) {
            unexploredNodes.add(i);
        }

        exploredNodes.add(0);
        for (Edge e: adjList[0]) {
            edgeSet.add(e);
        }

        while (!unexploredNodes.isEmpty()) {
            Edge smallest = edgeSet.first();
            edgeSet.remove(smallest);
            if (exploredNodes.contains(smallest.s)) {
                if (!exploredNodes.contains(smallest.d)) {
                    unexploredNodes.remove(smallest.d);
                    exploredNodes.add(smallest.d);
                    mst.add(smallest);

                    for (Edge e: adjList[smallest.d]) {
                        edgeSet.add(e);
                    }
                }
            } else {
                unexploredNodes.remove(smallest.s);
                exploredNodes.add(smallest.s);
                mst.add(smallest);

                for (Edge e: adjList[smallest.s]) {
                    edgeSet.add(e);
                }
            }
        }

        return new ArrayList<>(mst);
    }

    @Override
    public List<Edge> kurashkalMST() {
        Collections.sort(edges);
        List<Edge> mst = new ArrayList<>();

        int [] collections = new int[adjList.length];

        for (int i=0; i<collections.length; i++) {
            collections[i] = i;
        }

        for (Edge e: edges) {
            int collectionS = getCollection(collections, e.s);
            int collectionD = getCollection(collections, e.d);

            if (collectionS != collectionD) {
                mst.add(e);
                mergeCollections(collections, collectionS, collectionD);
            }
        }

        return mst;
    }

    private void mergeCollections(int[] collections, int collectionS, int collectionD) {
        collections[collectionS] = collectionD;
    }

    private int getCollection(int[] collections, int d) {
        while (collections[d] != d) {
            d = collections[d];
        }

        return d;
    }
}

class Edge implements Comparable<Edge>{
    int s;
    int d;
    int index;
    int w;

    public Edge(int s, int d, int index, int w) {
        this.s = s;
        this.d = d;
        this.index = index;
        this.w = w;
    }


    @Override
    public int compareTo(Edge o) {
        if (this.w != o.w) {
            return Integer.compare(this.w, o.w);
        }
        return Integer.compare(this.index, o.index);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "s=" + s +
                ", d=" + d +
                ", w=" + w +
                '}'+'\n';
    }
}


// undirected graph
abstract class GraphOperation {
    abstract void insert(int s, int d, int w);
    abstract void print();
}
interface MSTI {
    List<Edge> prismMST();
    List<Edge> kurashkalMST();
}