package Y2023.oct9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 10/9/2023
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            pw.append(getfactor(Integer.parseInt(br.readLine())));
        }
        pw.flush();
        pw.close();

    }

    private static String getfactor(int n) {
        int a=1;
        int b=4;
        int c = n-a-b;

        if (satisfyContrains(a,b,c)) {
            return format(a,b,c);
        }
        else {
            b = 2;
            c = n-a-b;
            if (satisfyContrains(a,b,c)) {
                return format(a,b,c);
            }
        }

        return "NO\n";
    }

    private static boolean satisfyContrains(int a, int b, int c) {
        return (a>0 && b>0 && c>0)&&(a!=b) && (b!=c) && (c!=a) && ((a%3)!=0)&& ((b%3)!=0)&& ((c%3)!=0);
    }

    private static String format(int i, int j, int k) {
        return "YES\n"+i+" "+j+" "+k+"\n";
    }
}
