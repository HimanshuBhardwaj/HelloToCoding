package Y2023.nov27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.zip.Adler32;

/**
 * @author Himanshu Bhardwaj
 * @Date 11/27/2023
 */
public class AnjisBinaryTree {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String op = br.readLine();

            Tree tree = new Tree(n);

            for (int i=0;i<n;i++) {
                String [] str = br.readLine().split(" ");
                int l = Integer.parseInt(str[0]);
                int r = Integer.parseInt(str[1]);

                if (l != 0) {
                    tree.insert(i,l-1, (op.charAt(i) == 'L') ? 0 : 1);
                }

                if (r != 0) {
                    tree.insert(i,r-1, (op.charAt(i) == 'R') ? 0 : 1);
                }
            }

            // tree.print();
            pw.append(tree.shortestDistanceToLeaf(0)+"\n");
        }
        pw.flush();
        pw.close();
    }
}

class Tree {
    ArrayList<Edge> adjList[];
    int n;
    Node node[];

    public Tree(int n) {
        adjList = new ArrayList[n];
        this.node = new Node[n];


        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList<>();
            node[i] = new Node(i);
        }

        this.n = n;
    }

    void insert(int s, int d, int w) {
        Edge edge = new Edge(node[s], node[d], w);
        adjList[s].add(edge);
    }

    public int shortestDistanceToLeaf(int root) {
        Comparator<Node> comparator = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (Integer.compare(o1.SD, o2.SD)==0) {
                    return Integer.compare(o1.index, o2.index);
                }
                return Integer.compare(o1.SD, o2.SD);
            }
        };

        TreeSet<Node> treeSet = new TreeSet<>(comparator);

        node[root].SD=0;
        treeSet.add(node[root]);
        int count=0;

        while (!treeSet.isEmpty()) {
            Node r = treeSet.first();
            treeSet.remove(r);

            if (isLeaf(r.index)) {
                return r.SD;
            }

            for (Edge edge: adjList[r.index]) {
                Node d = (edge.s.index == r.index)? edge.d: edge.s;
                if (d.SD > r.SD+edge.weight) {
                    treeSet.remove(d);
                    d.SD = r.SD+edge.weight;
                    treeSet.add(d);
                }
            }
        }

        return 0;
    }

    public void print() {
        for (int i=0;i<n;i++) {
            System.out.print(i+": ");
            for (Edge e: adjList[i]) {
                System.out.print("("+e.s+", "+e.weight+"),");
            }
            System.out.println();
        }
    }

    private boolean isLeaf(int n) {
        return adjList[n].size()==0;
    }
}

class Edge {
    Node s;
    Node d;
    int weight;

    public Edge(Node s, Node d, int weight) {
        this.s = s;
        this.d = d;
        this.weight = weight;
    }
}

class Node {
    int index;
    int SD = Integer.MAX_VALUE;

    public Node(int i) {
        this.index = i;
    }
}
