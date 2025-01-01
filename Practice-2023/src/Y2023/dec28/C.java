package Y2023.dec28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/28/2023
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String [] str = br.readLine().split(" ");
            long sum = 0;
            for (int i=0;i<str.length;i++) {
                sum+= Long.parseLong(str[i]);
            }

            long sqrt = (long)Math.sqrt(sum);

            if ((sqrt*sqrt) == sum ) {
                pw.append("YES\n");
            } else {
                pw.append("NO\n");
            }
        }
        pw.flush();
        pw.close();
    }
}
