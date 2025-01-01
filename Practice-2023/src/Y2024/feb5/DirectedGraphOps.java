package Y2024.feb5;

import Y2023.dec5.A;

import java.util.ArrayList;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/5/2024
 */
public class DirectedGraphOps {
    public static void main(String[] args) {
        TopologicalSortI topologicalSortI = new TopologicalSortImpl(6);
        topologicalSortI.insert(0,1);
        topologicalSortI.insert(0,2);
        topologicalSortI.insert(0,3);
        topologicalSortI.insert(1,4);
        topologicalSortI.insert(1,2);
        topologicalSortI.insert(2,4);
        topologicalSortI.insert(3,2);
        topologicalSortI.insert(4,5);
        topologicalSortI.topologicalSort(0);
    }
}

class TopologicalSortImpl implements TopologicalSortI {
    ArrayList<Integer> [] adjList;
    int numN;

    public TopologicalSortImpl(int numNodes) {
        this.numN = numNodes;
        this.adjList = new ArrayList[numNodes];

        for (int i=0;i<numNodes;i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    @Override
    public void insert(int s, int d) {
        adjList[s].add(d);
    }

    @Override
    public void topologicalSort(int root) {
        boolean isVisited [] = new boolean[numN];
        topologicalSortHelper(root, isVisited);
    }

    private void topologicalSortHelper(int root, boolean[] isVisited) {
        if (isVisited[root]) {
            return;
        }
        isVisited[root] = true;

        for (int n: adjList[root]) {
            topologicalSortHelper(n,isVisited);
        }
        System.out.print(root+", ");
    }

    @Override
    public void print() {
        for (int i=0;i<numN;i++) {
            System.out.print(i+": ");
            for (int x: adjList[i]) {
                System.out.print(x+", ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

interface TopologicalSortI {
    void insert(int s, int d);
    void topologicalSort(int root);
    void print();
}