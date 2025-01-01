package Y2024.jan27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 1/27/2024
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int x = Integer.parseInt(str[0]);
            int n = Integer.parseInt(str[1]);
            pw.append(maxGCD(x,n)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static int maxGCD(int x, int n) {
        ArrayList<Integer> fac = factors(x);
        int min  = getSubset(0,fac,new LinkedList<>(),n, x);
        return x/min;
    }

    private static int getSubset(int index, ArrayList<Integer> fac, LinkedList<Object> objects, int n, int sum) {
        if (sum < n) {
            return Integer.MAX_VALUE;
        }



        return 0;
    }

    static ArrayList<Integer> factors(int x) {
        TreeSet<Integer> sets = new TreeSet<>();

        int sx = (int)Math.sqrt(x)+1;

        for (int i=1;i<=sx;i++) {
            if ((x%i) == 0) {
                sets.add(i);
                sets.add(x/i);
            }
        }

        return new ArrayList<>(sets.stream().toList());
    }
}
