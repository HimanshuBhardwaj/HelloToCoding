package Y2023.dec24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/24/2023
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

            for (int i=0;i<n;i++) {
                arr[i] = Integer.parseInt(str[i]);
            }

            pw.append(getResult(n,arr)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static String getResult(int n, int[] arr) {
        boolean containsZero = false;
        int numNeg=0;

        for (int x: arr) {
            if (x==0) {
                containsZero = true;
                break;
            }
            if (x<0) {
                numNeg++;
            }
        }

        if (containsZero) {
            return "0";
        }

        if (numNeg==0) {
            return "1\n1 0";
        }

        if (numNeg%2==0) {
            return "1\n1 0";
        } else {
            return "0";
        }


    }
}
