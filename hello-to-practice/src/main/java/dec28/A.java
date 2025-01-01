package dec28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String [] str = br.readLine().split(" ");
            long [] a = new long[n];
            
            for (int i=0;i<n;i++) {
                a[i] = Long.parseLong(str[i]);
            }
            
            pw.append(isPossibleToPartition(a)?"YES\n":"NO\n");
        }
        pw.flush();
        pw.close();
    }

    private static boolean isPossibleToPartition(long[] a) {
        int c=0;

        for (int i=0;i<a.length-1;i++) {
            long min = Math.min(a[i],a[i+1]);
            long max = Math.max(a[i],a[i+1]);
            if (2*min <= max) {
                c++;
            }
        }

        if (c == a.length-1) {
            return false;
        }
        return true;
    }
}
