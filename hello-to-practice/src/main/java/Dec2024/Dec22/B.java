package Dec2024.Dec22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            long n = Long.parseLong(str[0]);
            long a = Long.parseLong(str[1]);
            long b = Long.parseLong(str[2]);
            long c = Long.parseLong(str[3]);

            long num = (n/(a+b+c));
            long diff = n - num*(a+b+c);
            num = num*3;
            if (diff==0) {
                pw.append(num+"\n");
            } else if (diff-a <=0) {
                pw.append((num+1)+"\n");
            } else if (diff-(a+b) <=0) {
                pw.append((num+2)+"\n");
            } else {
                pw.append((num+3)+"\n");
            }

        }
        pw.flush();
        pw.close();

    }
}
