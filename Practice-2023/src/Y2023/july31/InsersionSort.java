package Y2023.july31;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;

public class InsersionSort {
    public static void main(String[] args) {
        Sort<Long> sort = new BubbleSort<>();
        Long [] longN = new Long [] {3l, 1l, 2l, 5l, 6l, 0l, 4l, -1l, 7l};
        sort.sort(longN);
        for (Long l: longN) {
            System.out.println(l);
        }
    }
}

class QuickSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] t) {

    }
}
class BubbleSort<T extends  Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] t) {
        if (t == null || t.length==0) {
            return;
        }

        for (int i=0;i<t.length;i++) {
            for (int j=0; j < t.length-1-i; j++) {
                if (t[j+1].compareTo(t[j]) < 0) {
                    T temp = t[j+1];
                    t[j+1] = t[j];
                    t[j] = temp;
                }
            }
        }
    }
}

class InserstionSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] array) {
        if (array== null || array.length==0) {
            return;
        }

        for (int i=1; i<array.length;i++) {
            T temp = array[i];
            for (int j=i-1;j>=0;j--) {
                if (array[j+1].compareTo(array[j]) <= 0) {
                    array[j+1] = array[j];
                } else {
                    break;
                }

                array[j] = temp;
            }
        }
    }
}

interface Sort<T> {
      void sort(T[] t);
}