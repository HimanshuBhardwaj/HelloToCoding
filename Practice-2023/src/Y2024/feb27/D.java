package Y2024.feb27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/27/2024
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            long [] arr = new long[n];
            String [] str = br.readLine().split(" ");

            for (int i=0;i<n;i++) {
                arr[i] = Integer.parseInt(str[i]);
            }

            pw.append(isPossible(arr)).append("\n");
        }

        pw.flush();
        pw.close();
    }

    private static String isPossible(long[] arr) {
        TreeMap<Long, Integer> freQMap = new TreeMap<>();
        Arrays.sort(arr);

        for (long x:arr) {
            if (!freQMap.containsKey(x)) {
                freQMap.put(x,0);
            }
            freQMap.put(x,freQMap.get(x)+1);
        }

        if (areDistinct(freQMap)) {
            return "YES";
        }

        long fofe = firstOneFreE(freQMap);

        if (fofe == -1) {
            if (spfse(freQMap, arr)) {
                return "YES";
            }
            return "NO";
        }

        long tempOFE = fofe;
        for(long x: arr) {
            if (x > tempOFE) {
                break;
            }

            if (x!=fofe) {
                tempOFE = tempOFE % x;
            }
        }

        return (tempOFE==0)?"NO":"YES";
    }

    private static boolean spfse(TreeMap<Long, Integer> freQMap, long [] arr) {
        if (freQMap.size()==1) {
            return false;
        }
        long se = -1;

        for (Map.Entry<Long, Integer> entry : freQMap.entrySet()) {
            if ((entry.getKey()%freQMap.firstKey()) != 0) {
                se = entry.getKey();
            }
        }

        if (se==-1) {
            return false;
        }

        long pse = se;
        int flag=0;

        for (long x:arr) {
            if ((x==pse)) {
                if (flag==0) {
                    flag++;
                    continue;
                } else {
                    se = se%x;
                }
            } else {
                se = se%x;
            }
        }

        return se!=0;
    }

    private static long firstOneFreE(TreeMap<Long, Integer> freQMap) {
        for (Map.Entry<Long, Integer> entry : freQMap.entrySet()) {
            if (entry.getValue()==1) {
                return entry.getKey();
            }
        }
        return -1;
    }

    private static boolean areDistinct(TreeMap<Long, Integer> freQMap) {
        for (Map.Entry<Long, Integer> entry : freQMap.entrySet()) {
            if (entry.getValue() > 1) {
                return false;
            }
        }
        return true;
    }
}