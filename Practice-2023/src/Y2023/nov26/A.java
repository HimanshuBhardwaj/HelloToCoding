package Y2023.nov26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 11/26/2023
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String str = br.readLine();
            pw.append(minNumber(str)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static int minNumber(String str) {
        int mc = maxContinousdots(str);
        int dots = totalDots(str);

        if (mc<=2) {
            return dots;
        }

        return 2;
    }

    static int maxContinousdots(String str) {
        int mc = 0;

        boolean flag = false;
        int lc=0;
        for (int i=0;i<str.length();i++) {
            if (str.charAt(i)=='.') {
                if (flag) {
                    lc++;
                } else {
                    flag = true;
                    lc=1;
                }
                mc = Math.max(mc, lc);
            } else {
                lc = 0;
                flag=false;
            }
        }

        return mc;
    }

    static int totalDots(String str) {
        int c=0;

        for (int i=0;i< str.length();i++) {
            if (str.charAt(i)=='.') {
                c++;
            }
        }

        return c;
    }
}
