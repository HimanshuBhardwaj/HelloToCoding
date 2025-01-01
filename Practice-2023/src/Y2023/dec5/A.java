package Y2023.dec5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/5/2023
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String s = br.readLine();
            printPositions(s.charAt(0), Integer.parseInt(""+s.charAt(1)));
        }
    }

    private static void printPositions(char c, int p) {
        for (int i=1;i<9;i++) {
            if (i!=p) {
                System.out.println(c + "" + i);
            }
        }

        for (int i=0;i<8;i++) {
            if ((int)(c-'a') != i) {
                System.out.println(((char) ('a' + i)) + "" + p);
            }
        }
    }
}
