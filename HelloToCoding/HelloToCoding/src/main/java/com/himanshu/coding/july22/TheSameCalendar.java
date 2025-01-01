package com.himanshu.coding.july22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TheSameCalendar {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());

        int currentDay = ((isLeapYear(n))?366:365)%7;
        n+=1;
        System.out.println(getYear(n,currentDay,isLeapYear(n-1)));



    }

    private static int getYear(int n,int currentDay, boolean wasLeapYear) {
        if (currentDay==0) {
            if(wasLeapYear == isLeapYear(n)){
                return n;
            }
        }
        currentDay += (isLeapYear(n))?366:365;
        n++;
        return getYear(n,currentDay%7,wasLeapYear);
    }

    private static boolean isLeapYear(int n) {
        if ( n%100 !=0) {
            return ((n%4)==0);
        } else {
            return ((n%400)==0);
        }
    }
}
