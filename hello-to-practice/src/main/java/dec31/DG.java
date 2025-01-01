package dec31;

import java.util.*;

public class DG {
    public static void main(String[] args) {
        DirectedGraphI directedGraphI = new DirectedGraph(5);
        directedGraphI.insert(0,1);
        directedGraphI.insert(0,3);
        directedGraphI.insert(1,2);
        directedGraphI.insert(3,2);
        directedGraphI.insert(3,4);
        directedGraphI.insert(4,2);

        directedGraphI.print();
        directedGraphI.DFS(0);
        directedGraphI.BFS(0);

        System.out.println(directedGraphI.topologicalSort(0));
        System.out.println("Transitive Closure");
        directedGraphI.transitiveClosure();
    }
}

// 5:32 - 5:54
class DirectedGraph implements DirectedGraphI {
    ArrayList<Integer> adjList[];
    int [][]adjMat;
    int n;

    public DirectedGraph(int n) {
        this.n = n;
        adjList = new ArrayList[n];

        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList<>();
        }

        adjMat = new int[n][n];
    }

    @Override
    public void DFS(int root) {
        boolean [] visited = new boolean[n];

        for (int i=0;i<n;i++) {
            if (!visited[i]) {
                DFSHelper(root, visited);
            }
        }
        System.out.println();
    }

    private void DFSHelper(int root, boolean[] visited) {
        if (visited[root]) {
            return;
        }
        System.out.print(root+", ");
        visited[root]=true;

        for (int nei: adjList[root]) {
            if (!visited[nei]) {
                DFSHelper(nei,visited);
            }
        }
    }

    @Override
    public void BFS(int root) {
        boolean[]visited = new boolean[n];
        Queue<Integer> queue = new LinkedList();

        queue.add(root);
        visited[root] = true;

        while (!queue.isEmpty()) {
            int top = queue.poll();
            System.out.print(top+", ");

            for (int n:adjList[top]) {
                if (!visited[n]) {
                    visited[n]=true;
                    queue.add(n);
                }
            }
        }
        System.out.println();
    }

    @Override
    public List<Integer> topologicalSort(int root) {
        boolean [] visited = new boolean[n];
        ArrayList<Integer> tso=new ArrayList<>();
        topologicalSortHelper(root, visited, tso);
        return tso;
    }

    private void topologicalSortHelper(int root, boolean[] visited, ArrayList<Integer> tso) {
        if (visited[root]) {
            return;
        }
        visited[root]=true;

        for (int n: adjList[root]) {
            if (!visited[n]) {
                topologicalSortHelper(n,visited,tso);
            }
        }
        tso.add(root);
    }

    @Override
    public void transitiveClosure() {
        int [][]adjMatC = adjMat.clone();

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                for (int k=0;k<n;k++) {
                    if (adjMatC[i][k]==1 && adjMatC[k][j]==1) {
                        adjMatC[i][j]=1;
                    }
                }
            }
        }

        for (int i=0;i<n;i++) {
            System.out.print(i+": ");
            for (int j=0;j<n;j++) {
                if (adjMatC[i][j]==1) {
                    System.out.print(j+",");
                }
            }
            System.out.println();
        }
    }

    @Override
    public void print() {
        for (int i=0;i<n;i++) {
            System.out.print(i+":");
            for (int j=0;j<adjList[i].size();j++) {
                System.out.print(adjList[i].get(j)+", ");
            }
            System.out.println();
        }
    }

    @Override
    public void insert(int s, int d) {
        adjMat[s][d]=1;
        adjList[s].add(d);
    }
}

interface DirectedGraphI {
    void DFS(int root);
    void BFS(int root);
    List<Integer> topologicalSort(int root);
    void transitiveClosure();
    void print();
    void insert(int s, int d);
}