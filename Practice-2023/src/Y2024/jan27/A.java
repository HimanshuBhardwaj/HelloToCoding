package Y2024.jan27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 1/27/2024
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

            pw.append(getString(n,k)).append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static String getString(int n, int k) {
        int ap = Math.min(n,k);
        StringBuffer sb = new StringBuffer();

        for (int i=0;i<n;i++) {
            sb.append(getStr(k));
        }

        return sb.toString();
    }

    private static String getStr(int ap) {
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<ap;i++) {
            sb.append((char)('a'+i));
        }
        return sb.toString();
    }
}
