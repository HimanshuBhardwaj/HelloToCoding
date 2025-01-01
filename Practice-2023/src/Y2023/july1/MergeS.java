package Y2023.july1;

import java.util.Arrays;

public class MergeS {
    public static void main(String[] args) {
        int [] arr = new int[]{-11,-22,};
        Arrays.stream(arr).forEach(value -> System.out.print(value+", "));
        System.out.println();
        sortI sortI = new MergeSort();
        int [] sorted = sortI.sort(arr);

        Arrays.stream(sorted).forEach(value -> System.out.print(value+", "));
        System.out.println();
    }
}


class MergeSort implements sortI {
    @Override
    public int[] sort(int[] array) {
        if (array==null || array.length<=1) {
            return array;
        }

        if (array.length==2) {
            int min = Math.min(array[0], array[1]);
            array[1] = Math.max(array[0], array[1]);
            array[0] = min;
            return array;
        }


        int mid = array.length/2;
        int a[] = sort(getArray(array,0,Math.min(mid, array.length-1)));

        int b[] = sort(getArray(array,Math.min(array.length-1, mid+1),array.length-1));
        return merge(a,b);
    }

    private int[] getArray(int[] array, int start, int end) {
        int [] newArr = new int[end-start+1];
        int pos=0;
        for (int i=start;i<=end;i++) {
            newArr[pos++] = array[i];
        }

        return newArr;
    }

    private int[] merge(int[] a, int[] b) {
        if (b==null || b.length==0) {
            return a;
        }

        if (a == null || a.length == 0) {
            return b;
        }

        int [] newArr = new int[a.length+b.length];
        int pos=0;
        int posA=0;
        int posB=0;

        while (posA<a.length && posB<b.length) {
            if (a[posA] < b[posB]) {
                newArr[pos++] = a[posA++];
            } else {
                newArr[pos++] = b[posB++];
            }
        }

        for (int i=posA;i<a.length;i++) {
            newArr[pos++] = a[i];
        }


        for (int i=posB;i<b.length;i++) {
            newArr[pos++] = b[i];
        }

        return newArr;
    }
}

interface sortI {
    int [] sort(int [] array);
}