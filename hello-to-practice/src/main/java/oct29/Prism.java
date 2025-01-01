package oct29;

import java.util.*;

public class Prism {
    public static void main(String[] args) {
        MST mst = new PrismMST(5);
        mst.insert(0, 1, 10);
        mst.insert(0, 2, 11);
        mst.insert(1, 3, 5);
        mst.insert(1, 4, 1);
        mst.insert(2, 3, 8);
        mst.insert(3, 4, 3);

        List<Edge> mstE = mst.mst();
        System.out.println("MST:");
        for (Edge e:mstE) {
            System.out.println(e);
        }
    }
}

class PrismMST implements MST {
    int n;
    List<Edge> edges;
    ArrayList<Edge> [] adjList;

    public PrismMST(int n) {
        this.n = n;
        edges = new ArrayList<>();
        adjList = new ArrayList[n];

        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void insert(int s, int d, int w) {
        Edge edge =new Edge(s, d, w);
        edges.add(edge);
        adjList[s].add(edge);
        adjList[d].add(edge);
    }

    @Override
    public List<Edge> mst() {
        Collections.sort(edges);

        Set<Integer> visited = new HashSet<>();
        Set<Integer> yetToVisit = new HashSet<>();
        ArrayList<Edge> mstEdges = new ArrayList<>();

        visited.add(0);

        for (int i=1;i<n;i++) {
            yetToVisit.add(i);
        }

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();

        for (Edge neighbour: adjList[0]) {
            neighbour.isVisited=true;
            priorityQueue.add(neighbour);
        }

        while (!yetToVisit.isEmpty() && !priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();

            if (isMSTEdge(visited, yetToVisit, edge)) {
                int newExploredNode = newExploredNode(visited, yetToVisit, edge);
                mstEdges.add(edge);
                yetToVisit.remove(newExploredNode);
                visited.add(newExploredNode);

                for (Edge neighbour:adjList[newExploredNode]) {
                    if (neighbour.isVisited == false) {
                        neighbour.isVisited = true;
                        priorityQueue.add(neighbour);
                    }
                }
            }
        }

        return mstEdges;
    }

    private int newExploredNode(Set<Integer> visited, Set<Integer> yetToVisit, Edge edge) {
        if (visited.contains(edge.start)) {
            return edge.end;
        }
        return edge.start;
    }

    private boolean isMSTEdge(Set<Integer> visited, Set<Integer> yetToVisit, Edge edge) {
        if (visited.contains(edge.start) && visited.contains(edge.end)) {
            return false;
        }
        return true;
    }
}


interface MST {
    List<Edge> mst();
    void insert(int s, int d, int w);
}


class Edge implements Comparable<Edge>{

    int start;
    int end;
    int weight;

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }

    boolean isVisited;

    public Edge(int s, int e, int w) {
        this.start = s;
        this.end = e;
        this.weight = w;
        this.isVisited = false;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight == o.weight) {
            if (o.start == this.start) {
                return Integer.compare(this.end, o.end);
            } else if (this.start == o.end){
                return Integer.compare(this.end, o.start);
            } else {
                return Integer.compare(this.start, o.start);
            }
        } else {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
