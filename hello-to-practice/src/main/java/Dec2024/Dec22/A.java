package Dec2024.Dec22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class A {
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

            str = br.readLine().split(" ");
            int [] b = new int[str.length];

            for (int i=0;i<b.length;i++) {
                b[i] = Integer.parseInt(str[i]);
            }
            int sum=0;
            for (int i=0;i<a.length;i++) {
                if (i<= a.length-2) {
                    if (a[i] > b[i + 1]) {
                        sum+= (a[i]-b[i+1]);
                    }
                } else {
                    sum+=a[i];
                }
            }

            pw.append(sum+"\n");

        }
        pw.flush();
        pw.close();

    }
}