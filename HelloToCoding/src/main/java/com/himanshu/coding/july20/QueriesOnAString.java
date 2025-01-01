package com.himanshu.coding.july20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class QueriesOnAString {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strA = br.readLine();


        int m = Integer.parseInt(br.readLine());
        ArrayList<Query> queries = new ArrayList<>();

        for (int i=0;i<m;i++) {
            String [] str = br.readLine().split(" ");
            queries.add(new Query(Integer.parseInt(str[0]),
                    Integer.parseInt(str[1]), Integer.parseInt(str[2])));
        }
        for (int i=0;i<m;i++) {
            int l = queries.get(i).l;
            int r = queries.get(i).r;
            int k = queries.get(i).k;

            String tempStrA1 = strA.substring(0, l);
            String tempStrA2= strA.substring(l,r+1);
            String tempStrA3=strA.substring(r+1,strA.length());
            String tempStrA4 = rotate(tempStrA2,k);
            strA=tempStrA1+tempStrA4+tempStrA3;
        }

        System.out.print(strA);
    }

    private static String rotate(String tempStrA2, int k) {
        return tempStrA2.substring(tempStrA2.length()-k,tempStrA2.length())+
                tempStrA2.substring(0,tempStrA2.length()-k);
    }
}

class Query {
    int l;
    int r;
    int k;

    public Query(int l, int r, int k) {
        this.l=l-1;
        this.r=r-1;
        this.k=k;
        this.k = this.k%(r-l+1);
    }
}