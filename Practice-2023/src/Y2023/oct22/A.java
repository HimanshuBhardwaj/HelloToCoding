package Y2023.oct22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


/**
 * @author Himanshu Bhardwaj
 * @Date 10/22/2023
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int t = Integer.parseInt( br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

       while (t-- > 0) {
           pw.append(getMinCount(br.readLine().toCharArray())+"\n");
       }
       pw.flush();
       pw.close();
    }

    private static int getMinCount(char [] s) {
        char cp = '1';
        int count=0;

        for (char cs: s) {
            if (cs == '0' ) {
                if (cp=='0') {
                    count++;
                }
                else {
                    count += 2+ (int)('9' - cp);
                    cp = cs;
                }
            } else {
                if (cp=='0') {
                    count += 2+ (int)('9' - cs);
                    cp = cs;
                }
                else {
                    count += Math.abs(cp-cs)+1;
                            cp = cs;
                }
            }
        }

        return count;
    }
}
