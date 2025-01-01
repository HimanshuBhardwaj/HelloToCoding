package Y2023.december3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/3/2023
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            pw.append(isPossibleToMake(br.readLine())).append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static String isPossibleToMake(String s) {
        if (containsDiffChar(s)) {
            return "YES";
        }

        int cz=0;
        int c1=0;

        for (char c: s.toCharArray()) {
            if (c=='1') {
                c1++;
            } else {
                cz++;
            }
        }
        return (cz>c1)?"YES":"NO";
    }

    private static boolean containsDiffChar(String s) {
        for (int i=0;i<s.length()-1;i++) {
            if (s.charAt(i) != s.charAt(i+1)) {
                return true;
            }
        }
        return false;
    }
}
