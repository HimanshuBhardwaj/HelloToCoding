package dec24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;

public class B {
    public static void main(String[] args) throws IOException {
        num(11);
        num(111);
        num(1111);
        num(11111);
        num(111111);
        num(1111111);
        num(11111111);
        num(111111111);
        num(1111111111l);
        num(11111111111l);
        num(111111111111l);
        num(1111111111111l);
        num(11111111111111l);
        num(111111111111111l);
        num(1111111111111111l);
        num(11111111111111111l);
        num(111111111111111111l);
        num(1111111111111111111l);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {

        }
        pw.flush();
        pw.close();

    }

    static void num(long num) {
        TreeSet<Integer> set = new TreeSet<>();

        for (int i=1;i<=9;i=i+2) {
            if (num%i==0) {
                set.add(i);
            }
        }

        System.out.println(set+"\t"+num);
    }
}
