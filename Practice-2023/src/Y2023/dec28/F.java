package Y2023.dec28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/28/2023
 */
public class F {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            ArrayList<Seg> segs = new ArrayList<>();

            for (int i=0;i<n;i++) {
                String [] s = br.readLine().split(" ");
                long a = Long.parseLong(s[0]);
                long b = Long.parseLong(s[1]);
                Seg seg = new Seg(a,b);
                segs.add(seg);
            }
            pw.append(countGreeting2(segs)+"\n");

        }
        pw.flush();
        pw.close();
    }

    private static int countGreeting2(ArrayList<Seg> segs) {
        TreeMap<Long, Seg> segHashMap = new TreeMap<>();

        for (Seg seg : segs) {
            segHashMap.put(seg.a,seg);
            segHashMap.put(seg.b,seg);
        }


        int gc=0;
        int ce=0;

        for(Map.Entry<Long, Seg> entry: segHashMap.entrySet()) {
            if (isStartValue(entry.getKey(), entry.getValue())) {
                entry.getValue().c=ce;
            } else {
                gc += ce-entry.getValue().c;
                ce++;
            }
        }

        return gc;
    }

    private static boolean isStartValue(Long key, Seg value) {
        if (key == value.a) {
            return true;
        }
        return false;
    }

    private static int countGreeting(ArrayList<Seg> segs) {
        Collections.sort(segs);
        TreeMap<Seg, Integer> treeMap = new TreeMap();

        for (int i=0;i<segs.size();i++) {
            treeMap.put(segs.get(i),i+1);
        }


        int count=0;
        for (int i=0;i<segs.size();i++) {
            Seg seg1 = new Seg(0,segs.get(i).a+1);
            Seg seg2 = new Seg(0,segs.get(i).b-1);

            if (seg1.b > seg2.b) {
                continue;
            }

            Seg seg1C = treeMap.ceilingKey(seg1);
            Seg seg2f = treeMap.floorKey(seg2);

            if (seg1C != null && seg2f != null) {
                count += (treeMap.get(seg2f) - treeMap.get(seg1C))+1;
            }
        }



        return count;
    }
}


class Seg implements Comparable<Seg>{
    public Seg(long a, long b) {
        this.a = a;
        this.b = b;
    }

    long a;
    long b;
    int c;


    @Override
    public int compareTo(Seg o) {
        return Long.compare(this.b, o.b);
    }
}