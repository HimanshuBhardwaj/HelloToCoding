package Y2024.may10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 5/10/2024
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            int c = Integer.parseInt(str[2]);
            int d = Integer.parseInt(str[3]);
            pw.append(areIntersecting(Math.min(a,b), Math.max(a,b),c,d)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static String areIntersecting(int a, int b, int c, int d) {
        int cc=0;
        for (int i=a+1;i<=b-1;i++) {
            if (i==c) {
                cc++;
            }
            if (i==d) {
                cc++;
            }
        }

        if (cc==1) {
            return "YES";
        } else {
            return "NO";
        }
    }
}
