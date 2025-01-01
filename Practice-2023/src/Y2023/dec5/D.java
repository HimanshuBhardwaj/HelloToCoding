package Y2023.dec5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/5/2023
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            ArrayList<Segment> segments = new ArrayList<>();
            int []s = new int[n];
            int []e = new int[n];

            for (int i=0;i<n;i++) {
                String [] str = br.readLine().split(" ");
                s[i] = Integer.parseInt(str[0]);
                e[i] = Integer.parseInt(str[1]);
                segments.add(new Segment(s[i],e[i]));
            }

            pw.append(minimumK(n,s,e, segments)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static long minimumK(int n, int[] st, int[] en, ArrayList<Segment> segments) {
        int s=0;
        int max = -1;

        for (Segment sg: segments) {
            max = Math.max(max, (int)sg.r);
        }
        int e = max;

        HashMap<Integer, Boolean> cachingR = new HashMap<Integer, Boolean>();

        while (s<e) {
            if (isPossible(s,segments)) {
                return s;
            }

            if (s == (e-1)) {
                return e;
            }

            int mid = (s+e)/2;
            Boolean result = cachingR.get(mid);

            if (result == null) {
                result = isPossible(mid, segments);
                cachingR.put((int)mid, result);
            }

            if (result) {
                e=mid;
            } else {
                s = mid;
            }
        }


        return s;
    }

    private static boolean isPossible(long k,  ArrayList<Segment> segments) {
        long s=0;
        long e=0;

        boolean isPossible = true;

        for (int i=0;isPossible && (i<segments.size());i++) {
            s = s-k;
            e = e+k;

            if (!hasIntersection(s,e,segments.get(i))) {
                isPossible = false;
            }

            s = Math.max(segments.get(i).l,s);
            e = Math.min(segments.get(i).r,e);
        }

        return isPossible;
    }

    private static boolean hasIntersection(long s, long e, Segment segment) {
        if ((segment.l<=e && segment.r>=s)) {
            return true;
        }

        return false;
    }
}

class Segment implements Comparable<Segment>{
    public Segment(int l, int r) {
        this.l = l;
        this.r = r;
    }
    public Segment(Segment s) {
        this.l = s.l;
        this.r = s.r;
    }

    long l;
    long r;

    @Override
    public int compareTo(Segment o) {
        if (this.l != o.l) {
            return Long.compare(this.l, o.l);
        }

        return Long.compare(o.r, this.r);
    }
}