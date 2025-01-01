package Y2024.jan9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 1/6/2024
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String str = br.readLine();
            int count=0;
            for (char c:str.toCharArray()) {
                if (c=='+') {
                    count++;
                } else {
                    count--;
                }
            }
            System.out.println(Math.abs(count));

        }
    }
}
