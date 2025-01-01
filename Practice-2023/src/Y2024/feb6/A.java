package Y2024.feb6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/6/2024
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String str = br.readLine();
            pw.append(minStrips(str)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static int minStrips(String str) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i=0;i<str.length();i++) {
            if (str.charAt(i)=='B') {
                min = Math.min(min, i);
                max = Math.max(max, i);
            }
        }

        if (min == Integer.MAX_VALUE) {
            return str.length();
        }
        return max-min+1;
    }
}
