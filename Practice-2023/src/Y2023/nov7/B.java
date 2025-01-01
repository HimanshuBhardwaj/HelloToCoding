package Y2023.nov7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Himanshu Bhardwaj
 * @Date 11/7/2023
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String [] str = br.readLine().split(" ");
            pw.append(getSet(str)).append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static String getSet(String[] str) {
        TreeMap<Integer,TreeSet<Integer>> freqMap = new TreeMap<>();

        int [] a = new int[str.length];

        for (int i=0;i<a.length;i++) {
            a[i] = Integer.parseInt(str[i]);

            if (!freqMap.containsKey(a[i])) {
                freqMap.put(a[i], new TreeSet<>());
            }

            freqMap.get(a[i]).add(i);
        }

        int rulesUsed = 0;

        Set<Integer> keys = freqMap.keySet();
        TreeSet<Integer> mapping = new TreeSet();
        int [] resultArray = new int[str.length];

        if (keys.size()==1) {
            return "-1";
        }

        int iteration=0;
        int differF = 0;

        for (Integer key: keys){
            TreeSet<Integer> values = freqMap.get(key);
            boolean flag = false;
            boolean ruleNeedC = false;

            if (values.size() > 1) {
                differF++;
            }

            for (int v: values) {
                if (flag==false) {
                    flag=true;
                    resultArray[v] = 1;
                    continue;
                }

                if (rulesUsed == 0) {
                    resultArray[v] = 2;
                    ruleNeedC = true;
                }
                else {
                    resultArray[v] = 3;
                    ruleNeedC = true;
                }
            }

            if (ruleNeedC) {
                if (iteration==0) {
                    iteration++;
                    rulesUsed=1;
                }
            }
        }

        if ((rulesUsed == 0 && iteration > 0) || differF < 2) {
            return "-1";
        }
        else {
            StringBuilder sb = new StringBuilder();

            for (int i:resultArray) {
                sb.append(i).append(" ");
            }

            return sb.toString();
        }
    }
}
