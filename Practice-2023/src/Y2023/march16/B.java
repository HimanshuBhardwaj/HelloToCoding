package Y2023.march16;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Himanshu Bhardwaj
 * @Date 3/16/2024
 */
public class B {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("4");
        list.add("1100");
        list.add("1110");
        list.add("0110");
        list.add("0001");

        System.out.println(countGroups(list));

    }
    public static int countGroups(List<String> related) {
        int n = related.size();
        DSUI dsu = new DSU(n);

        ArrayList<String> relatedL = generatedRelatedList(related);

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (relatedL.get(i).charAt(j)=='1') {
                    Edge edge = new Edge(i,j);
                    dsu.join(edge);
                }
            }
        }


        return dsu.numConnectedComponents();
        // Write your code here

    }

    private static ArrayList<String> generatedRelatedList(List<String> related) {
        int flag=0;
        ArrayList<String> relatedL = new ArrayList<>();

        for (String s: related) {
            relatedL.add(s);
        }
        return relatedL;
    }
}



interface DSUI {
    void join(Edge e);
    int numConnectedComponents();
}

class Edge implements Comparable<Edge> {
    int s;
    int d;

    public Edge(int s, int d) {
        this.s = s;
        this.d = d;
    }

    @Override
    public int compareTo(Edge o) {

        if (this.s != o.s) {
            return Integer.compare(this.s, o.s);
        }

        return Integer.compare(this.d, o.d);
    }
}

    class DSU implements DSUI {

        int n;
        int[] parent;

        int connectedComponents;

        LinkedList<Edge> list;

        public DSU(int n) {
            list = new LinkedList<>();
            initializeDSU(n);
        }

        void  initializeDSU(int n) {
            this.n=n;
            this.connectedComponents = n;
            parent = new int[n];

            for (int i=0;i<n;i++) {
                parent[i] = i;
            }
        }

        public void join(Edge e) {
            list.addLast(e);
            int s = e.s;
            int d = e.d;

            int ss = findSet(s);
            int sd = findSet(d);

            if (ss == sd) {
                return;
            }

            parent[ss] = sd;
            connectedComponents--;
        }

        @Override
        public int numConnectedComponents() {
            return this.connectedComponents;
        }


        private int findSet(int s) {
            if (s == parent[s]) {
                return s;
            }
            parent[s] = findSet(parent[s]);

            return parent[s];
        }
    }