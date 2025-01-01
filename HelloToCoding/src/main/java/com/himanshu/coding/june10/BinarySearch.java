package com.himanshu.coding.june10;

import java.util.ArrayList;
import java.util.Collections;

public class BinarySearch {
    public static void main(String[] args) {
        ArrayList<Elements> elements = new ArrayList<>(10);
        elements.add(new Elements(10));
        elements.add(new Elements(33));
        elements.add(new Elements(15));
        elements.add(new Elements(897));
        elements.add(new Elements(78));
        elements.add(new Elements(11));
        elements.add(new Elements(515));
        elements.add(new Elements(417));
        elements.add(new Elements(119));
        elements.add(new Elements(-20));

        Collections.sort(elements);

        for (int i=0;i<elements.size();i++) {
            System.out.println(i+", "+elements.get(i));
        }

        System.out.println(binaryFind(elements,0,elements.size()-1,119));



    }

    private static int binaryFind(ArrayList<Elements> elements, int start, int end, int i2) {
        if (start<0 || end >=elements.size() || end<start) {
            return Integer.MIN_VALUE;
        }
        if (start==end) {
            return (elements.get(start).value==i2)?start:Integer.MIN_VALUE;
        }
        if (start+1 == end) {
            if (elements.get(start).value==i2) {
                return start;
            }
            if (elements.get(end).value==i2) {
                return end;
            }
            return Integer.MIN_VALUE;
        }

        int mid = (start+end)/2;

        if (elements.get(mid).value==i2) {
            return i2;
        } else if (elements.get(mid).value > i2) {
            return binaryFind(elements,start,mid-1,i2);
        } else {
            return binaryFind(elements,mid+1, end, i2);
        }
    }
}

class Elements implements  Comparable<Elements> {
    int value;

    public Elements(int element) {
        this.value = element;
    }

    @Override
    public int compareTo(Elements o) {
        return Integer.compare(this.value,o.value);
    }

    public String toString() {
        return value+"";
    }
}