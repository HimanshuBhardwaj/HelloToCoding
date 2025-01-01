package Y2023.dec28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/28/2023
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int [] a = new int[10];
            a[Integer.parseInt(str[0])]++;
            a[Integer.parseInt(str[1])]++;
            a[Integer.parseInt(str[2])]++;

            for (int i=0;i<10;i++) {
                if (a[i]==1) {
                    pw.append(i+"\n");
                    break;
                }
            }
        }
        pw.flush();
        pw.close();
    }
}
