package Y2024.feb27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/27/2024
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int [] arr = new int[n];
            String [] str = br.readLine().split(" ");

            for (int i=0;i<n;i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
            pw.append(maxSum(arr)+"\n");
        }

        pw.flush();
        pw.close();
    }

    private static long maxSum(int[] arr) {

        Arrays.sort(arr);
        long sum=0;
        for (int x: arr) {
            sum+= Math.abs(x);
        }
        return sum;

    }
}
