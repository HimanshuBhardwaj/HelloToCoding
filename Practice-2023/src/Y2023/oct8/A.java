package Y2023.oct8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 10/8/2023
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter printWriter = new PrintWriter(System.out);
        int t= Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String [] str = br.readLine().split(" ");
            long sum = 0;
            for (int i=0;i<str.length;i++) {
                sum += Long.parseLong(str[i]);
            }
            printWriter.append((-1*sum)+"\n");
        }

        printWriter.flush();
        printWriter.close();
    }
}
