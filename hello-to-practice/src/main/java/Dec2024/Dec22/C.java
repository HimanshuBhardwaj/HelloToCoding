package Dec2024.Dec22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            long n = Long.parseLong(str[0]);
            long m = Long.parseLong(str[1]);
            long k = Long.parseLong(str[2]);

            long a[] = new long[(int)m];
            str = br.readLine().split(" ");

            for (int i=0;i<str.length;i++) {
                a[i] = Long.parseLong(str[i]);
            }

            long q[] = new long[(int)k];
            str = br.readLine().split(" ");

            for (int i=0;i<k;i++) {
                q[i] = Long.parseLong(str[i]);
            }

            pw.append(possibleSets(n,a,q)+"\n");
        }
        pw.flush();
        pw.close();

    }

    private static String possibleSets(long n, long[] a, long[] q) {
        if (q.length < n-1) {
            StringBuilder sb = new StringBuilder();

            for (int i=0;i<a.length;i++) {
                sb.append('0');
            }

            return sb.toString();
        }

        long mq = (n*(n+1))/2;

        for (long x: q) {
            mq-=x;
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0;i<a.length;i++) {
            if (mq==0 || a[i]==mq) {
                sb.append(1);
            } else {
                sb.append(0);
            }

        }


        return sb.toString();
    }
}
