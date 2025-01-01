package com.himanshu.coding.july20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TheTime {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(":");
        int hour = Integer.parseInt(str[0]);
        int minute = Integer.parseInt(str[1]);

        int offset = Integer.parseInt(br.readLine());

        printTime(getMinute(hour,minute)+offset);

    }

    static long getMinute(int hout, int minute) {
        return 60*hout+minute;
    }
    static void printTime(long minute) {
        long min = minute%60;
        long h = ((minute-min)/60)%24;

        String s = "";

        if (h<10) {
            s= "0"+h+":";
        } else {
            s = h+":";
        }

        if (min<10) {
            s = s+"0"+min;
        } else {
            s = s+min;
        }
        System.out.print(s);


    }
}
