package Y2023.june10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TwoCakes {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int a = Integer.parseInt(str[1]);
        int b = Integer.parseInt(str[2]);

        int min = Integer.MIN_VALUE;

        for (int x = 1; x<n;x++) {
            int valA = (int)Math.floor(((double)a)/x);
            int valB = (int)Math.floor(((double)b)/(n-x));

            min = Math.max(min, Math.min(valA,valB));
        }

        System.out.println(min);
    }
}
