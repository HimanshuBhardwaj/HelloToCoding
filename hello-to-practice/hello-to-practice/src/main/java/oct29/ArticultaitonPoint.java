package oct29;

import java.util.ArrayList;
import java.util.List;

public class ArticultaitonPoint {
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.insert(0, 1);
        graph.insert(1, 2);
        graph.insert(2, 3);
        graph.insert(3, 4);
        graph.insert(4, 5);
       // graph.insert(5, 6);


        System.out.println(graph.findArticulationPoints(0));

    }
}

class Graph implements ArticulationPointI {
    int numNodes;
    ArrayList<Integer> adjList[];
    int clock;

    public Graph(int n) {
        adjList = new ArrayList[n];
        for (int i=0;i<adjList.length;i++) {
            adjList[i] = new ArrayList<>();
        }
        this.numNodes = n;
    }

    public void insert(int s, int d) {
        adjList[s].add(d);
        adjList[d].add(s);
    }

    @Override
    public List<Integer> findArticulationPoints(int root) {
        List<Integer> articulationPoints = new ArrayList<>();
        int [] discoveryTime = new int[numNodes];
        int [] finishtime = new int[numNodes];
        int [] minDiscoverytime = new int[numNodes];

        for (int i=0;i<numNodes;i++) {
            discoveryTime[i] = finishtime[i] = -1;
        }

        findArticulationPointsHelper(root, -1, articulationPoints, discoveryTime, finishtime, minDiscoverytime);

        return articulationPoints;
    }

    private int findArticulationPointsHelper(int root, int parent, List<Integer> articulationPoints, int[] discoveryTime,
                                             int[] finishtime, int[] minDiscoverytime) {
        incrementClock();
        discoveryTime[root] = clock;

        for (int neighbour: adjList[root]) {
            if ((neighbour != parent) && (discoveryTime[neighbour]==-1)) {
                findArticulationPointsHelper(neighbour, root, articulationPoints, discoveryTime, finishtime, minDiscoverytime);
            }
        }

        int mdt = clock;

        for (int neighbour: adjList[root]) {
            if (neighbour != parent) {
                mdt = Math.min(mdt, minDiscoverytime[neighbour]);
            }
        }

        minDiscoverytime[root] =  mdt;

        if((mdt != Integer.MAX_VALUE) && (mdt > discoveryTime[root])) {
            articulationPoints.add(root);
        }

        incrementClock();
        finishtime[root] = clock;
        return mdt;
    }

    void resetClock() {
        clock=0;
    }

    void incrementClock() {
        clock++;
    }
}

interface ArticulationPointI {
    List<Integer> findArticulationPoints(int root);
}
