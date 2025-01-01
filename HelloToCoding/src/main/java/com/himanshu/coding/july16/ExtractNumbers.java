package com.himanshu.coding.july16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ExtractNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        String [] token = str.split("[,;]",-1);

        getWords(token);
    }

    private static void getWords(String [] stringList) {
        StringBuilder asb = new StringBuilder();
        boolean flaga=false;
        boolean flagb=false;
        StringBuilder bsb = new StringBuilder();

        for ( String s:stringList) {
            if(isInteger(s)) {
                if (flaga) {
                    asb.append("," + s);
                } else {
                   flaga=true;
                    asb.append(s);
                }
            } else {
                if (flagb) {
                    bsb.append("," + s);
                } else {
                    flagb=true;
                    bsb.append(s);
                }
            }
        }
        String a=asb.toString();
        String b=bsb.toString();
        System.out.println((!flaga)?"-":("\""+a+ "\""));
        System.out.println((!flagb)?"-":("\""+b+ "\""));
    }

    private static boolean isInteger(String s) {
     try {
         if (s.length() <=9) {
             int i = Integer.parseInt(s);

             if (Integer.toString(i).equals(s)) {
                 return true;
             }
             return false;
         } else {
             if (s.startsWith("0")) {
                 return false;
             }

             for (int i=0;i<s.length();i++) {
                 if (((s.charAt(i)-'0') < 0) || (s.charAt(i)-'0') >9) {
                     return false;
                 }
             }

             return true;
         }
     }catch (Exception ex)
     {
         return false;
     }
    }
}
