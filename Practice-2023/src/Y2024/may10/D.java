package Y2024.may10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Himanshu Bhardwaj
 * @Date 5/10/2024
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String str = br.readLine();
            pw.append(numCuts(str)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static int numCuts(String str) {
        if (isSorted(str)) {
            return 1;
        }

        int fz = firstZero(str);

        int pos = conSaFz(fz,str);

        int numSeg = numSeg(pos,str);

        return numSeg+(fz==0 ? 0:1)+1;
    }

    private static int conSaFz(int fz, String str) {
        boolean flag = false;
        for (int i=fz;i<str.length();i++) {
            if (str.charAt(i)=='0') {
                if (flag) {
                    return i;
                }
                continue;
            }
            else {
                flag=true;
            }

        }
        return str.length();
    }

    private static int firstZero(String str) {
        for (int i=0;i<str.length();i++) {
            if (str.charAt(i) == '0') {
                return i;
            }
        }

        return -1;
    }

    private static int numSeg(int ii, String str) {
        if (ii>=str.length()) {
            return 0;
        }

        int c=1;
        char cs=str.charAt(ii);

        for (int i=ii;i<str.length();i++) {
            if (str.charAt(i)==cs) {
                continue;
            } else {
                c++;
                cs=str.charAt(i);
            }
        }

        return c;
    }

    private static boolean isSorted(String str) {
        char[] c = str.toCharArray();
        Arrays.sort(c);
        return str.equals(new String(c));
    }
}
