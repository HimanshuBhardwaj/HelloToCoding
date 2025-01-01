package Y2023.dec23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/23/2023
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String [] str = br.readLine().split(" ");
            ArrayList<Integer> l = new ArrayList<Integer>(n);
            ArrayList<Integer> r =  new ArrayList<Integer>(n);
            ArrayList<Long> c =  new ArrayList<Long>(n);

            for (int i=0;i<n;i++) {
                l.add(Integer.parseInt(str[i]));
            }

            str = br.readLine().split(" ");

            for (int i=0;i<n;i++) {
                r.add(Integer.parseInt(str[i]));
            }

            str = br.readLine().split(" ");

            for (int i=0;i<n;i++) {
                c.add(Long.parseLong(str[i]));
            }

            pw.append(minCost(n,l,r,c)+"\n");
        }
        pw.flush();
        pw.close();
    }


    private static long minCost(int n, ArrayList<Integer> l, ArrayList<Integer> r, ArrayList<Long> c) {
        TreeSet<Integer> treeSet = new TreeSet(r);

        Collections.sort(l,Collections.reverseOrder());

        ArrayList<Pair> p = new ArrayList<>();

        for (int ll: l) {
            int rr = treeSet.higher(ll);
            treeSet.remove(rr);
            p.add(new Pair(ll,rr));
        }

        Collections.sort(p);
        //System.out.println(p);
        Collections.sort(c, Collections.reverseOrder());

        long cost=0;

        for (int i=0;i<p.size();i++) {
            cost+= ((p.get(i).r-p.get(i).l)*c.get(i));
        }



        return cost;
    }
}

class Pair implements Comparable<Pair> {
    @Override
    public String toString() {
        return "Pair{" +
                "l=" + l +
                ", r=" + r +
                "}\n";
    }

    int l;
    int r;

    public Pair(int l, int r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public int compareTo(Pair o) {
        int tr = r-l;
        int or = o.r-o.l;

        return Integer.compare(tr, or);
    }
}
