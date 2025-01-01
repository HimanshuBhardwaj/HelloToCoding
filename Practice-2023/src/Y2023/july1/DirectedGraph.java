package Y2023.july1;

import Y2023.june16.LinkedListImpl;

import java.util.LinkedList;
import java.util.Queue;

public class DirectedGraph {
    public static void main(String[] args) {
        GraphI dg = new DG(6);
        dg.insert(0,1);
        dg.insert(0,2);
        dg.insert(0,5);
        dg.insert(1,3);
        dg.insert(2,3);
        dg.insert(2,4);
        dg.insert(3,5);
        dg.insert(3,4);
        dg.insert(4,1);

        dg.print();
        System.out.println();
        System.out.println("DFS");
        dg.DFS(0);
        System.out.println();
        System.out.println("Topologival sort");
        dg.topologicalSort(0);
        System.out.println("BFS");
        dg.BFS(0);

        System.out.println("\nHasCycle: "+dg.hasCycle());
    }
}

class DG implements GraphI {
    LinkedList<Node>[] adjList;
    int numNodes;

    Node [] nodes;

    public DG(int n) {
        this.numNodes = n;
        adjList = new LinkedList[n];
        nodes = new Node[n];

        for (int i=0;i<n;i++) {
            adjList[i] = new LinkedList<>();
            nodes[i] = new Node(i);
        }
    }

    @Override
    public void insert(int s, int d) {
        adjList[s].add(nodes[d]);
    }

    @Override
    public void print() {
        System.out.println("Printing DAG");
        for (int i=0;i<numNodes;i++) {
            System.out.print(i+": ");
            for (Node n: adjList[i]) {
                System.out.print(n.index+", ");
            }
            System.out.println();
        }
    }

    @Override
    public void DFS(int root) {
        if (root <0) {
            return;
        }
        DFSHelper(root, new boolean[numNodes]);
    }

    @Override
    public void topologicalSort(int root) {
        if (root < 0 ) {
            return;
        }

        topologicalSortHelper(root, new boolean[numNodes]);
    }

    @Override
    public void BFS(int root) {
        if (root < 0) {
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        boolean [] visited = new boolean[numNodes];
        visited[root] = true;

        while (!queue.isEmpty()) {
            int firstN = queue.poll();
            System.out.print(firstN+", ");

            for (Node neigh: adjList[firstN]) {
                if (!visited[neigh.index]) {
                    visited[neigh.index] = true;
                    queue.add(neigh.index);
                }
            }
        }
    }

    @Override
    public boolean hasCycle() {
        int [] visited = new int[numNodes];

        for (int i=0;i<numNodes;i++) {
            if((visited[i]==0) && hasCycleHelper(i, visited)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCycleHelper(int node, int[] visited) {
        if (visited[node] == 1) {
            return true;
        }

        visited[node] = 1;

        for (Node adj:adjList[node]) {
            if (visited[node] != 2) {
                if (hasCycleHelper(adj.index, visited)) {
                    return true;
                }
            }
        }

        visited[node] = 2;
        return false;
    }

    private void topologicalSortHelper(int root, boolean[] visitedNodes) {
        if (visitedNodes[root]) {
            return;
        }

        visitedNodes[root] = true;

        for (Node neigh: adjList[root]) {
            topologicalSortHelper(neigh.index, visitedNodes);
        }

        System.out.print(root+", ");
    }

    private void DFSHelper(int root, boolean[] visitedN) {
        if (visitedN[root]) {
            return;
        }

        System.out.print(root+", ");
        visitedN[root] = true;

        for (Node neighbour: adjList[root]) {
            DFSHelper(neighbour.index, visitedN);
        }
    }
}

class Node {
    int index;
    int distance;

    public Node(int i) {
        this.index = i;
    }
}

interface GraphI {
    void insert(int s, int d);
    void print();

    void DFS(int root);

    void topologicalSort(int root);

    void BFS(int root);

    boolean hasCycle();
}
