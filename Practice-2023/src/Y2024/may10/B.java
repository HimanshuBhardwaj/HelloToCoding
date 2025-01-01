package Y2024.may10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 5/10/2024
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String str = br.readLine();
            pw.append(rearrange(str)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static String rearrange(String str) {
        int numChars = distinctChars(str);

        if (numChars==1) {
            return "NO";
        }
        char [] strC = str.toCharArray();
        Arrays.sort(strC);
        String a = new String(strC);
        if (!a.equals(str)) {
            return "YES\n"+a;
        }

        Character[] strCO = new Character[strC.length];
        for (int i=0;i<strCO.length;i++) {
            strCO[i] = strC[i];
        }

        Arrays.sort(strCO, Collections.reverseOrder());

        for (int i=0;i<strCO.length;i++) {
            strC[i] = strCO[i];
        }

        a = new String(strC);
        if (!a.equals(str)) {
            return "YES\n"+a;
        }

        return "NO";
    }

    private static int distinctChars(String str) {
        return 0;
    }
}
