package Y2023.dec30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/30/2023
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);
        int [] DP = computeDP(100009);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            long [] arr = new long[n];
            String [] str = br.readLine().split(" ");
            for (int i=0;i<n;i++) {
                arr[i] = Long.parseLong(str[i]);
            }
            pw.append(findValues(arr, DP)).append("\n");
        }

        pw.flush();
        pw.close();
    }

    private static String findValues(long[] arr, int[] dp) {
        long [] suffixSum = new long[arr.length];
        suffixSum[0]=arr[0];
        long evenC= (arr[0]+1)%2;
        long oddC= arr[0]%2;

        StringBuffer sb = new StringBuffer();
        sb.append(arr[0]+" ");

        for (int i=1;i<suffixSum.length;i++) {
            suffixSum[i] = suffixSum[i-1]+arr[i];
            if (arr[i] %2 ==0) {
                evenC++;
            } else {
                oddC++;
            }

            computeSum(i%2,suffixSum[i],evenC, oddC, dp,sb);
        }

        return sb.toString();
    }

    private static void computeSum(int chance, long suffixSum, long evenC, long oddC, int[] dp, StringBuffer sb) {
        if (evenC==0) {
            if (oddC==1) {
                sb.append(suffixSum);
            } else if (oddC==2) {
                sb.append(suffixSum);
            } else {
                    sb.append(suffixSum - dp[(int) oddC - 2]);
            }
        } else {
            if (oddC == 0) {
                sb.append(suffixSum);
            } else if (oddC == 1) {
                sb.append(suffixSum - 1);
            } else {
                    sb.append(suffixSum - dp[(int) oddC - 2]);

            }
        }

        sb.append(" ");
    }

    // This will tell ke kitna loss hoga
    private static int[] computeDP(int n) {
        int [] dp = new int[n];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i=3;i<n;i++) {
            dp[i] = 1+ dp[i-3];
        }

        return dp;
    }
}
