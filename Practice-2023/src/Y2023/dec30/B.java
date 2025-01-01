package Y2023.dec30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/30/2023
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            pw.append(getX(a,b)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static long getX(long a, long b) {
        if (a==1) {
            return b*b;
        }

        long gcd = gcd((int)a,(int)b);

        long t_a = a/gcd;
        long t_b = b/gcd;
        long x = t_a*t_b*gcd;
        if (x==b) {
                return t_b * t_b * gcd;
        } else {
            return x;
        }
    }

    static long gcd(int a, int b) {
        if (a<b) {
            return gcd(b,a);
        }
        if (b==0) {
            return a;
        }
        if (b==1) {
            return 1;
        }
        return gcd(a%b,b);
    }
}
