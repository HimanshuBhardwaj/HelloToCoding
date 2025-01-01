package Y2023.dec16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/16/2023
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String []str = br.readLine().split(" ");
             int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            pw.append(Math.max(n,m)+"\n");
        }
        pw.flush();
        pw.close();
    }
}
