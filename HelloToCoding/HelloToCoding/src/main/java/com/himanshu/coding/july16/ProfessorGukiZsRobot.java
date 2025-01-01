package com.himanshu.coding.july16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProfessorGukiZsRobot {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");

        int x1 = Integer.parseInt(str[0]);
        int y1 = Integer.parseInt(str[1]);

        str = br.readLine().split(" ");

        int x2 = Integer.parseInt(str[0]);
        int y2 = Integer.parseInt(str[1]);

        System.out.print(Math.max(Math.abs(x1-x2),Math.abs(y2-y1)));
    }
}
