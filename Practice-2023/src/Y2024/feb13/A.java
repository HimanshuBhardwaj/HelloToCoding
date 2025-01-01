package Y2024.feb13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/13/2024
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            pw.append(smallestWord(n)).append("\n");
        }

        pw.flush();
        pw.close();
    }

    private static String smallestWord(int n) {
        char [] chars = new char[3];
        return smallestWordHelper(0,chars, n);
    }

    private static String smallestWordHelper(int index, char[] chars, int n) {
        if (index==chars.length) {
            if (n==0) {
                return new String(chars);
            } else {
                return null;
            }
        }

        if (n<=0) {
            return null;
        }


        for (int i=0;i<26;i++) {
            chars[index] = (char) ('a'+i);
            String word = smallestWordHelper(index+1, chars, n-(i+1));

            if (word != null) {
                return word;
            }
        }

        return null;
    }


}
