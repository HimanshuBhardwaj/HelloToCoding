package com.himanshu.coding.july23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class July23 {
    static LinkedList<Integer> items;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader( new InputStreamReader( System.in));
        String [] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int k = Integer.parseInt(str[2]);

        items = new LinkedList<>();
        str = br.readLine().split(" ");

        for (int i=0;i<str.length;i++) {
            items.addLast(Integer.parseInt(str[i]));
        }

        ArrayList<ArrayList<Integer>> orders = new ArrayList<>();

        for (int i=0;i<n;i++) {
            ArrayList<Integer> personOrder = new ArrayList<>();
            orders.add(personOrder);
            str=br.readLine().split(" ");

            for (int j=0;j<str.length;j++) {
                personOrder.add(Integer.parseInt(str[j]));
            }
        }

        long cost = 0l;

        for (int i=0;i<n;i++) {
            cost+=findCost(orders.get(i));
        }

        System.out.println(cost);
    }

    private static long findCost(ArrayList<Integer> order) {
        long cost=0;



for (int i=0;i<order.size();i++) {
            int index = 1;
            int itemChoosen=-1;
            for (int item : items) {
                if (order.get(i)==item) {
                    cost += index;
                    itemChoosen=item;
                    break;
                }
                index++;
            }
            items = reconstructItemList(items,itemChoosen);
        }

        return cost;
    }

    private static LinkedList<Integer> reconstructItemList(LinkedList<Integer> items, int itemChoosen) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(itemChoosen);

        for (int x:items) {
            if (x!=itemChoosen) {
                linkedList.addLast(x);
            }
        }
        return linkedList;
    }
}
