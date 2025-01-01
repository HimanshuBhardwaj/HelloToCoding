package Y2023.dec19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/19/2023
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String str = br.readLine();
            pw.append(numProb(str)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static int numProb(String str) {
        int [] freq = new int[26];

        for (char c: str.toCharArray()) {
            freq[(int)(c-'A')]++;
        }

        int c=0;
        for (int i=0;i<freq.length;i++) {
            if (freq[i] >= (i+1)) {
                c++;
            }
        }

        return c;
    }
}
