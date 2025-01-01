package Y2023.march9;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Himanshu Bhardwaj
 * @Date 3/9/2024
 */
public class B {
    public static void main(String[] args) {
        ShortestPathI shortestPathI = new ShortestPathService(3);
        Edge edge = new Edge();
        edge.s = 0;
        edge.d = 1;
        shortestPathI.add(edge);
        edge.s = 0;
        edge.d = 1;
        shortestPathI.add(edge);
        System.out.println(shortestPathI.sixDegreeSepration());
    }
}




class ConnectedComponent {
    ParserServiceI parserService;
    int n;
    DSUI dsu;
    ShortestPathI shortestPathI;

    public ConnectedComponent(int n, ParserServiceI parserService) {
        this.parserService = parserService;
        this.n = n;
        this.dsu = new DSU(n); // ideal way is to have factory of it and get via factory
        shortestPathI = new ShortestPathService(n);
    }

    long getEarliestTimestamp(List<String> events) {

        for (String event: events) {
            Edge edge = parserService.parseInput(event);
            if (!edge.isDeleted) {
                dsu.join(edge);
                shortestPathI.add(edge);
            } else {
                shortestPathI.remove(edge);
                dsu.delete(edge);
            }

            if (dsu.numConnectedcomponents()==0 && shortestPathI.sixDegreeSepration()) {
                return edge.epoch;
            }
        }

        return Long.MAX_VALUE;
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

    public int numConnectedcomponents() {
        return this.connectedComponents;
    }

    @Override
    public void delete(Edge e) {
        initializeDSU(n);

        for (Edge edge: list) {
            if (!edge.isDeleted) {
                if ((edge.s==e.s) && (e.d == edge.d)) {
                    edge.isDeleted = true;
                } else {
                    join(edge);
                }
            }
        }
    }

    private int findSet(int s) {
        if (s == parent[s]) {
            return s;
        }
        return findSet(parent[s]);
    }
}

interface DSUI {
    void join(Edge e);
    int numConnectedcomponents();

    void delete(Edge e);
}

interface ParserServiceI {
    Edge parseInput(String relation);
}

class Edge implements Comparable<Edge> {
    int s;
    int d;
    long epoch;

    boolean isDeleted; // initially false


    @Override
    public int compareTo(Edge o) {

        if (this.s != o.s) {
            return Integer.compare(this.s, o.s);
        }

        return Integer.compare(this.d, o.d);
    }
}

class ShortestPathService implements ShortestPathI {
    int [][] adjMat;
    int n;

    public ShortestPathService(int n) {
        this.n = n;
        adjMat = new int[n][n];
    }

    @Override
    public boolean sixDegreeSepration() {
        int [][] shortestPAth = (int [][])adjMat.clone();
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (i==j) {
                    shortestPAth[i][j] = 1;
                }
                else if (shortestPAth[i][j] != 1) {
                    shortestPAth[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int k=0;k<n;k++) {
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                    if (shortestPAth[i][j] == Integer.MAX_VALUE) {
                       if (shortestPAth[i][k] != Integer.MAX_VALUE && shortestPAth[k][j] != Integer.MAX_VALUE) {
                           shortestPAth[i][j] = shortestPAth[i][k]+shortestPAth[k][j];
                       }
                    } else {
                        if (shortestPAth[i][k] != Integer.MAX_VALUE && shortestPAth[k][j] != Integer.MAX_VALUE) {
                            shortestPAth[i][j] = Math.min(shortestPAth[i][j],shortestPAth[i][k]+shortestPAth[k][j]);
                        }
                    }
                }
            }
        }

        int maxShortestPath = Integer.MIN_VALUE;

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (maxShortestPath < shortestPAth[i][j]) {
                    maxShortestPath = shortestPAth[i][j];
                }
            }
        }
        return maxShortestPath <=6;
    }

    @Override
    public void add(Edge e) {
        adjMat[e.s][e.d]=1;
        adjMat[e.d][e.s]=1;
    }

    @Override
    public void remove(Edge e) {
        adjMat[e.s][e.d]=0;
        adjMat[e.d][e.s]=0;
    }
}

interface ShortestPathI {
    boolean sixDegreeSepration();
     void add(Edge e);
     void remove(Edge e);

}