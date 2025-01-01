package Y2024.feb13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/13/2024
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String [] str = br.readLine().split(" ");
            long [] a = new long[str.length];

            for (int i=0;i<str.length;i++) {
                a[i] = Long.parseLong(str[i]);
            }
            pw.append(isPossibleToEqualiseWater(a)).append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static String isPossibleToEqualiseWater(long[] a) {
        long sum=0;
        for (long x:a) {
            sum+=x;
        }

        sum = sum/a.length;
        long totalWater=0;
        for (int i=a.length-1;i>=0;i--) {
            totalWater+=a[i];
            if (totalWater > (sum*(a.length-i))) {
                return "NO";
            }
        }

        return "YES";
    }
}
