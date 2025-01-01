package Y2023.dec16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/16/2023
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String str = br.readLine();
            str = br.readLine();
            pw.append(isPossible(str)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static int isPossible(String str) {
        char [] sc = str.toCharArray();
        Arrays.sort(sc);

        boolean flag = true;

        for (int i=0;i<sc.length;i++) {
            if (sc[i] != str.charAt(i)) {
                flag = false;
                break;
            }
        }

        if (flag) {
            return 0;
        }

        int pos=0;
        char [] lr = laxographicallyLar(str);

        StringBuffer sb = new StringBuffer();

        for (int i=0;i<str.length();i++) {
            if ((pos<lr.length) && (str.charAt(i)==lr[pos])) {
                pos++;
            } else {
                if (str.charAt(i) != sc[i]) {
                    return -1;
                }
            }
        }

        for (int i=1;i<lr.length;i++) {
            if (lr[i]>lr[i-1]) {
                return -1;
            }
        }

        int c=0;
        for (int i=1;i<lr.length;i++) {
            if (lr[i]!=lr[i-1]) {
                if (lr.length == str.length()) {
                    return lr.length-i;
                } else {
                    return lr.length-i;
                }
            }
        }

        return 0;

    }

    static char[] laxographicallyLar(String str) {
        if (str.length()==1) {
            return str.toCharArray();
        }
        if (str.length() == 2) {
            if (str.charAt(0) >= str.charAt(1)) {
                //System.out.println(str);
                return str.toCharArray();
            } else {
                //System.out.println(str.charAt(1));
                (str.charAt(1)+"").toCharArray();
            }
        }

        char [] strc = str.toCharArray();
        int[] lrPos = new int[strc.length];
        lrPos[lrPos.length-1] = -1;
        lrPos[lrPos.length-2] = lrPos.length-1;

        for (int i= lrPos.length-3;i>=0;i--) {
            if (strc[i+1] >= strc[lrPos[i+1]]) {
                lrPos[i] = i+1;
            } else {
                lrPos[i] = lrPos[i+1];
            }
        }

        StringBuilder sb = new StringBuilder();

        int pos=0;

        if (strc[0] >= strc[lrPos[0]]) {
            sb.append(strc[0]);
        }


        while (lrPos[pos] != -1) {
            sb.append(strc[lrPos[pos]]);


            if (lrPos[pos] == -1) {
                sb.append(strc[pos]);
            }

            pos = lrPos[pos];

        }

        //System.out.println(sb.toString());
        return sb.toString().toCharArray();
    }
}
