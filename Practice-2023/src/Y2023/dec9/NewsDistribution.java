package Y2023.dec9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/9/2023
 */
public class NewsDistribution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph g = new Graph(n, m);

        for (int i=0;i<m;i++) {
            str=br.readLine().split(" ");
            ArrayList<Integer> gi = new ArrayList<>(str.length);
            for (String s: str) {
                gi.add(Integer.parseInt(s)-1);
            }
            g.insert(gi);
        }
        g.generateComponent();

        System.out.println(g.printMemberShips());
    }
}

class Graph {
    ArrayList<Integer> adjList[];

    int n;

    ArrayList<ArrayList<Integer>> groups;

    int groupMembership[];

    public Graph(int n, int m) {
        this.n = n;
        adjList = new ArrayList[n];
        groups = new ArrayList<>();
        groupMembership = new int[n];

        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList<>();
        }

    }

    void insert(ArrayList<Integer> gi) {
        for (int i=2;i<gi.size();i++) {
            adjList[gi.get(i)].add(gi.get(i-1));
            adjList[gi.get(i-1)].add(gi.get(i));
        }
    }

    public void generateComponent() {
        boolean [] isVisited = new boolean[n];

        for (int i=0;i<n;i++) {
            groupMembership[i] = -1;
        }

        for (int i=0;i<n;i++) {
            if (groupMembership[i]==-1) {
                ArrayList<Integer> group = new ArrayList<>();
                getGroupsMembersShips(i, group, isVisited);
                groups.add(group);
                for (int c:group) {
                    groupMembership[c] = groups.size()-1;
                }
            }
        }
    }

    private void getGroupsMembersShips(int node, ArrayList<Integer> group, boolean[] isVisited) {
        group.add(node);
        isVisited[node] = true;

        for (int ne: adjList[node]) {
            if (!isVisited[ne]) {
                getGroupsMembersShips(ne, group, isVisited);
            }
        }
    }

    public String printMemberShips() {
        StringBuffer sb = new StringBuffer();

        for (int i=0;i<n;i++) {
            int g = groupMembership[i];
            sb.append(groups.get(g).size()+" ");
        }

        return sb.toString();
    }
}
