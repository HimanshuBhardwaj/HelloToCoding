package com.himanshu.coding.july16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ComparingTwoLongIntegers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ta = br.readLine();
        String tb = br.readLine();
        String a = sanitize(ta);
        String b = sanitize(tb);
/*
        System.out.println(a);
        System.out.println(b);
*/

        if (a.length() > b.length()) {
            System.out.print(">");
        } else if (a.length() < b.length()) {
            System.out.print("<");
        } else {
            for (int i=0;i<a.length();i++) {
                if (a.charAt(i) > b.charAt(i) ){
                    System.out.print(">");
                    break;
                } else if (a.charAt(i) < b.charAt(i)) {
                    System.out.println("<");
                    break;
                }

                if (i==a.length()-1) {
                    System.out.print("=");
                }
            }
        }



    }

    private static String sanitize(String a) {
        int pos=0;

        while (a.charAt(pos)=='0') {
            pos++;
            if (pos==a.length()) {
                return "0";
            }
        }

        return a.substring(pos);
    }
}
