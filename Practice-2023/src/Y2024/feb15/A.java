package Y2024.feb15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/15/2024
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String [] str = br.readLine().split(" ");
            int [] arr = new int[n];
            int max = 0;
            int min = Integer.MAX_VALUE;

            for (int i=0;i<n;i++) {
                arr[i] = Integer.parseInt(str[i]);
                max = Math.max(max, arr[i]);
                min = Math.min(min, arr[i]);
            }

            pw.append((max-min)+"\n");
        }

        pw.flush();
        pw.close();
    }
}
