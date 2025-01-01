package Y2023.oct28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Himanshu Bhardwaj
 * @Date 10/28/2023
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String [] str = br.readLine().split(" ");
            int [] a = new int[str.length];

            for(int i=0;i<str.length;i++) {
                a[i] = Integer.parseInt(str[i]);
            }

            pw.append(isPossible(a)?"Yes":"No").append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static boolean isPossible(int[] a) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int x: a) {
            if (!map.containsKey(x)) {
                map.put(x,0);
            }
            map.put(x,map.get(x)+1);
        }
        if (map.size()==1) {
            return true;
        }

        if (map.size() > 2) {
            return false;
        }

        int less = map.firstKey();
        int more = map.lastKey();
        if (Math.abs(map.get(less) - map.get(more)) <= 1) {
            return true;
        }
        return false;
    }
}
