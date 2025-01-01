package Y2023.july1;

import java.util.LinkedList;
import java.util.Queue;

public class UndirectedGraphOperations {
    public static void main(String[] args) {
        UGraphOperationI uGraphOperation = new UGraph(7);
        /*uGraphOperation.insert(0,1);
        uGraphOperation.insert(0,2);
        uGraphOperation.insert(0,5);
        uGraphOperation.insert(0,6);
        uGraphOperation.insert(1,2);
        uGraphOperation.insert(1,3);
        uGraphOperation.insert(2,3);
        uGraphOperation.insert(2,4);
        uGraphOperation.insert(3,4);
        uGraphOperation.insert(3,6);
        uGraphOperation.insert(4,5); */

        uGraphOperation.insert(0,1);
        uGraphOperation.insert(0,2);
        uGraphOperation.insert(1,2);
        uGraphOperation.insert(3,4);
        uGraphOperation.insert(3,5);
        uGraphOperation.insert(5,4);
        uGraphOperation.insert(6,4);
        uGraphOperation.insert(6,5);

        uGraphOperation.print();

        System.out.println("DFS:");
        uGraphOperation.DFS(0);
        System.out.println();
        System.out.println("BFS");
        uGraphOperation.BFS(3);

    }
}

class UGraph implements UGraphOperationI {
    LinkedList<UNode>[] adjList;
    int numNodes;

    UNode [] nodes;

    public UGraph(int n) {
        this.numNodes = n;
        adjList = new LinkedList[n];
        nodes = new UNode[n];

        for (int i=0;i<n;i++) {
            adjList[i] = new LinkedList<>();
            nodes[i] = new UNode(i);
        }
    }

    @Override
    public void insert(int s, int d) {
        adjList[s].add(nodes[d]);
        adjList[d].add(nodes[s]);
    }

    @Override
    public void print() {
        for (int i=0; i<numNodes; i++) {
            System.out.print(i+": ");
            for (UNode neighbour:adjList[i]) {
                System.out.print(neighbour.index+", ");
            }
            System.out.println();
        }
    }

    @Override
    public void DFS(int root) {

        boolean [] visited = new boolean[numNodes];

        for (int i=0;i<numNodes;i++) {
            if (!visited[i]) {
                DFSHelper(i, visited);
                System.out.println();
            }
        }
    }

    @Override
    public void BFS(int root) {
        Queue<Integer> queue = new LinkedList<>();
        boolean [] isVisited = new boolean[numNodes];

        queue.add(root);
        isVisited[root] = true;

        while (!queue.isEmpty()) {
            int top = queue.poll();
            System.out.print(top+", ");

            for (UNode neighbour: adjList[top]) {
                if (!isVisited[neighbour.index]) {
                    isVisited[neighbour.index] = true;
                    queue.add(neighbour.index);
                }
            }
        }
    }

    private void DFSHelper(int root, boolean[] visited) {
        if (visited[root]) {
            return;
        }
        System.out.print(root+", ");
        visited[root] = true;

        for (UNode neighbour : adjList[root]) {
            if (!visited[neighbour.index]) {
                DFSHelper(neighbour.index, visited);
            }
        }
    }
}

interface UGraphOperationI {
    void insert(int s, int d);
    void print();
    void DFS(int root);

    void BFS(int root);
}

class UNode implements Comparable<UNode>{
    int index;

    int weight;


    public UNode(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(UNode o) {
        return Integer.compare(this.index, o.index);
    }
}
