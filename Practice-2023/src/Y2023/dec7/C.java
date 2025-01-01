package Y2023.dec7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/7/2023
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String s = br.readLine();
            pw.append(minStringSize(s)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static int minStringSize(String s) {
        int maxfreq = maxFreq(s);
        if (maxfreq >= (s.length()+1)/2) {
            return maxfreq - (s.length() - maxfreq);
        }
        return s.length()%2;
    }

    private static int maxFreq(String s) {
        int [] feq = new int[26];
        int max=0;

        for (char c: s.toCharArray()) {
            try {
                feq[(int)(c-'a')]++;
            }
            catch (Exception e) {

            }

            max = Math.max(feq[c-'a'], max);
        }
        return max;
    }
}
