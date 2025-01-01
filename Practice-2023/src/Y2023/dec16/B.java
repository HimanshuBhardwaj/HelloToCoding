package Y2023.dec16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/16/2023
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            Tree tree = new Tree(n);
            for (int i=0;i<(n-1);i++) {
                String[] str = br.readLine().split(" ");
                int s = Integer.parseInt(str[0]) - 1;
                int d = Integer.parseInt(str[1]) - 1;
                tree.insert(s,d);
            }
            pw.append(tree.numOp()+"\n");
        }

        pw.flush();
        pw.close();
    }
}


class Tree {
    ArrayList<Integer> [] adjList;
    int n;

    public Tree(int n) {
        this.n = n;
        adjList = new ArrayList[n];

        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void insert(int s, int d) {
        adjList[s].add(d);
        adjList[d].add(s);
    }

    int numOp() {
        int count = 0;
        for (int i=0;i<n;i++) {
            if (adjList[i].size()==1) {
                count++;
            }
        }

        return (count+1)/2;
    }
}