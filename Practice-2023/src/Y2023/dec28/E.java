package Y2023.dec28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/28/2023
 */
public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String [] word = br.readLine().split(" ");
            pw.append(isPossibleSum(word)?"YES":"NO").append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static boolean isPossibleSum(String[] word) {
        HashSet<Long> possibleSum = new HashSet<>();
        possibleSum.add(0l);
        long es=0;
        long os=0;

        for (int i=0;i<word.length;i++) {
            if ((i%2) == 0) {
             es+= Long.parseLong(word[i]);
            } else {
                os+= Long.parseLong(word[i]);
            }

            if (possibleSum.contains(es-os)) {
                return true;
            }

            possibleSum.add(es-os);
        }

        return false;
    }
}
