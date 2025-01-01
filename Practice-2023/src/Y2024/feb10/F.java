package Y2024.feb10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/10/2024
 */
public class F {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            GraphI graph = new GraphImpl(n,m);

            for (int i=0;i<m;i++) {
                str = br.readLine().split(" ");
                int s = Integer.parseInt(str[0])-1;
                int d = Integer.parseInt(str[1])-1;
                int w = Integer.parseInt(str[2]);
                graph.addEdge(s,d,w);
            }

            pw.append(graph.minWeightEdgeCausingSystem()).append("\n");
        }
        pw.flush();
        pw.close();
    }
}

class GraphImpl implements GraphI {
    ArrayList<Edge> [] adjList;
    int n;
    int e;

    HashSet<Integer> cluster[];
    ArrayList<Edge> edges = new ArrayList<>();

    UnionFindI unionFindI;

    public GraphImpl(int n, int e) {
        this.n = n;
        this.e = e;
        adjList = new ArrayList[n];
        cluster = new HashSet[n];

        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList<>();
            cluster[i] = new HashSet<>();
            cluster[i].add(i);
        }

        this.unionFindI = new UnionFindRank(n);
    }

    @Override
    public String minWeightEdgeCausingSystem() {
        Collections.sort(edges, Comparator.reverseOrder());
        int min = Integer.MAX_VALUE;
        Edge le = null;

        for (Edge e: edges) {
            if (joinEdge(e)) {
                min = e.w;
                le = e;
            }
        }

        boolean [] isVisited = new boolean[n];
        LinkedList<Integer> cycle = new LinkedList<>();
        cycle.add(le.s);
        getCycle(le.s, le.d,isVisited, cycle);

        StringBuilder sb = new StringBuilder();
        sb.append(min).append(" ").append(cycle.size()).append("\n");

        for (int x: cycle) {
            sb.append((x+1)).append(" ");
        }

        return sb.toString();
    }

    private boolean getCycle(int s, int d, boolean[] isVisited, LinkedList<Integer> cycle) {
        if (s==d) {
            return true;
        }

        isVisited[s] = true;

        for (Edge e:adjList[s]) {
            int neighbour = (e.s != s)?e.s:e.d;
            if (!isVisited[neighbour]) {
                cycle.addLast(neighbour);
                if (getCycle(neighbour, d, isVisited, cycle)) {
                    return true;
                }

                cycle.removeLast();
            }
        }
        return false;
    }

    @Override
    public boolean joinEdge(Edge e) {
        boolean belongToSameContainer = unionFindI.sameSet(e.s, e.d);

        adjList[e.s].add(e);
        adjList[e.d].add(e);
        unionFindI.union(e.s, e.d);
        return belongToSameContainer;
    }

    @Override
    public void addEdge(int s, int d, int w) {
        Edge e = new Edge(s,d,w);
        edges.add(e);
    }
}


interface GraphI {
    String minWeightEdgeCausingSystem();

    boolean joinEdge(Edge e);

    void addEdge(int s, int d, int w);
}

class Edge implements Comparable<Edge> {
    int s;
    int d;
    int w;

    public Edge(int s, int d, int w) {
        this.s = s;
        this.d = d;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.w, o.w);
    }
}

class UnionFindRank implements UnionFindI {
    int [] cluster;
    int height[];
    int n;

    public UnionFindRank(int n) {
        this.cluster = new int[n];
        this.height =  new int[n];
        this.n = n;

        for (int i=0;i<n;i++) {
            cluster[i] = i;
            height[i]=1;
        }
    }

    @Override
    public void union(int s, int d) {
        int ps = parent(s);
        int pd = parent(d);
        if (height[s] < height[d]) {
            cluster[ps] = pd;
            while (s != cluster[s]) {
                height[s]++;
                s = cluster[s];
            }
            height[s] ++;
        } else if (height[s] >= height[d]) {
            cluster[pd] = ps;

            while (d != cluster[d]) {
                height[d]++;
                d = cluster[d];
            }
            height[d] ++;
        }
    }

    @Override
    public boolean sameSet(int s, int d) {
        return parent(s) == parent(d);
    }

    private int parent(int s) {
        if (cluster[s] == s) {
            return s;
        }
        cluster[s] =parent(cluster[s]);
        height[s] = 2;
        return cluster[s];
    }
}


class UnionFindPathCompression implements UnionFindI {
    int [] cluster;
    int n;

    public UnionFindPathCompression(int n) {
        this.cluster = new int[n];
        this.n = n;

        for (int i=0;i<n;i++) {
            cluster[i] = i;
        }
    }

    @Override
    public void union(int s, int d) {
        int ps = parent(s);
        int pd = parent(d);
        cluster[ps] = pd;
    }

    @Override
    public boolean sameSet(int s, int d) {
        return parent(s) == parent(d);
    }

    private int parent(int s) {
        if (cluster[s] == s) {
            return s;
        }
        int cl = parent(cluster[s]);
        cluster[s] = cl;
        return cl;
    }
}

interface UnionFindI {
    void union(int s, int d);
    boolean sameSet(int s, int d);
}
