package dec24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            long n = Long.parseLong(br.readLine());

            pw.append(numberCoins(n)+"\n");

        }
        pw.flush();
        pw.close();

    }

    private static long numberCoins(long n) {
        if (n<=3) {
            return 1;
        }

        long c= 2*numberCoins(n/4);
        return c;
    }
}
