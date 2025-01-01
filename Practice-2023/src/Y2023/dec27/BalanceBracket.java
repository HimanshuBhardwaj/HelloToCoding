package Y2023.dec27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/27/2023
 */
public class BalanceBracket {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(isBalanced(br.readLine()));

    }

    private static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i=0;i<s.length();i++) {
            if (s.charAt(i)=='(') {
                stack.push(s.charAt(i));
            } else {
                if (!stack.isEmpty() && stack.peek()=='(') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
