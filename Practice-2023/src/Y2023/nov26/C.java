package Y2023.nov26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 11/26/2023
 */
public class C {
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
                    tree.insert(i,l-1);
                }

                if (r != 0) {
                    tree.insert(i,r-1);
                }
            }
            pw.append(tree.minNodes(0, 0, op, 0, -1)+"\n");
        }
        pw.flush();
        pw.close();
    }
}

class Tree {
    ArrayList<Integer> [] adjList;
    HashMap<Integer, Integer> parentP = new HashMap<>();

    public Tree(int n) {
        adjList = new ArrayList[n];

        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void insert(int s, int d) {
        adjList[s].add(d);
        parentP.put(s,d);
    }

    int minNodes(int index, int posS, String str, int count, int parent) {
        if (adjList[index].size()==0) {
            return count;
        }

        if (posS == str.length()) {
            return Integer.MAX_VALUE;
        }

        int mcc = Integer.MAX_VALUE;

        if (str.charAt(posS)=='U') {
            if (parent == -1) {
                int tmcc = minNodes(adjList[index].get(1),posS+1,str,count+1,index);
                int tmcc2 = minNodes(adjList[index].get(0),posS+1,str,count+1,index);
                mcc = Math.min(tmcc2,tmcc);
            } else {
                int patentParent = parentP.containsKey(parentP.get(index)) ? parentP.get(parentP.get(index)) : -1;
                int tmcc = minNodes(parentP.get(index),posS+1,str,count,patentParent);
                int tmcc2 = (adjList[index].size()>0)?minNodes(adjList[index].get(0),posS+1,str,count+1,index):Integer.MAX_VALUE;
                int tmcc3 = (adjList[index].size()>1)?minNodes(adjList[index].get(1),posS+1,str,count+1,index):Integer.MAX_VALUE;
                mcc = Math.min(tmcc2,tmcc);
                mcc = Math.min(mcc,tmcc3);
            }
            //
        }  else if (str.charAt(posS)=='R') {
            int tmcc = (adjList[index].size()>1)?minNodes(adjList[index].get(1),posS+1,str,count,index): Integer.MAX_VALUE;
            int tmcc2 = (adjList[index].size()>0)?minNodes(adjList[index].get(0),posS+1,str,count+1,index):Integer.MAX_VALUE;

            int patentParent = parentP.containsKey(parentP.get(index)) ? parentP.get(parentP.get(index)) : -1;
            int tmcc3 = minNodes(parentP.get(index),posS+1,str,count,patentParent);

            mcc = Math.min(tmcc2,tmcc);
            mcc = Math.min(mcc,tmcc3);
        } else { //L
            int tmcc = (adjList[index].size()>0)?minNodes(adjList[index].get(0),posS+1,str,count,index):Integer.MAX_VALUE;
            int tmcc2 = (adjList[index].size()>1)?minNodes(adjList[index].get(1),posS+1,str,count+1,index):Integer.MAX_VALUE;
            mcc = Math.min(tmcc2,tmcc);

            int patentParent = parentP.containsKey(parentP.get(index)) ? parentP.get(parentP.get(index)) : -1;
            int tmcc3 = minNodes(parentP.get(index),posS+1,str,count,patentParent);
            mcc = Math.min(mcc,tmcc3);
        }

        return mcc;
    }
}
