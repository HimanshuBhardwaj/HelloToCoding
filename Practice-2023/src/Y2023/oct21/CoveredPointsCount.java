package Y2023.oct21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.Key;
import java.util.*;

/**
 * @author Himanshu Bhardwaj
 * @Date 10/21/2023
 * Codeforces: https://codeforces.com/contest/1000/problem/C
 */
public class CoveredPointsCount {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        ArrayList<Segment> segments = new ArrayList<>();
        int index = 0;

        for (;index<n;index++) {
            String [] str = br.readLine().split(" ");
            segments.add(new Segment(index, Long.parseLong(str[0]), Long.parseLong(str[1])));
        }

        int[] intersectingPoint = getIntersectingPoints(segments);

        for (Integer i: intersectingPoint) {
            pw.append(i+"  ");
        }

        pw.flush();
        pw.close();
    }

    private static int[] getIntersectingPoints(ArrayList<Segment> segments) {
        Collections.sort(segments);
        int [] numInCount = new int[segments.size()+1];

        TreeMap<Long, TreeSet<Segment>> numberMapping = new TreeMap<>();

        for (Segment segment: segments) {
            if (!numberMapping.containsKey(segment.s)) {
                numberMapping.put(segment.s, new TreeSet<>());
            }
            if (!numberMapping.containsKey(segment.e)) {
                numberMapping.put(segment.e, new TreeSet<>());
            }
            numberMapping.get(segment.s).add(segment);
            numberMapping.get(segment.e).add(segment);
        }

        int count=0;

        for (Map.Entry<Long, TreeSet<Segment>> entry: numberMapping.entrySet()) {

        }

        return null;
    }

    private static void updateCountSegments(int[] countSegments,
                                            Long previousIndex, TreeSet<Segment> previousValue,
                                            Long newIndex, TreeSet<Segment> newValue) {
        countSegments[newValue.size()] += 1;

        Long pointsBetween = Math.abs(newIndex-previousIndex-1);
        int segBetween = Math.min(previousValue.size(), newValue.size());
        countSegments[segBetween]+=pointsBetween;
    }

    private static boolean startingSeg(Long key, Segment s) {
        return (s.s == key);
    }
}

class Segment implements Comparable<Segment> {
    int index;
    public Segment(int index, long s, long e) {
        this.index = index;
        this.s = s;
        this.e = e;
    }

    long s;
    long e;

    @Override
    public int compareTo(Segment o) {
        if (this.s != o.s) {
            return Long.compare(this.s, o.s);
        } else {
            if (this.e != o.e) {
                return Long.compare(this.e, o.e);
            } else {
                return Integer.compare(this.index, o.index);
            }
        }
    }

    @Override
    public String toString() {
        return "Segment{" +
                "index=" + index +
                ", s=" + s +
                ", e=" + e +
                "}\n";
    }
}
