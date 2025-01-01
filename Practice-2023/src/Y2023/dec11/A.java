package Y2023.dec11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/11/2023
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String str = br.readLine();
            pw.append(genUserName(str)).append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static String genUserName(String str) {
        for (int i=str.length()-1;i>=0;i--) {
            if (str.charAt(i) != '0') {
                return str.substring(0,i);
            }
        }
        return "";
    }
}
