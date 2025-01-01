package Y2023.nov3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 11/3/2023
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int x = Integer.parseInt(str[0]);
            int y = Integer.parseInt(str[1]);
            int k = Integer.parseInt(str[2]);
            pw.append(getMintime(x,y,k)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static int getMintime(int x, int y, int k) {
        if (x==y) {
            return x;
        }

        if (x > y) {
            return x;
        }

        if ((x+k) >= y) {
            return y;
        }

        return y+y-(x+k);
    }
}
