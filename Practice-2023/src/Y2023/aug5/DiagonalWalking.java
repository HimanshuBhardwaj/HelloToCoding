package Y2023.aug5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DiagonalWalking {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();

        for (int i=0;i<num;i++) {
            String [] str = br.readLine().split(" ");
            long n = Long.parseLong(str[0]);
            long m = Long.parseLong(str[1]);
            long k = Long.parseLong(str[2]);
            sb.append(getMaxDigonalMoves(n, m, k));
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }

    private static long getMaxDigonalMoves(long n, long m, long k) {
        if (Math.max(n,m) > k) {
            return -1;
        }
        long num = num  = (k - Math.max(n, m)) / 2;
        if ((k - Math.max(n, m)) % 2 != 0) {
           return (2*num) + Math.max(n, m) - 1;
        }

        return (2*num) + Math.max(n, m);
    }
}
