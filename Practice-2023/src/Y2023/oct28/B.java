package Y2023.oct28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 10/28/2023
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            char [] sa = br.readLine().toCharArray();
            char [] ta = br.readLine().toCharArray();

            pw.append(isPossible(sa,ta)?"Yes":"No").append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static boolean isPossible(char[] s, char[] t) {

        int sI = 0;
        int ti = -1;
        char prvc = '2';
        boolean flag = false;

        for (; sI< s.length; sI++) {
            if (prvc != s[sI]) {
                prvc = s[sI];
                flag = false;
                continue;
            }

            if (flag) {
                return false;
            }

            flag = true;

            for (int sT=0;sT<t.length;sT++) {
                if (prvc != t[sT]) {
                    prvc = t[sT];
                } else {
                    return false;
                }
            }

            sI--;

        }
        return true;
    }
}
