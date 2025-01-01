package Y2023.nov11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;

/**
 * @author Himanshu Bhardwaj
 * @Date 11/11/2023
 */
public class QueriesForTheArray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        for (int i=1;i<=t;i++){
            String str = br.readLine();
            pw.append(isValid(str,(i==777))?"YES":"NO").append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static boolean isValid(String str, boolean printI) {
        Stack<Integer> stack = new Stack<>();
        int count0=0;
        int lastSorted = 0;

        for (int i=0;i<str.length();i++) {
            switch (str.charAt(i)) {
                case '+': {
                    lastSorted++;
                    stack.push(1);
                    break;
                }
                case '-': {
                    lastSorted--;
                    int top = stack.pop();
                    if (top==0) {
                        count0--;
                    }
                    break;
                }
                case '0': {
                    if ( (lastSorted==0) || stack.size() <= 1) {
                        return false;
                    }
                    int top = stack.pop();
                    if (top==0) {
                        count0--;
                    }

                    stack.push(0);
                    count0++;
                    break;
                }
                case '1': {
                    lastSorted=0;
                    if (count0 > 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
