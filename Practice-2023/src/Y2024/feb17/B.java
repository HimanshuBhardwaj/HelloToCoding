package Y2024.feb17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/17/2024
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            pw.append(generateArray(n)).append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static String generateArray(int n) {
        int [] a = new int[n];
        generateArrayH(a);

        StringBuffer sb = new StringBuffer();

        for (int x: a) {
            sb.append(x).append(" ");
        }

        return sb.toString();
    }

    private static void generateArrayH(int[] a) {
        for (int i=0;i<a.length/2;i++) {
            if (i%2 == 0) {
                a[i] = i+1;
                a[a.length-1-i] = a.length-i;
            } else {
                a[a.length-1-i] = i+1;
                a[i] = a.length-i;
            }
        }

        if (a.length%2 == 1) {
            a[a.length/2]=(a.length+1)/2;
        }
    }

    private static boolean isValid(int[] a) {
        boolean nDividing = true;
        for (int i=0;i<a.length-1;i++) {
            for (int j=i+1;j<a.length-1;j++) {
                if ((a[i]%a[j] == 0) && (a[i+1]%a[j+1]==0)) {
                    nDividing = false;
                    System.out.println(i+"\t\t"+j);
                    break;
                }

                if ((a[j]%a[i] == 0) && (a[j+1]%a[i+1]==0)) {
                    System.out.println(i+"\t\t"+j);
                    nDividing = false;
                    break;
                }
            }
        }
        return nDividing;
    }
}
