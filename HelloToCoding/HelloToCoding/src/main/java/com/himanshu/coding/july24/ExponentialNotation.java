package com.himanshu.coding.july24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExponentialNotation {
    static double remainerd;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String whole=null;
        String fraction=null;
        Double d = new Double(str);

        int b = getB(d);





        StringBuilder number = new StringBuilder();
        boolean flag=false;
        boolean flag2=false;
        for (int i=0;i<str.length();i++) {
            if (!flag && ((str.charAt(i)-'0')>0) && (str.charAt(i)-'0') <= 9) {
                flag=true;
                number.append(str.charAt(i));
            } else if (flag && !flag2 && ((str.charAt(i)-'0')>=0) && ((str.charAt(i)-'0') <= 9)) {
                flag2=true;
                number.append(".");
                number.append(str.charAt(i));
            } else if (flag && flag2 && ((str.charAt(i)-'0')>=0) && ((str.charAt(i)-'0') <= 9) ) {
                number.append(str.charAt(i));
            }
        }

        String numS = number.toString();
        String orignalNumber="";
        for (int i=numS.length()-1;i>=0;i--) {
            if (numS.charAt(i) != '0') {
                orignalNumber = numS.substring(0,i+1);
                break;
            }
        }

        if (orignalNumber.charAt(orignalNumber.length()-1)=='.') {
            orignalNumber = orignalNumber.substring(0,orignalNumber.length()-1);
        }

        if (b==0) {
            System.out.print(orignalNumber);
            return;
        }

       // System.out.println("Orignal Number"+orignalNumber);

        if (remainerd == ((int)remainerd)  ) {
            System.out.print(orignalNumber + "E" + b);
        } else {
            System.out.print(orignalNumber + "E" + b);
        }


    }

    private static int getB(Double d) {
        int b=0;

        if (Double.compare(d,10) >=0) {
            while (Double.compare(d, 10) >= 0) {
                b++;
                d = d / 10;
            }
        } else {
            while (Double.compare(d,1)<0) {
                d = d*10;
                b--;
            }
        }

        remainerd=d;

        return b;
    }
}
