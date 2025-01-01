package Y2023.nov7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 11/7/2023
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String str = br.readLine();

            if (str.length()==0) {
                pw.append("?").append("\n");
                continue;
            }

            pw.append(str.charAt(str.length()-1)).append("\n");
        }

        pw.flush();
        pw.close();
    }
}
