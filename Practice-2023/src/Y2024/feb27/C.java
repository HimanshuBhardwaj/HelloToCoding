package Y2024.feb27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/27/2024
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            long a = Integer.parseInt(str[0]);
            long b = Integer.parseInt(str[1]);
            long l = Integer.parseInt(str[2]);

            pw.append(countNum(a,b,l)+"\n");
        }

        pw.flush();
        pw.close();
    }

    private static long countNum(long a, long b, long l) {
        TreeSet<Long> treeSet = new TreeSet<>();
        long ppa[] = new long[51];
        long ppb[] = new long[51];

        ppb[0]=1;
        ppa[0]=1;

        for (int i=1;i<ppb.length;i++) {
            ppb[i] = ppb[i-1]*b;
            ppa[i] = ppa[i-1]*a;

            if (ppa[i] > l || ppa[i] <= 0) {
                ppa[i] = 0;
            }

            if (ppb[i] > l || ppb[i] <= 0) {
                ppb[i] = 0;
            }
        }


        for (int i=0;i<=50;i++) {
            for (int j=0;j<=50;j++) {
                long pa = ppa[i];
                long pb = ppb[j];

                if (pb <= 0 || pa <= 0 ||pa*pb<=0|| pa*pb > l) {
                    break;
                }

                if ((l % (pa*pb)) ==0) {
//                    System.out.println(l+"\t"+a+"\t"+i+"\t"+b+"\t"+j+"\t\t"+(l/(pa*pb)));
                    treeSet.add(l/(pa*pb));
                }
            }
        }


        return treeSet.size();
    }
}
