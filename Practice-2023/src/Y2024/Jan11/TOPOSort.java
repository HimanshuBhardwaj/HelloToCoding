package Y2024.Jan11;

import java.util.ArrayList;

/**
 * @author Himanshu Bhardwaj
 * @Date 1/11/2024
 */
public class TOPOSort {
    public static void main(String[] args) {
        GraphOps graphOps = new DGraph(6);

        graphOps.insert(0,3);
        graphOps.insert(0,1);
        graphOps.insert(3,2);
        graphOps.insert(1,2);
        graphOps.insert(3,4);
        graphOps.insert(3,5);
        graphOps.insert(2,4);
        graphOps.insert(4,5);

        graphOps.print();
        System.out.println();
        ArrayList<Integer> list = new ArrayList<>();
        boolean [] visited = new boolean[6];
        graphOps.topologicalSort(0, list, visited);
        System.out.println(list);
    }
}

class DGraph implements GraphOps {
    ArrayList<Integer> adjList[];
    int numNodes;

    public DGraph(int numNodes) {
        this.numNodes = numNodes;
        adjList = new ArrayList[numNodes];

        for (int i=0;i< numNodes;i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    @Override
    public void insert(int s, int d) {
        adjList[s].add(d);
    }

    @Override
    public void print() {
        for (int i=0;i<numNodes;i++) {
            System.out.print(i+": ");
            for (int n:adjList[i]) {
                System.out.print(n+", ");
            }
            System.out.println();
        }
    }

    @Override
    public void topologicalSort(int root, ArrayList<Integer> ts, boolean[]visited) {
        if (visited[root]) {
            return;
        }

        visited[root] = true;
        for (int n:adjList[root]) {
            topologicalSort(n, ts, visited);
        }
        ts.add(root);
    }
}

interface GraphOps {
    void insert(int s, int d);
    void print();
    void topologicalSort(int root,  ArrayList<Integer> ts, boolean[]visited);
}