package Y2023.dec19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/19/2023
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();

            String[] str = br.readLine().split(" ");

            long a [] = new long[str.length];
            long b [] = new long[str.length];
            long c [] = new long[str.length];

            for (int i=0;i<str.length;i++) {
                a[i] =Long.parseLong(str[i]);
            }

            str = br.readLine().split(" ");
            for (int i=0;i<str.length;i++) {
                b[i] = Long.parseLong(str[i]);
            }

            str = br.readLine().split(" ");
            for (int i=0;i<str.length;i++) {
                c[i] = Long.parseLong(str[i]);
            }

            pw.append(getSum(a,b,c)+"\n");

        }
        pw.flush();
        pw.close();
    }

    private static long getSum(long[] a, long[] b, long[] c) {
return -1;
    }
}

class Day implements Comparable<Day>{
    long a;
    long b;

    public Day( long index, long a, long b, long c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.index = index;
    }

    long c;
    long index;

    @Override
    public int compareTo(Day o) {
        long tm = Math.max(Math.max(this.a, this.b), this.c);
        long om = Math.max(Math.max(o.a, o.b), o.c);
        return Long.compare(tm, om);
    }
}
