package Y2023.dec23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/23/2023
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            long [] arr = new long[n];
            String [] str = br.readLine().split(" ");

            for (int i=0;i<n;i++) {
                arr[i] = Long.parseLong(str[i]);
            }
            pw.append(findK(arr,n)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static long findK(long[] arr, int n) {
        long num=2;

        while (num >=0 && num<=1000000000000000000l) {
            TreeSet<Long> rem = new TreeSet<>();

            for (int i=0;i<arr.length;i++) {
                rem.add(arr[i]%num);
                if (rem.size() > 2) {
                    break;
                }
            }

            if (rem.size() == 2) {
                return num;
            }
            num = num<<1;
        }

        return 0;

    }

}