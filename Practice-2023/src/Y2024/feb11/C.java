package Y2024.feb11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/11/2024
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            long n = Long.parseLong(str[0]);
            long x = Long.parseLong(str[1]);

            if ((n+x) %2 != 0 ) {
                pw.append(0+"\n");
            } else {
                int factors1 = findFactors((n-x)/2);
                int factors2 = findFactors(((n+x)/2)-1);
                pw.append((factors2+factors1)+"\n");
            }
        }
        pw.flush();
        pw.close();
    }

    private static int findFactors(long l) {
        int x = (int)l;
        TreeSet<Integer> factors = new TreeSet<>();
        int count=0;

        for (int i = 2; i<=Math.sqrt(x);i++) {
            if (x%i==0) {
                count = count+2;
            }
        }

        return count;
    }
}
