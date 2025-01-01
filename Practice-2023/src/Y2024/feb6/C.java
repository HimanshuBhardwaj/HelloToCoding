package Y2024.feb6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/6/2024
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            int k = Integer.parseInt(str[2]);
            str = br.readLine().split(" ");
            int [] a = new int[str.length];

            for (int i=0;i<str.length;i++) {
                a[i] = Integer.parseInt(str[i]);
            }

            str = br.readLine().split(" ");
            int [] b = new int[str.length];

            for (int i=0;i<str.length;i++) {
                b[i] = Integer.parseInt(str[i]);
            }

            pw.append(isPossible(a,b,k)?"YES":"NO").append("\n");
        }
        pw.close();
        pw.flush();
    }

    private static boolean isPossible(int[] a, int[] b, int k) {
        if (!containsAllNumbers(a,b,k)) {
            return false;
        }
        TreeSet<Integer> ua = uniqueA(a,b,k);
        TreeSet<Integer> ub = uniqueA(b,a,k);
        int ar = (k/2) - ua.size();
        int br = (k/2) - ub.size();

        if (ar<0 || br<0) {
            return false;
        }

        TreeSet<Integer> an = new TreeSet<>();

        for (int x: a) {
            if (!ua.contains(x)) {
                an.add(x);
            }
        }

        TreeSet<Integer> bn = new TreeSet<>();

        for (int x: b) {
            if (!ub.contains(x)) {
                bn.add(x);
            }
        }


        for (int i=1;i<=k;i++) {
            if (ua.contains(i) || ub.contains(i)) {
                continue;
            }

            if (an.contains(i) && (ar>0)) {
                ar--;
                continue;
            }

            if (bn.contains(i) && (br>0)) {
                br--;
                continue;
            }
            return false;
        }


        return true;
    }

    private static TreeSet<Integer> uniqueA(int[] a, int[] b, int k) {
        TreeSet<Integer> bs = new TreeSet<>();

        for (int x:b) {
            bs.add(x);
        }
        TreeSet<Integer> ua = new TreeSet<>();
        for (int x:a) {
            if (!bs.contains(x) && (x>=1 && x<=k)) {
                ua.add(x);
            }
        }
        return ua;
    }

    private static boolean containsAllNumbers(int[] a, int[] b, int k) {
        boolean bb[] = new boolean[k+1];

        for (int x: a) {
            if (x<=k) {
                bb[x] = true;
            }
        }

        for (int x: b) {
            if (x<=k) {
                bb[x] = true;
            }
        }

        for (int i=1;i<bb.length;i++) {
            if (!bb[i]) {
                return false;
            }
        }

        return true;
    }
}
