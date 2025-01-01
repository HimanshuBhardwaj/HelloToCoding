    package com.himanshu.coding.July19;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.*;

    public class QueriesAboutLessOrEqualElements {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String [] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);


            str = br.readLine().split(" ");
            ArrayList<Integer> aa = new ArrayList<>();

            for (int i=0;i<str.length;i++) {
                aa.add(Integer.parseInt(str[i]));
            }

            Collections.sort(aa);

            str = br.readLine().split(" ");
            int [] b = new int[str.length];

            for (int i=0;i<str.length;i++) {
                b[i] = Integer.parseInt(str[i]);
            }

            System.out.println(getOutput1(aa,b));


        }

        private static String getOutput1(ArrayList<Integer> a, int[] b) {
            TreeMap<Integer,Integer> map = new TreeMap<>();

            for (int x:a) {
                Integer count = map.get(x);
                if (count==null) {
                    map.put(x,1);
                } else {
                    map.put(x,count+1);
                }
            }

            int count=0;
            TreeMap<Integer,Integer> fMap = new TreeMap<>();

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                count+=entry.getValue();
                fMap.put(entry.getKey(),count);
            }

            StringBuilder sb = new StringBuilder();

            for (int x:b) {
                Map.Entry<Integer,Integer> entry=  fMap.floorEntry(x);
                int output = (entry!=null)?entry.getValue():0;
                sb.append(output);
                sb.append(" ");
            }

            return sb.toString();
        }


        private static String getOutput(int[] a, int[] b) {
            StringBuilder sb = new StringBuilder();

            for (int x:b) {
                sb.append(numberLessThan(a,x,0,a.length-1)+" ");
            }

            return sb.toString();
        }

        private static int numberLessThan(int[] a, int x, int start, int end) {
            if (start > end || start<0 || end<0 || start>=a.length) {
                return 0;
            }

            if (end <= start+100) {
                int count=0;
                for (int i=start;i<=end;i++) {
                    if (a[i]<=x) {
                        count++;
                    } else {
                        return count;
                    }
                }
            }

            if (a[end] <= x) {
                return end-start+1;
            }

            if (start == end) {
                return 0;
            }

            if (start == end-1) {
                if (a[start] <= x) {
                    return 1;
                }
            }

            int mid = start+end-1;

            if (a[mid] <= x) {
                return mid-start+1 + numberLessThan(a,x,mid+1,end);
            } else {
                return numberLessThan(a,x,start,mid-1);
            }



        }
    }
