package Y2023.oct28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * @author Himanshu Bhardwaj
 * @Date 10/28/2023
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String str = br.readLine();
            pw.append(getSeq(str)).append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static String getSeq(String str) {

        int start = 0;
        int end = str.length()-1;
        int count=0;
        LinkedList<Integer> listOp = new LinkedList<>();

        while (start<=end) {
            if (start==end) {
                return "-1";
            }

            if (str.charAt(start) != str.charAt(end)) {
                start++;
                end--;
                continue;
            } else {
                if (end == start+1) {
                    return "-1";
                }
                if (str.charAt(start)=='0') {
                    if (count>=300) {
                        return "-1";
                    }

                    listOp.addLast(end+1);
                    str = append01(str, end, start, end);
                    count++;
                    end = end+2;
                } else {
                    if (count>=300) {
                        return "-1";
                    }
                    str = append01(str, start, start, end);
                    count++;
                    end = end+2;
                    listOp.addLast(start);
                }
            }
        }

        if (listOp.size()==0) {
            return "0\n";
        }

        StringBuffer sb = new StringBuffer();
        sb.append(listOp.size()).append("\n");
        for (int x: listOp) {
            sb.append(x+" ");
        }

        return sb.toString();
    }

    private static String append01(String str, int index, int start, int end) {
        if (index==end) {
            return str.substring(0,end+1)+"01"+str.substring(end+1, str.length());
        }

        return str.substring(0,start)+"01"+str.substring(start,str.length());
    }
}
