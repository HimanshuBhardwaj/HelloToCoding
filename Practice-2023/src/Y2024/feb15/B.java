package Y2024.feb15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/15/2024
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String[] str = br.readLine().split(" ");

            long n = Integer.parseInt(str[0]);
            long k = Integer.parseInt(str[1]);

            pw.append(minSq(n,k)+"\n");
        }

        pw.flush();
        pw.close();
    }

    private static long minSq(long n, long k) {
        if (k<= ((2*n)+1)) {
            return (k+1)/2;
        }
        if (k <= (4*n - 4)) {
            return (k+1)/2;
        }

        long rem = k-(4*n - 4);

        return (2*n -2)+rem;
    }
}
