package Y2023.december3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/3/2023
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (tc-- > 0) {
            String [] str = br.readLine().split(" ");
            long n = Long.parseLong(str[0]);
            long p = Long.parseLong(str[1]);
            long l = Long.parseLong(str[2]);
            long t = Long.parseLong(str[3]);

            pw.append(maxRestDays(0, n, n,l,t,p)+"\n");
        }

        pw.flush();
        pw.close();
    }

    // s => itne din kaam kare
    // e ==> end
    private static long maxRestDays(long s, long e, long n, long l, long t, long p) {
        if (isPossibleToRestDays(s,n,l,t,p)) {
            return n-s;
        }

        while (s < e) {
            if (isPossibleToRestDays(s,n,l,t,p)) {
                return n-s;
            }

            if ((s+1) == e ) {
                return n-e;
            }

            long mid = s+ ((e-s)/2);

            if (isPossibleToRestDays(mid,n,l,t,p)) {
                e = mid;
            } else {
                s = mid;
            }
        }

        return 0;
    }



    private static boolean isPossibleToRestDays(long r,long n, long l, long t, long p) {
        long sd = r;
        long points = l*sd;

        if (points>=p) {
            return true;
        }

        long totalTasks = (n+6)/7;
        long totalTasksCouldBrCompleted = Math.min(sd*2, totalTasks);
        points+= (t*totalTasksCouldBrCompleted);

        return points>=p;
    }
}
