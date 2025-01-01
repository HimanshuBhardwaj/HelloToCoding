package com.himanshu.coding.july22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SPalindrome {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String rStr = reverse(str);

        int commonIndx = commonIndex(str, rStr);

        if (commonIndx >= str.length()/2) {
            System.out.print("TAK");
        } else {
            System.out.print("NIE");
        }
    }

    private static int commonIndex(String str, String rStr) {

        int ci=-1;

        if (str.length()%2==0) {
            return ci;
        }

        for (int i=0;i<str.length();i++) {
            if (str.charAt(i) == rStr.charAt(i)) {
                ci=i;
            } else {
                break;
            }
        }

        return ci;
    }

    private static String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i=str.length()-1;i>=0;i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
}
