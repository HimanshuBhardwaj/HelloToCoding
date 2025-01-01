package Y2023.dec30;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/30/2023
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);

            int [] a = new int[n];
            str = br.readLine().split(" ");

            for (int i=0;i<str.length;i++) {
                a[i] = Integer.parseInt(str[i]);
            }

            pw.append(resultingSubArray(a,k)).append("\n");

        }
        pw.flush();
        pw.close();
    }

    private static String resultingSubArray(int[] a, int k) {
        long p = 1;

        for (int i=0;i<a.length;i++) {
            p*=a[i];
        }

        if ((2023l%p)!=0) {
            return "NO";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("YES\n");
            for (int i=0;i<k-1;i++) {
                sb.append("1 ");
            }
            sb.append((2023/p)+"");
            return sb.toString();
        }
    }
}
