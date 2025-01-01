package dec24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;

public class C {
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

            pw.append(printPossibleSums(a)+"\n");

        }
        pw.flush();
        pw.close();

    }

    private static String printPossibleSums(int[] a) {
        int cnS = nonOnePos(a);
        if (cnS == -1) {
            int max = getMaxValue(a,0,a.length-1);
            int min = getMinValue(a,0,a.length-1);
            TreeSet<Long> set = new TreeSet<>();

            for (int i=min;i<=max;i++) {
                set.add((long)i);
            }

            return prepareString(set);
        } else {

            int max = getMaxValue(a,0,cnS-1);
            int min = getMinValue(a,0,cnS-1);
            TreeSet<Long> set = new TreeSet<>();

            for (int i=min;i<=max;i++) {
                set.add((long)i);
            }

             max = getMaxValue(a,cnS+1,a.length-1);
            min = getMinValue(a,cnS+1, a.length-1);
            for (int i=min;i<=max;i++) {
                set.add((long)i);
            }

            int maxR =0;
            int minR = 0;
            int sumR=0;

            for (int i=cnS+1;i<a.length;i++) {
                sumR+=a[i];
                maxR = Math.max(sumR, maxR);
                minR = Math.min(sumR, minR);
            }



            int maxL =0;
            int minL = 0;
            int sumL=0;

            for (int i=cnS-1;i>=0;i--) {
                sumL+=a[i];
                maxL = Math.max(sumL, maxL);
                minL = Math.min(sumL, minL);
            }

            int gMax = maxL+maxR+a[cnS];
            int gMin = minL+minR+a[cnS];

            for (int i=gMin;i<=gMax;i++) {
                set.add((long)i);
            }


            return prepareString(set);

        }
    }

    private static int nonOnePos(int[] a) {
        for (int i=0;i<a.length;i++) {
            if (a[i]!=1 && a[i]!=-1) {
                return i;
            }
        }
        return -1;
    }

    private static String prepareString(TreeSet<Long> set) {
        set.add(0l);
        StringBuilder sb = new StringBuilder();

        sb.append(set.size()+"\n");

        for (long x: set) {
            sb.append(x+" ");
        }

        return sb.toString();
    }

    private static int getMinValue(int[] a, int s, int e) {
        if (s<0 || e>=a.length || s>e) {
            return 0;
        }
        int gMin = a[s];
        int min = a[s];

        for (int i=s+1;i<=e;i++) {
            if (min+a[i] < 0) {
                min = min+a[i];
                gMin = Math.min(gMin, min);
            } else {
                min = a[i];
                gMin = Math.min(gMin, min);
            }
        }

        return gMin;
    }

    private static int getMaxValue(int[] a, int s, int e) {
        if (s<0 || e>=a.length || s>e) {
            return 0;
        }

        int gMin = a[s];
        int min = a[s];

        for (int i=s+1;i<=e;i++) {
            if (min+a[i] > 0) {
                min = min+a[i];
                gMin = Math.max(gMin, min);
            } else {
                min = a[i];
                gMin = Math.max(gMin, min);
            }
        }

        return gMin;
    }
}