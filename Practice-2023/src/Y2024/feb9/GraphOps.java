package Y2024.feb9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/9/2024
 */
public class GraphOps {
    public static void main(String[] args) {
        GraphI graphI = new UndirectedGraphImpl(10);
        graphI.insert(0,1);
        graphI.insert(0,2);

        graphI.insert(3,4);
        graphI.insert(5,3);

        //graphI.insert(6,7);
        graphI.insert(6,8);
        graphI.insert(8,9);
        graphI.insert(7,9);

        graphI.print();
        graphI.BFS(8);
        graphI.DFS(8);
        System.out.println(graphI.hasCycle());
        graphI.connectedComponents();
    }
}

class UndirectedGraphImpl implements GraphI {
    ArrayList<Integer> adjList[];
    int n;

    public UndirectedGraphImpl(int n) {
        this.n = n;
        adjList = new ArrayList[n];

        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    @Override
    public void DFS(int s) {
        boolean [] visited = new boolean[n];
        ArrayList<Integer> dfsOrder = new ArrayList<>();
        DFSHelper(s,visited,dfsOrder);
        System.out.println("DFS Order: "+dfsOrder);
    }

    private void DFSHelper(int s, boolean[] visited, ArrayList<Integer> dfsOrder) {
        if (visited[s]) {
            return;
        }
        visited[s] = true;
        dfsOrder.add(s);

        for (int n:adjList[s]) {
            if (!visited[n]) {
                DFSHelper(n,visited,dfsOrder);
            }
        }
    }

    @Override
    public void BFS(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        boolean [] visited = new boolean[n];
        visited[s] = true;
        ArrayList<Integer> bfsOrder = new ArrayList<>();


        while (!queue.isEmpty()) {
            int top = queue.poll();
            bfsOrder.add(top);

            for (int n:adjList[top]) {
                if (!visited[n]) {
                    queue.add(n);
                    visited[n] = true;
                }
            }
        }

        System.out.println("BFS Order"+ bfsOrder);
    }

    @Override
    public boolean hasCycle() {
        boolean [] isVisited = new boolean[n];

        for (int i=0;i<n;i++) {
            if(!isVisited[i] && hasCycleHelper(i,isVisited, -1)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCycleHelper(int i, boolean[] isVisited, int parent) {
        if (isVisited[i]) {
            return true;
        }
        isVisited[i] = true;

        for  (int n: adjList[i]) {
            if (n != parent) {
                if (hasCycleHelper(n,isVisited,i)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void insert(int s, int d) {
        adjList[s].add(d);
        adjList[d].add(s);
    }

    @Override
    public void print() {
        for (int i=0;i<this.n;i++) {
            System.out.print(i+": ");
            for (int x:adjList[i]) {
                System.out.print(x+", ");
            }
            System.out.println();
        }
    }

    @Override
    public void connectedComponents() {
        boolean [] isVisited = new boolean[n];
        ArrayList<ArrayList> connectedComponents = new ArrayList<>();

        for (int i=0;i<n;i++) {
            if (!isVisited[i]) {
                ArrayList<Integer> cc = new ArrayList<>();
                DFSHelper(i,isVisited,cc);
                connectedComponents.add(cc);
            }
        }

        System.out.println(" Connected Componenets: "+connectedComponents.size());
    }
}

interface GraphI {
    void DFS(int s);
    void BFS(int s);
     boolean hasCycle();
     void insert(int s, int d);
     void print();

     void connectedComponents();
}