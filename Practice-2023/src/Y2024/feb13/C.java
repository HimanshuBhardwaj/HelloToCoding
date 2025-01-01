package Y2024.feb13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/13/2024
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String [] str = br.readLine().split(" ");
            int [] a = new int[str.length];

            for (int i=0;i<a.length;i++) {
                a[i] = Integer.parseInt(str[i]);
            }

            pw.append(minimumConversion(a)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static int minimumConversion(int[] a) {
        if (a[0]==a[a.length-1]) {
            int pe=1;

            for (int i=1;i<a.length;i++) {
                if (a[i]==a[0]) {
                    pe++;
                } else {
                    break;
                }
            }

            int se=1;

            for (int i=a.length-2; i>=0; i--) {
                if (a[i]==a[a.length-1]) {
                    se++;
                } else {
                    break;
                }
            }

            return Math.max(a.length-(pe+se),0);
        } else {
            int pe=1;

            for (int i=1;i<a.length;i++) {
                if (a[i]==a[0]) {
                    pe++;
                } else {
                    break;
                }
            }

            int se=1;

            for (int i=a.length-2; i>=0; i--) {
                if (a[i]==a[a.length-1]) {
                    se++;
                } else {
                    break;
                }
            }

            int max = Math.max(se, pe);

            return Math.max(a.length-max,0);
        }
    }
}
