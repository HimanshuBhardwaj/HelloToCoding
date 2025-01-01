package Y2024.feb11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/11/2024
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            long a = Long.parseLong(str[0]);
            long b = Long.parseLong(str[1]);

            pw.append((isPossible(a,b)|| isPossible(b,a))?"Yes":"No" ).append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static boolean isPossible(long a, long b) {
        Rectangle rectangle = new Rectangle(a,b);
        ArrayList<Integer> factors = factors((int)a);

        for (int f:factors) {
            long ta = a/f;
            long tb = b*f;
            Rectangle rectangle1 = new Rectangle(ta,tb);
            if (rectangle1.compareTo(rectangle)==1) {
                return true;
            }
        }

        return false;
    }

    static ArrayList<Integer> factors(int x) {
        ArrayList<Integer> factors = new ArrayList<>();
        if (x%2==0) {
            factors.add(2);
        }
        return factors;
    }


}

class Rectangle implements Comparable<Rectangle> {
    long r;
    long c;

    public Rectangle(long r, long c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public int compareTo(Rectangle o) {
        if ((this.r == o.c) && (this.c == o.r)) {
            return 0;
        }
        return (((Long.compare(this.r, o.r)==0) && (Long.compare(this.c, o.c) ==0))
        || ((Long.compare(this.r, o.c)==0) && (Long.compare(this.c, o.r) == 0)))?0:1;
    }
}