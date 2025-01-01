package dec28;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class UndirectedGraph {
    public static void main(String[] args) {
        GraphI graphI = new UnDGraphImpl(5);
       graphI.insert(0,1);
       graphI.insert(0,2);
       graphI.insert(1,2);

       graphI.insert(3,4);
       //graphI.insert(1,2);

        graphI.print();
        System.out.println("DFS");
        graphI.DFS(0);
        System.out.println();
        System.out.println("BFS");
        graphI.BFS(0);
        System.out.println();
        System.out.println("Level Order");
        graphI.LeveOrder(0);
        System.out.println();
        System.out.println();
        System.out.println(graphI.connectedComponents());
        System.out.println("Cycle");
        System.out.println(graphI.cycle());
        System.out.println("Transitive closure");
        graphI.transitiveClosure();

    }
}

//1:50
class UnDGraphImpl implements GraphI {
    ArrayList<Integer> [] adjList;
    int n;
    boolean [][] adjMat;

    public UnDGraphImpl(int n) {
        this.n = n;
        adjList = new ArrayList[n];
        adjMat = new boolean[n][n];

        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    @Override
    public void DFS(int root) {
        boolean [] visited = new boolean[n];
        DFSHelper(root,visited);
    }

    @Override
    public int connectedComponents() {
        boolean [] visited = new boolean[n];
        int c=0;

        for (int i=0;i<visited.length;i++) {
            if (!visited[i]) {
                c++;
                DFSHelper(i,visited);
            }
        }
        System.out.println();
        return c;
    }

    private void DFSHelper(int root, boolean[] visited) {
        if (visited[root]) {
            return;
        }
        System.out.print(root+", ");
        visited[root] = true;

        for (int neigh: adjList[root]) {
            if (!visited[neigh]) {
                DFSHelper(neigh, visited);
            }
        }
    }

    @Override
    public void BFS(int root) {
        boolean [] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        visited[root] = true;

        while (!queue.isEmpty()) {
            int top = queue.poll();
            System.out.print(top+", ");
            for (int nei: adjList[top]) {
                if (!visited[nei]) {
                    visited[nei] = true;
                    queue.add(nei);
                }
            }
        }

        System.out.println();
    }

    @Override
    public void LeveOrder(int root) {
        ArrayList<Integer> [] levelNodes = new ArrayList[n];

        for (int i=0;i<levelNodes.length;i++) {
            levelNodes[i] = new ArrayList<>();
        }

        int level=0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        queue.add(Integer.MIN_VALUE);
        boolean [] visited = new boolean[n];
        visited[root] = true;

        while (!queue.isEmpty()) {
            int top = queue.poll();

            if (top== Integer.MIN_VALUE) {
                if (!queue.isEmpty()) {
                    queue.add(top);
                    level++;
                }
            } else {
                levelNodes[level].add(top);
                for (int neigh: adjList[top]) {
                    if (!visited[neigh]) {
                        visited[neigh] = true;
                        queue.add(neigh);
                    }
                }
            }
        }

        for (int i=0;i<levelNodes.length;i++) {
            if (levelNodes[i].size() > 0) {
                System.out.println(i+"\t"+levelNodes[i]);
            }
        }
    }

    @Override
    public void transitiveClosure() {

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                for (int k=0;k<n;k++) {
                    if (adjMat[i][k] && adjMat[k][j]) {
                        adjMat[i][j] = true;
                    }
                }
            }
        }

        for (int i=0;i<n;i++) {
            System.out.print(i+": ");
            for (int j=0;j<n;j++) {
                if (adjMat[i][j]) {
                    System.out.print(j + ",");
                }
            }
            System.out.println();
        }

    }

    @Override
    public boolean cycle() {
        boolean[] visited = new boolean[n];
        return cycleHelper(0,visited, -1);
    }

    private boolean cycleHelper(int index, boolean[] visited, int parent) {
        if (visited[index]) {
            return true;
        }
        visited[index] = true;

        for (int neifh: adjList[index]) {
            if (neifh != parent) {
                if (cycleHelper(neifh,visited,index)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void print() {
        for (int i=0;i<n;i++) {
            System.out.print(i+": ");
            for (int nei: adjList[i]) {
                System.out.print(nei+", ");
            }
            System.out.println();
        }
    }

    @Override
    public void insert(int s, int d) {
        adjList[s].add(d);
        adjList[d].add(s);
        adjMat[s][d]=true;
        adjMat[d][s]=true;
    }
}


interface GraphI {
    void DFS(int root);
    void BFS(int root);
    void LeveOrder(int root);
    void transitiveClosure();
    int connectedComponents();
    boolean cycle();
    void print();
    void insert(int s, int d);
}