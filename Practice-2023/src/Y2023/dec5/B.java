package Y2023.dec5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/5/2023
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String s = br.readLine();
            pw.append(getFormattedString(s)).append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static String getFormattedString(String s) {
        TreeSet<Char> lowerChar = new TreeSet<>();
        TreeSet<Char> upperChar = new TreeSet<>();

        for (int i=0;i<s.length();i++) {
            if (('a' <= s.charAt(i)) && ('z' >= s.charAt(i))) {
                if ('b' != s.charAt(i)) {
                    lowerChar.add(new Char(s.charAt(i), i));
                } else {
                    if (lowerChar.size() > 0) {
                        lowerChar.remove(lowerChar.last());
                    }
                }
            } else {
                if ('B' != s.charAt(i)) {
                    upperChar.add(new Char(s.charAt(i), i));
                } else {
                    if (upperChar.size() > 0) {
                        upperChar.remove(upperChar.last());
                    }
                }
            }
        }

        lowerChar.addAll(upperChar);
        StringBuilder sb = new StringBuilder();

        for (Char c: lowerChar) {
            sb.append(c.c);
        }
        return sb.toString();
    }
}

class Char implements Comparable<Char> {
    char c;
    int index;

    public Char(char c, int index) {
        this.c = c;
        this.index = index;
    }

    @Override
    public int compareTo(Char o) {
        return Integer.compare(this.index, o.index);
    }
}
