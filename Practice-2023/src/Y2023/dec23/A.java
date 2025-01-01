package Y2023.dec23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/23/2023
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int [][] points = new int[n][2];

            for (int i=0;i<n;i++) {
                String [] str = br.readLine().split(" ");
                int x = Integer.parseInt(str[0]);
                int y = Integer.parseInt(str[1]);
                points[i][0] = x;
                points[i][1] = y;
            }

            pw.append(isPossible(points, n)?"YES":"NO").append("\n");

        }
        pw.flush();
        pw.close();
    }

    private static boolean isPossible(int[][] points, int n) {
        TreeSet<String> op = new TreeSet<>();

        for (int i=0;i<n;i++) {
            if (points[i][0] > 0) {
                op.add("R");
            }
            if (points[i][0] < 0) {
                op.add("L");
            }

            if (points[i][1] > 0) {
                op.add("U");
            }
            if (points[i][1] < 0) {
                op.add("D");
            }
        }
        return op.size() < 4;
    }
}
