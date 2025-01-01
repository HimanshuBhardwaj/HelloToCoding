package Y2023.dec19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/19/2023
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);
            pw.append(printOrder(n,k)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static String printOrder(int n, int k) {
        StringBuffer sb = new StringBuffer();
        for (int i=1;i<=k;i++) {
            sb.append(i+" ");
        }

        for (int i=n;i>k;i--) {
            sb.append(i+" ");
        }

        return sb.toString();
    }
}
