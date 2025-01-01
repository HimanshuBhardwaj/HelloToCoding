package Dec2024.Dec22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class D {
    public static void main(String[] args) throws IOException {
        //poc();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            long x = Long.parseLong(str[1]);
            long y = Long.parseLong(str[2]);
            long n = Long.parseLong(str[0]);

            long [] a = new long[(int)n];

            str = br.readLine().split(" ");

            for (int i=0;i<str.length;i++) {
                a[i] =Long.parseLong(str[i]);
            }

            pw.append(numIndexes(x,y,a)+"\n");
        }
        pw.flush();
        pw.close();

    }

    static void poc() {
        ArrayList<Long> list = new ArrayList<>();
        list.add(3l);
        list.add(6l);

        SQRTDecomposition sqrtDecomposition = new SQRTDecomposition();
        //System.out.println(sqrtDecomposition.lastOccurence(list,0,list.size()-1,4));

    }

    private static int numIndexes(long x, long y, long[] a) {
        SQRTDecomposition sqrtDecomposition = new SQRTDecomposition(a);

        long [] suffixSum =getSuffixSumArray(a);
        long [] prefixSum =getPrefixSumArray(a);
        int count=0;

        for (int i=0;i<a.length;i++) {
            long etMin = x- ((i < a.length-1)?suffixSum[i+1]:0);
            long etMax = y-((i<a.length-1)?suffixSum[i+1]:0);
            long eMin = ((i>0)?prefixSum[i-1]:0)-etMax;
            long eMax = ((i>0)?prefixSum[i-1]:0)-etMin;

            int tCount= sqrtDecomposition.countWithinRangeElements(0,i-1,eMin,eMax);
            count+=tCount;
        }




        return count;
    }

    private static long[] getPrefixSumArray(long[] a) {
        long [] prefixSum  =new long[a.length];
        prefixSum[0]=a[0];

        for (int i=1;i<a.length;i++) {
            prefixSum[i]= prefixSum[i-1]+a[i];
        }
        return prefixSum;
    }

    private static long[] getSuffixSumArray(long[] a) {
        long [] suffixSum  =new long[a.length];
        suffixSum[suffixSum.length-1]=a[a.length-1];

        for (int i=a.length-2;i>=0;i--) {
            suffixSum[i]= suffixSum[i+1]+a[i];
        }
        return suffixSum;
    }
}

class PSum implements Comparable<PSum> {
    long value;
    int index;

    public PSum(long value, int index) {
        this.value = value;
        this.index = index;
    }

    @Override
    public int compareTo(PSum o) {

        return Long.compare(this.value, o.value);

    }
}

// given start index and end index, we need to find number of elements greater than s and less than or equal to e


class SQRTDecomposition implements ElementOperationI{
    long [] arr;
    ArrayList<Long>[]sqrt;
    int numSeg;
    int segSize;

    public SQRTDecomposition() {

    }

    public SQRTDecomposition(long [] arr) {
        this.arr = arr;
        this.segSize = (int)Math.sqrt(arr.length);
        this.numSeg = (arr.length/segSize)+(arr.length%segSize==0?0:1);
        sqrt = new ArrayList[numSeg];

        for (int i=0;i<sqrt.length;i++) {
            sqrt[i]=new ArrayList<>();
        }

        for (int i=0;i<arr.length;i++) {
            int sn = segNum(i);
            sqrt[sn].add(arr[i]);
        }

        for (int i=0;i<sqrt.length;i++) {
            Collections.sort(sqrt[i]);
        }
    }

    int segNum(int i) {
        return i/segSize;
    }

    int firstOccurence(ArrayList<Long> list, int s, int e, long element) {
        if (s>e || s<0 || e>=list.size()) {
            return -1;
        }

        if (list.get(s)>=element) {
            return s;
        }

        if (s==e) {
            return -1;
        }

        if (s==e-1) {
            if (list.get(e) >= e) {
                return e;
            }
            return -1;
        }

        int mid = (s+e)/2;

        if (list.get(mid)<element) {
            return firstOccurence(list,mid+1,e,element);
        } else {
            return firstOccurence(list,s,mid,element);
        }
    }

    int lastOccurence(ArrayList<Long> list, int s, int e, long x, long y) {
        if (s<0 ||e>=list.size() || s>e) {
            return -1;
        }

        if (list.get(e)<=y && list.get(e)>=x) {
            return e;
        }

        if (s==e) {
            return -1;
        }

        if (s+1 == e) {
            if(list.get(s) >= x && list.get(s)<=y) {
                return s;
            }
            return -1;
        }

        int mid = (s+e)/2;

        if (list.get(mid) <= y) {
            return lastOccurence(list,s,mid-1,x,y);
        } else if (list.get(mid) <x) {
            return lastOccurence(list,mid+1,e,x,y);
        }
        return -1;
    }


    @Override
    public int countWithinRangeElements(int sI, int eI, long x, long y) {
        if (sI > eI || sI < 0 || eI >= arr.length) {
            return 0;
        }
        int count=0;
        int ss = segNum(sI);
        int es = segNum(eI);

        for (int i=sI;segNum(i)==ss && i < arr.length && i<=eI;i++) {
            if (arr[i]>=x && arr[i]<=y) {
                count++;
            }
        }

        for (int i=eI;i>=0 && segNum(i)==es && segNum(i) != ss;i--) {
            if (arr[i]>=x && arr[i]<=y) {
                count++;
            }
        }

        for (int i=ss+1;i<es;i++) {
            int ep = i;
            int sp = i;

            if (sp!=-1 && ep!=-1) {
                count+= ep-sp+1;
            }
        }

        return count;
    }
}

interface ElementOperationI {
    int countWithinRangeElements(int sI, int eI, long x, long y);
}