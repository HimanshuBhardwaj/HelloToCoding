package Y2023.dec23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/23/2023
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            long n = Long.parseLong(str[0]);
            long k = Long.parseLong(str[1]);

            str = br.readLine().split(" ");

            long [] arr = new long[(int)n];
            long sum=0;
            TreeSet<Long> set = new TreeSet<>();

            for (int i=0;i<n;i++) {
                arr[i] = Long.parseLong(str[i]);
                sum+=arr[i];
                set.add(arr[i]);
            }

            if (set.size()==1) {
                pw.append("0\n");
            } else {
                pw.append(min(k,n,sum)+"\n");
            }



        }
        pw.flush();
        pw.close();
    }

    private static int min(long k, long n, long sum) {
        for (int i=1;i<=n;i++) {
            if (((i*k+sum)%(n+i)) == 0) {
                return i;
            }
        }
        return -1;
    }
}
