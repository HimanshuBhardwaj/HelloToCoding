package Y2023.nov25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 11/25/2023
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            char [] str = br.readLine().toCharArray();
            int pos=str.length;

            for (int i=0; i<str.length; i++) {
                if (str[i] == 'A') {
                    pos=i;
                    break;
                }
            }

            pw.append(countPossibleSwap(pos, str, 0)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static int countPossibleSwap(int index, char[] str, int count) {

        if (index >= str.length) {
            return count;
        }

        for (int i = index;i<str.length;i++) {
            if (str[i] == 'B') {
                count += i-index;
                str[i]='A';
                return countPossibleSwap(i,str,count);
            }
        }

        return count;
    }
}
