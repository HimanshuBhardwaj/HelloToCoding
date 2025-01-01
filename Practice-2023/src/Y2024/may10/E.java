package Y2024.may10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Himanshu Bhardwaj
 * @Date 5/10/2024
 */
public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            long n= Integer.parseInt(str[0]);
            long k= Integer.parseInt(str[1]);
            long q= Integer.parseInt(str[2]);

            long ai[] = new long[(int)k];
            str = br.readLine().split(" ");

            for (int i=0;i<str.length;i++) {
                ai[i] = Long.parseLong(str[i]);
            }

            long bi[] = new long[(int)k];
            str = br.readLine().split(" ");

            for (int i=0;i<str.length;i++) {
                bi[i] = Long.parseLong(str[i]);
            }

            long queries [] = new long[(int)q];

            for (int i=0;i<q;i++) {
                queries[i] = Long.parseLong(br.readLine());
            }

            pw.append(findTimes(n,k,q,ai,bi,queries));
        }
        pw.flush();
        pw.close();
    }

    private static String findTimes(long n, long k, long q, long[] ai, long[] bi, long[] queries) {
        StringBuilder sb = new StringBuilder();

        TreeMap<Long, Long> treeMap = new TreeMap<>();
        for (int i=0;i<ai.length;i++) {
            treeMap.put(ai[i], bi[i]);
        }

        for (int i=0;i<queries.length;i++) {
            sb.append(findTime(n,k,q,ai,bi,queries[i],treeMap)).append(" ");
        }

        sb.append("\n");
        return sb.toString();
    }

    private static long findTime(long n, long k, long q, long[] ai, long[] bi, long query, TreeMap<Long, Long> treeMap) {
        if (treeMap.containsKey(query)) {
            return treeMap.get(query);
        }
        Map.Entry<Long, Long> fe = treeMap.floorEntry(query);
        Map.Entry<Long, Long> ce = treeMap.ceilingEntry(query);
        return timeTaken(fe,ce,query);
    }

    private static long timeTaken(Map.Entry<Long, Long> fe, Map.Entry<Long, Long> ce, long query) {
        if (fe==null) {
            return (ce.getValue()*query)/ce.getKey();
        }

        return fe.getValue()+ ((ce.getValue()-fe.getValue())*(query-fe.getKey()))/(ce.getKey()-fe.getKey());
    }
}
