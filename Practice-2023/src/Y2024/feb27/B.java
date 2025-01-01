package Y2024.feb27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/27/2024
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int [] arr = new int[n];
            String [] str = br.readLine().split(" ");

            for (int i=0;i<n;i++) {
                arr[i] = Integer.parseInt(str[i])%3;
            }
            pw.append(numMoves(arr)+"\n");
        }

        pw.flush();
        pw.close();
    }

    private static int numMoves(int[] arr) {
        int zc=0,oc=0,tc=0;
        long sum=0;

        for (int x:arr) {
            sum+=x;
            if (x==0) {
                zc++;
            } else if (x==1) {
                oc++;
            } else {
                tc++;
            }
        }

        if (sum%3 == 0) {
            return 0;
        }

        if ((sum%3) ==1) {
            if (oc>0) {
                return 1;
            }
                return 2;
        }

        return 1;
    }
}
