package Y2023.dec19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/19/2023
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);
            long [] a = new long[n];
            long [] b = new long[n];

            str = br.readLine().split(" ");

            for (int i=0;i<n;i++) {
                a[i] = Long.parseLong(str[i]);
            }

            str = br.readLine().split(" ");

            for (int i=0;i<n;i++) {
                b[i] = Long.parseLong(str[i]);
            }

            pw.append(maxExp(n,k,a,b)+"\n");


        }
        pw.flush();
        pw.close();
    }

    private static long maxExp(int n, int k, long[] a, long[] b) {
        int n1=n;
        n = Math.min(n,k);
        long max [] = new long[n+1];
        max[0]=b[0];
        for (int i=1;i<n;i++) {
            max[i] = Math.max(max[i-1], b[i]);
        }
        max[n] = max[n-1];

        long dp[] = new long[n+1];
        dp[n] = max[n-1];

        dp[n-1] = Math.max(max[n-2], dp[n]+a[n-1]);

        for (int i=n-2;i>0;i--) {
            dp[i] = Math.max(max[i-1], dp[i+1]+a[i]);
        }
        dp[0] = dp[1]+a[0];

        return -1;
    }
}
