package Y2023.dec28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/28/2023
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String word = br.readLine();
            pw.append(breakWord(word));
        }
        pw.flush();
        pw.close();
    }

    private static String breakWord(String word) {
        int lastC=word.length()-1;

        Stack<String> stack = new Stack<>();

        while (lastC >=0 ) {
            if (isVovel(word.charAt(lastC))) {
                int starting = lastC-1;
                stack.push(word.substring(starting,lastC+1));
                lastC = starting-1;
            } else {
                int starting = lastC-2;
                stack.push(word.substring(starting,lastC+1));
                lastC = starting-1;
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
            if (!stack.isEmpty()) {
                sb.append(".");
            }
        }

        sb.append("\n");

        return sb.toString();
    }

    static boolean isVovel(char c) {
        return (c=='a')|| (c=='e');
    }
}
