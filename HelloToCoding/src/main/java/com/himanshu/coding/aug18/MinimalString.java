package com.himanshu.coding.aug18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class MinimalString {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char [] chars = br.readLine().toCharArray();
        char [] minRight = new char[chars.length];

        minRight[minRight.length-1]=chars[minRight.length-1];

        for (int i=minRight.length-2;i>=0;i--) {
            minRight[i]= (char)Math.min((int)chars[i],(int)minRight[i+1]);
        }

        Stack<Character> stack = new Stack<>();
        StringBuffer str = new StringBuffer();

        int pos=0;
        String output="";

        while (pos < chars.length) {
            if (!stack.isEmpty()) {
                if (stack.peek() <= minRight[pos]) {
                    str.append(stack.pop());
                } else {
                    stack.push(chars[pos++]);
                }
            } else {
                stack.push(chars[pos++]);
            }
        }


       while (!stack.isEmpty()) {
           str.append(stack.pop());
       }

        System.out.println(str.toString());

    }
}
