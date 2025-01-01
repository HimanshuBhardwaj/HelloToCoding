package com.himanshu.coding.feb13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeSort1 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(3);
        list.add(0);
        list.add(5);
        list.add(5);
        list.add(-5);
        list.add(115);
        list.add(-1115);

        Sort sortF = new LibSort();

        List<Integer> sList = sortF.sort(list);

        for (int x: sList)
        {
            System.out.print(x+", ");
        }
        System.out.println();
        System.out.println();
        System.out.println();

        for (int x: list)
        {
            System.out.print(x+", ");
        }
        System.out.println();
        System.out.println();
        System.out.println();

        List<Integer> sAL = new MergeSort().sort(list);


        for (int x: sAL)
        {
            System.out.print(x+", ");
        }
        System.out.println();
        System.out.println();
        System.out.println();

    }
}


class MergeSort implements Sort
{
    @Override
    public List<Integer> sort(List<Integer> list) {
       ArrayList<Integer> arrayList = new ArrayList<>(list);
       return sortHelper(0,list.size()-1,arrayList);
    }

    private ArrayList<Integer> sortHelper(int s, int e, ArrayList<Integer> list)
    {
        if (s<e) {
                if (s == e-1) {
                    int min = Math.min(list.get(s),list.get(e));
                    int max = Math.max(list.get(s),list.get(e));
                    ArrayList<Integer> sList =  new ArrayList<>();
                    sList.add(min);
                    sList.add(max);
                    return  sList;
                } else {
                    int mid = (s+e)/2;
                    ArrayList<Integer> list1 = sortHelper(s,mid,list);
                    ArrayList<Integer> list2 = sortHelper(mid+1,e,list);
                    return merge(list1,list2);
                }
        } else if(s==e) {
            ArrayList<Integer> list1 = new ArrayList<>();
            list1.add(list.get(s));
            return list1;
        }
        return  new ArrayList<>();
    }

    private ArrayList<Integer> merge(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> sortedList = new ArrayList<>();

        int pa=0;
        int pb=0;

        while (pa<list1.size() && pb < list2.size())
        {
            if (list1.get(pa) < list2.get(pb)) {
                sortedList.add(list1.get(pa++));
            }else {
                sortedList.add(list2.get(pb++));
            }

            while (pa<list1.size())
            {
                sortedList.add(list1.get(pa++));
            }

            while (pb<list2.size())
            {
                sortedList.add(list2.get(pb++));
            }
        }

        return  sortedList;
    }
}

class LibSort implements Sort
{
    @Override
    public List<Integer> sort(List<Integer> list) {
        ArrayList<Integer> tl = new ArrayList<Integer>(list);
        Collections.sort(tl);
        return tl;
    }
}

interface Sort
{
     List<Integer> sort(List<Integer> list);
}
