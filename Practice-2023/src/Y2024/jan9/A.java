package Y2024.jan9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 1/6/2024
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String[] str = br.readLine().split(" ");
            long a = Long.parseLong(str[0]);
            long b = Long.parseLong(str[1]);

            int nn = (int) ((a + b) % 2);

            System.out.println((nn == 1) ? "Alice" : "Bob");
        }
    }

}
