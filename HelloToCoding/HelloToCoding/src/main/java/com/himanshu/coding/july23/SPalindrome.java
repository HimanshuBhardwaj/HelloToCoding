package com.himanshu.coding.july23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class SPalindrome {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Character,Character> characterCharacterHashMap = new HashMap<>();
        characterCharacterHashMap.put('A','A');
        characterCharacterHashMap.put('b','d');
        characterCharacterHashMap.put('d','b');
        characterCharacterHashMap.put('H','H');
        characterCharacterHashMap.put('I','I');
        characterCharacterHashMap.put('M','M');
        characterCharacterHashMap.put('o','o');
        characterCharacterHashMap.put('O','O');
        characterCharacterHashMap.put('p','q');
        characterCharacterHashMap.put('q','p');
        characterCharacterHashMap.put('T','T');
        characterCharacterHashMap.put('U','U');
        characterCharacterHashMap.put('V','V');
        characterCharacterHashMap.put('v','v');
        characterCharacterHashMap.put('W','W');
        characterCharacterHashMap.put('w','w');
        characterCharacterHashMap.put('x','x');
        characterCharacterHashMap.put('X','X');
        characterCharacterHashMap.put('Y','Y');

        HashSet<Character> symmetricalNumbers = new HashSet<>();
        symmetricalNumbers.add('A');
        symmetricalNumbers.add('H');
        symmetricalNumbers.add('I');
        symmetricalNumbers.add('M');
        symmetricalNumbers.add('O');
        symmetricalNumbers.add('o');
        symmetricalNumbers.add('T');
        symmetricalNumbers.add('U');
        symmetricalNumbers.add('V');
        symmetricalNumbers.add('v');
        symmetricalNumbers.add('W');
        symmetricalNumbers.add('w');
        symmetricalNumbers.add('X');
        symmetricalNumbers.add('x');
        symmetricalNumbers.add('Y');




        String str = br.readLine();
        for (int i=0;i<str.length()/2;i++) {
            if (!characterCharacterHashMap.containsKey(str.charAt(i)))
            {
                System.out.print("NIE");
                return;
            }
            if (str.charAt(str.length()-1-i)!=characterCharacterHashMap.get(str.charAt(i))) {
                System.out.print("NIE");
                return;
            }
        }

        if ((str.length()%2) ==1 ) {
            int i = str.length()/2;

                if (!symmetricalNumbers.contains(str.charAt(i))) {
                    System.out.print("NIE");
                    return;
                }

        }

        System.out.println("TAK");
    }
}
