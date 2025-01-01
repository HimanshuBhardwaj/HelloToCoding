package Y2024.feb15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/15/2024
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");

            long k = Long.parseLong(str[0]);
            long x = Long.parseLong(str[1]);
            long a = Long.parseLong(str[2]);
            pw.append(isPossible(k,x,a)?"YES":"NO").append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static boolean isPossible(long k, long x, long a) {
        return isPossible1(k,x,a) || isPossible2(k,x,a);
    }

    private static boolean isPossible1(long k, long x, long a) {

        long ap=a;
       long perHeadM = (a/k) + ((a%k == 0)?0:1);

       if (perHeadM==0) {
           return false;
       }

       for (int i=1;i<=x;i++) {
           a = a - perHeadM;
           if ((a+(perHeadM*k)) < ap) {
               return false;
           }
       }
       return (a*k >= ap);
    }

    private static boolean isPossible2(long k, long x, long a) {
        long ap = a;
        long bet=0;
        boolean win=false;

        // i means -> he wins after i times
        for (int i=1;i<=x;i++) {
            bet+=i;
            a = a-i;
            if ((a+(k*i))<ap) {
                return false;
            }
        }

        if (win == false) {
            return ((a*k) >= ap);
        }

        return win;
    }
}
