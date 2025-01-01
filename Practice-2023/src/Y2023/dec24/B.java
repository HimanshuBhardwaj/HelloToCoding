package Y2023.dec24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/24/2023
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
           /* br.readLine();
            pw.append(numberDS(br.readLine())+"\n");

            */
            poc(br.readLine());
        }
        pw.flush();
        pw.close();
    }

    private static long numberDS(String s) {
        if (s.length() ==1) {
            return 1;
        }
        return -1;
    }

    static void poc(String s) {
        System.out.println(s);
        if (s.length()==1) {
            return;
        }
        String subStr = s.substring(2,s.length());
        System.out.println(s.charAt(0)+subStr);
        if (s.charAt(0) != s.charAt(1)) {
            System.out.println(s.charAt(1) + subStr);
            poc(s.charAt(1)+subStr);
        }
        poc(s.charAt(0)+subStr);

    }
}
