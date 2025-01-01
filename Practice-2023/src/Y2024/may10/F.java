package Y2024.may10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 5/10/2024
 */
public class F {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            long r = Long.parseLong(br.readLine());
            pw.append(numPoints(r)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static long numPoints(long r) {
        long count=0;
        for (long x=1;x<=r;x++) {
            long lowB = (long)Math.sqrt((r*r) - (x*x));
            long upperB = (long)Math.sqrt(((r+1)*(r+1)) - (x*x));

            if (((lowB*lowB) + (x*x)) < r*r ) {
                lowB++;
            }

            if (((upperB*upperB) + (x*x))>= (r+1)*(r+1)) {
                upperB--;
            }



            count+= (upperB-lowB+1);
        }
        return count*4;
    }
}
