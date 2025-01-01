package Y2023.nov25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Himanshu Bhardwaj
 * @Date 11/25/2023
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);


        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String [] str = br.readLine().split(" ");
            int [] a = new int[n];

            for (int i=0;i<n;i++) {
                a[i] = Integer.parseInt(str[i]);
            }

            pw.append(isSortPossible(a)).append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static String isSortPossible(int[] a) {
        int []sa = a.clone();
        Arrays.sort(sa);

        if (a[0] != sa[0]) {
            return "NO";
        }
        for (int j=0;j<10;j++) {
            for (int i=0;i<a.length;i++) {
                for (int k=i+1;k<a.length-1;k++) {
                    if ((a[k] > a[k-1]) && (a[k] > a[k+1])) {
                        int temp = a[k];
                        a[k] = a[k+1];
                        a[k+1] = temp;
                    }
                }
            }
        }

        for (int i=0;i<a.length;i++) {
            if (a[i] != sa[i]) {
                return "NO";
            }
        }

        return "YES";
    }
}
