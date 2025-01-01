package com.himanshu.coding.sept15;


import java.util.*;

public class LinkedlistSort {
    public static void main(String[] args) {
        LinkedList<Element2> linkedlist  =  new LinkedList();
        linkedlist.add(new Element2(11));
        linkedlist.add(new Element2(10));
        linkedlist.add(new Element2(12));
        linkedlist.add(new Element2(8));

        System.out.println(linkedlist);

Comparator<Element2> comparator = new Comparator<Element2>() {
    @Override
    public int compare(Element2 o1, Element2 o2) {
        return Integer.compare(o1.value, o2.value);
    }
};

        Collections.sort(linkedlist, comparator);
        System.out.println(linkedlist);


    }

}


class Element2 {
    int value;

    public Element2(int v) {
        this.value = v;
    }

    @Override
    public String toString() {
        return "Element{" +
                "value=" + value +
                '}';
    }
}