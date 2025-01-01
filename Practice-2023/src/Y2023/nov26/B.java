package Y2023.nov26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 11/26/2023
 */
public class B {
    static HashSet<Integer> treeSet = new HashSet<>();
    static HashSet<Integer> nTreeSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int s1 = Integer.parseInt(str[0]);
            int s2 = Integer.parseInt(str[1]);
            int s3 = Integer.parseInt(str[2]);
            pw.append(possibleString(s1,s2,s3)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static String possibleString(int s1, int s2, int s3) {
        StringBuilder sb = new StringBuilder();
        if (is1Possible(s1,s2,s3)) {
            sb.append("1 ");
        } else {
            sb.append("0 ");
        }

        if (is1Possible(s2,s1,s3) || is1Possible(s2,s3,s1)) {
            sb.append("1 ");
        } else {
            sb.append("0 ");
        }

        if (is1Possible(s3,s1,s2) || is1Possible(s3,s2,s1)) {
            sb.append("1");
        } else {
            sb.append("0");
        }

        return sb.toString();
    }

    private static boolean is1Possible(int s1, int s2, int s3) {
        if (s1<0 || s2<0 || s3<0) {
            return false;
        }

        Integer s = (100000000*s1)+(10000*s2)+s3;

        if (treeSet.contains(s)) {
            return true;
        }

        if (nTreeSet.contains(s)) {
            return false;
        }

        if (s2==s3) {
            return true;
        }

        boolean result =  is1Possible(s1+1,s2-1,s3-1) || is1Possible(s1-1,s2-1,s3+1) ||is1Possible(s1-1,s2+1,s3-1);

        if (result) {
            treeSet.add(s);
        }
        else {
            nTreeSet.add(s);
        }

        return result;
    }
}
