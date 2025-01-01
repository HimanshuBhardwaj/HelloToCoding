package com.himanshu.coding.july23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TheSmallestStringConcatenation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<String> strings = new ArrayList<>();

        for (int i=0;i<n;i++) {
            strings.add(br.readLine());
        }
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return lexographicalySmall(o1+o2,o2+o1);
            }

            int lexographicalySmall(String s1, String s2) {
                int pos=0;

                while ((pos < s1.length()) && (pos < s2.length())) {
                    if (Character.compare(s1.charAt(pos),s2.charAt(pos)) !=0) {
                        return Character.compare(s1.charAt(pos),s2.charAt(pos));
                    } else {
                        pos++;
                    }
                }

                if (pos==s1.length() && pos < s2.length()) {
                    return -1;
                } else if(pos == s2.length() && pos < s1.length()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } ;

        Collections.sort(strings,comparator);

        StringBuilder sb = new StringBuilder();

        for (String s:strings) {
            sb.append(s);
        }
        System.out.print(sb.toString());

    }
}
