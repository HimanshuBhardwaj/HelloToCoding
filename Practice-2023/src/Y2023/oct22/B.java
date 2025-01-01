package Y2023.oct22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 10/22/2023
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);
            pw.append(isPossiblePalindrome(n,k,br.readLine())?"YES":"NO").append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static boolean isPossiblePalindrome(int n, int k, String s) {
        int charMap[] = new int[27];

        if (k > s.length()) {
            return false;
        }

        if ((k-s.length()) <=1 && (k-s.length())>=0  ) {
            return true;
        }

        for (char c: s.toCharArray()) {
            charMap[(int)(c-'a')]++;
        }

        int numOddCchars = 0;

        for (int i:charMap) {
            if (i%2 != 0) {
                numOddCchars++;
            }
        }

        if (numOddCchars >= k) {
            numOddCchars -=k;
            if (numOddCchars <= 1) {
                return true;
            } else {
                return false;
            }
        }

        return true;
    }

    boolean isPalindrome(int [] charMap) {
        int count1 = 0;

        for (int i: charMap) {
            if(i%2 == 1) {
                count1++;
            }
        }

        return (count1<=1);
    }
}
