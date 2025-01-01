package Y2024.feb6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


/**
 * @author Himanshu Bhardwaj
 * @Date 2/6/2024
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String trS[] = br.readLine().split(" ");
            int tr[] = new int[trS.length];
            for (int i=0;i<trS.length;i++) {
                tr[i] = Integer.parseInt(trS[i]);
            }
            pw.append(getString(tr)).append("\n");
        }
        pw.flush();
        pw.close();

    }

    private static String getString(int[] tr) {
        int [] cr = new int[26];

        for (int i=0;i<cr.length;i++) {
            cr[i] = -1;
        }

        StringBuffer sb = new StringBuffer();

        for (int x:tr) {
            sb.append(getFreqChar(cr,x-1));
        }
        return sb.toString();
    }

    private static char getFreqChar(int[] cr, int f) {
        for (int i=0;i<cr.length;i++) {
            if (cr[i]==f) {
                cr[i]++;
                return (char)('a'+i);
            }
        }
        return 'a';
    }
}
