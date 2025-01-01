package dec28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            long n = Long.parseLong(str[0]);
            long k = Long.parseLong(str[1]);
            HashMap<String, Long> map = new HashMap<>();

            pw.append(maximumValue(1,n,k,map)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static long maximumValue(long s, long e, long k, HashMap<String, Long> map) {
        if (k==1) {
            if (e%2==0) {
                return (e+1)*(e/2);
            } else {
                return (e*(e+1))/2;
            }
        }

        if (e-s+1 < k || s<0 || s>e) {
            return 0;
        }

        String key = s+"|"+e;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        long result=0;

        long mid = (s+e)/2;

        if ((e-s+1)%2==0) {
            result = maximumValue(s,mid,k,map)+maximumValue(mid+1,e,k,map);
        } else {
            result = mid+maximumValue(s,mid-1,k, map)+maximumValue(mid+1,e,k,map);
        }
        map.put(key,result);

        return result;
    }
}