package dec24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            long l = Long.parseLong(str[0]);
            long r = Long.parseLong(str[1]);
            long g = Long.parseLong(str[2]);

            pw.append(maxDiff(l,r,g)+"\n");
        }
        pw.flush();
        pw.close();

    }

    private static long maxDiff(long l, long r, long g) {
        long alpha = l/g + (l%g==0 ? 0:1);
        long beta = r/g;

        if (alpha*g > r) {
            return -1;
        }

        System.out.println(alpha*g + "\t"+beta*g);

        return (beta-alpha)*g;
    }
}
