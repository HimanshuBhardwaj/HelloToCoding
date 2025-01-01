package Y2023.nov3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Himanshu Bhardwaj
 * @Date 11/3/2023
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] str = br.readLine().split(" ");

            int [] a = new int[str.length];

            for (int i=0;i<a.length;i++) {
                a[i] = Integer.parseInt(str[i]);
            }
            pw.append(printSeq(a));
        }
        pw.flush();
        pw.close();
    }

    private static String printSeq(int[] a) {
        Arrays.sort(a);

        int xs=0;
        int ys = a.length/2;

        int num = a[ys-1]-a[xs]+a[a.length-1]-a[ys];
        StringBuilder sb = new StringBuilder();
        sb.append(num);
        sb.append("\n");
        for (int i=xs;i<ys;i++) {
            sb.append(a[i]).append(" ").append(a[i+a.length/2]).append("\n");
        }
        return sb.toString();
    }
}
