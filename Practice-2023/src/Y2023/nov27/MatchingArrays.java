package Y2023.nov27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Himanshu Bhardwaj
 * @Date 11/27/2023
 */
public class MatchingArrays {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int x = Integer.parseInt(str[1]);

            str = br.readLine().split(" ");

            int a[] = new int[n];

            for (int i=0;i<n;i++) {
                a[i] = Integer.parseInt(str[i]);
            }

            str = br.readLine().split(" ");

            int b[] = new int[n];

            for (int i=0;i<n;i++) {
                b[i] = Integer.parseInt(str[i]);
            }

            pw.append(isArrangementPossible(n,x,a,b)).append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static String isArrangementPossible(int n, int x, int[] a, int[] b) {
        ArrayList<Element> aE = new ArrayList<>();

        for (int i=0;i<a.length;i++) {
            aE.add(new Element(a[i],i));
        }

        Collections.sort(aE);

        ArrayList<Element> bE = new ArrayList<>();

        for (int i=0;i<a.length;i++) {
            bE.add(new Element(b[i],i));
        }

        Collections.sort(bE);

        ArrayList<Element> beB = new ArrayList<>();

        for (int i=x;i<n;i++) {
            beB.add(bE.get(i));
        }

        for (int i=0;i<x;i++) {
            beB.add(bE.get(i));
        }

        int cx = 0;

        for (int i=0;i<n;i++) {
            if (aE.get(i).val > beB.get(i).val) {
                cx++;
            }
        }

        if (cx != x) {
            return "NO";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("YES").append("\n");

        for (int i=0;i<aE.size();i++) {
            aE.get(i).tempE = beB.get(i).val;
        }

        Comparator<Element> comparator = new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
               return  Integer.compare(o1.index, o2.index);
            }
        };

        Collections.sort(aE,comparator);

        for (Element e: aE) {
            sb.append(e.tempE).append(" ");
        }

        return sb.toString();
    }

    static class Element implements Comparable<Element> {
        int val;
        int index;

        int tempE;

        public Element(int v, int index) {
            this.val = v;
            this.index = index;
        }

        @Override
        public int compareTo(Element o) {
            if (Integer.compare(this.val,o.val)==0) {
                return Integer.compare(this.index,o.index);
            }

            return Integer.compare(this.val,o.val);
        }
    }
}
