package Y2023.dec8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/8/2023
 */
public class PowerfulArray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int t = Integer.parseInt(str[1]);

        str = br.readLine().split(" ");

        ArrayList<Integer> arrayList = new ArrayList<>(str.length);

        for (String s: str) {
            arrayList.add(Integer.parseInt(s));
        }

        RangeDistinctElementQuery rangeDistinctElementQuery = new SqrtDecomposition(arrayList);

        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            str = br.readLine().split(" ");
            int l = Integer.parseInt(str[0])-1;
            int r = Integer.parseInt(str[1])-1;
            pw.append(rangeDistinctElementQuery.getPower(l,r)+"\n");
        }
        pw.flush();
        pw.close();
    }
}


class SqrtDecomposition implements RangeDistinctElementQuery {
    ArrayList<Integer> elements;
    int n;
    int totalSegments;
    int segSize;

    TreeMap<Integer, Integer>[] segments;
    int [] segN;

    public SqrtDecomposition(ArrayList<Integer> e) {
        this.elements = e;
        n = e.size();
        this.segSize = (int)Math.sqrt(n);
        this.totalSegments = (n/segSize)+ ((n%segSize == 0)?0:1);
        segments = new TreeMap[totalSegments];
        segN = new int[n];

        for (int i=0;i<totalSegments;i++) {
            segments[i] = new TreeMap<>();
        }

        for (int i=0;i<n;i++) {
            segN[i] = i/segSize;
            int segNum = segN[i];
            if (!segments[segNum].containsKey(e.get(i))) {
                segments[segNum].put(e.get(i),0);
            }
            segments[segNum].put(e.get(i), segments[segNum].get(e.get(i))+1);
        }
    }

    @Override
    public TreeMap<Integer, Integer>  distinctElement(int l, int r) {
        int segStart = segN[l];
        int segEnd = segN[r];

        TreeMap<Integer, Integer> distinctE = new TreeMap<>();
        for (int i=segStart+1;i<segEnd;i++) {
            merge(distinctE,segments[i]);
        }

        for (int i=l;(i<=r) && ((segN[i])==segStart);i++) {
            distinctE.put(elements.get(i), ((distinctE.containsKey(elements.get(i)))?distinctE.get(elements.get(i)):0)+1);
        }

        for (int i=segEnd*segSize;(i<=r)&&(segEnd != segStart);i++) {
            distinctE.put(elements.get(i), ((distinctE.containsKey(elements.get(i)))?distinctE.get(elements.get(i)):0)+1);
        }

        return distinctE;
    }

    @Override
    public long getPower(int l, int r) {
        TreeMap<Integer, Integer> distinctE = this.distinctElement(l,r);
        long power = 0;

        for (Map.Entry<Integer, Integer> e: distinctE.entrySet()) {
            power += ((e.getKey()*1l)*e.getValue()*e.getValue());
        }

        return power;
    }

    private void merge(TreeMap<Integer, Integer> distinctE, TreeMap<Integer, Integer> segment) {
        for (Map.Entry<Integer, Integer> entry: segment.entrySet()) {
            distinctE.put(entry.getKey(), ((distinctE.containsKey(entry.getKey()))?distinctE.get(entry.getKey()):0)+entry.getValue());
        }
    }
}

interface RangeDistinctElementQuery {
    TreeMap<Integer, Integer> distinctElement(int l, int r);
    long getPower(int l, int r);
}
