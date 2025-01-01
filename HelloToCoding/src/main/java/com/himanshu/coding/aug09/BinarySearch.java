package com.himanshu.coding.aug09;

public class BinarySearch {
    public static void main(String[] args) {
        int [] a = {0,2,5, 15, 25};

        System.out.println(binarySearch(0,a.length-1,a,1));
        System.out.println(ceil(0,a.length-1,a,25));
        System.out.println(floor(0,a.length-1,a,0));
    }

    private static int floor(int s, int e, int []a, int element) {
        if (s<0 || e>=a.length || a==null || a[s]>element) {
            return -1;
        }

        if (s==e) {
            if (a[e] <= element) {
                return a[e];
            }
            return -1;
        }

        if (s==(e-1) ) {
            if (a[e] <= element) {
                return a[e];
            }

            if (a[s] <= element) {
                return a[s];
            }
            return -1;
        }

        int mid = (s+e)/2;

        //int floorL = floor(s,mid-1,a,element);
        int floorL = floor(mid+1,e,a,element);
        if (floorL != -1) {
            return floorL;
        }
        if (a[mid]<=element) {
            return a[mid];
        }

        return floor(s,mid-1,a,element);
    }


    private static int ceil(int s, int e, int []a, int element) {
        if (s>e || s<0 || e>=a.length || element > a[e]) {
            return -1;
        }

        if (s==e) {
            return a[e];
        }

        if (s == (e-1)) {
            if (a[s]>=element) {
                return a[s];
            }
            if (a[e]>=element) {
                return a[e];
            }

            return -1;
        }

        int mid = (s+e)/2;

        int ceilL = ceil(s,mid-1,a,element);

        if (ceilL != -1) {
            return ceilL;
        }

        if (a[mid] >= element) {
            return a[mid];
        }
        return ceil(mid+1, e, a, element);
    }

    private static int binarySearch(int s, int e, int[] a, int element) {
        if (s>e || s<0 || e>= a.length) {
            return -1;
        }

        if (s==e) {
            if (a[s]==element) {
                return s;
            } else {
                return -1;
            }
        }

        if (s==(e-1)) {
            if (a[s]==element) {
                return s;
            }
            if (a[e] == element) {
                return e;
            }

            return -1;
        }

        int mid = (s+e)/2;

        if (a[mid] == element) {
            return mid;
        }

        if (a[mid] > element) {
            return binarySearch(s,mid-1,a,element);
        } else {
            return binarySearch(mid+1,e,a,element);
        }
    }
}
