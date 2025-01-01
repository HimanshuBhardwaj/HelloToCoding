package Y2023.nov11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 11/11/2023
 */
public class GameOnPermutation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String [] str = br.readLine().split(" ");
            int [] arr = new int[n];

            for (int i=0;i<n;i++) {
                arr[i] = Integer.parseInt(str[i]);
            }

            pw.append(getLuckyNumber3(arr)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static int getLuckyNumber3(int[] arr) {
        int lowestLuckyNumber = Integer.MAX_VALUE;
        int lowestNumber = arr[0];
        int count=0;

        for (int i=1;i<arr.length;i++) {
            if (lowestNumber < arr[i] && lowestLuckyNumber > arr[i]) {
                count++;
                lowestLuckyNumber = arr[i];
            }
            lowestNumber = Math.min(lowestNumber, arr[i]);
        }

        return count;
    }

    private static int getLuckyNumber2(int[] arr) {
        TreeSet<Number> luckyNumber = new TreeSet<>();
        TreeSet<Number> unLuckyNumber = new TreeSet<>();

        unLuckyNumber.add(new Number(0,arr[0]));


        for (int i=1;i<arr.length;i++) {
            Number number = new Number(i,arr[i]);
            Number smLN = luckyNumber.floor(number);

            if (smLN == null) {
                Number uN = unLuckyNumber.floor(number);
                if (uN== null) {
                    unLuckyNumber.add(number);
                } else {
                    luckyNumber.add(number);
                }
            } else {
                unLuckyNumber.add(number);
            }
        }

        return luckyNumber.size();
    }

    static class Number implements Comparable<Number>{
        int number;
        int index;

        public Number(int index, int number) {
            this.number = number;
            this.index = index;
        }

        @Override
        public int compareTo(Number o) {
            if (this.number != o.number) {
                return Integer.compare(this.number, o.number);
            }
            return Integer.compare(this.index, o.index);
        }
    }

    private static int getLuckyNumber1(int[] arr) {
        int [] dp = new int[arr.length];


        for (int i=1;i<arr.length;i++) {
            boolean flag = false;
            for (int j=i-1;j>=0;j--) {
                if (arr[j] < arr[i]) {
                    flag = true;
                }
            }

            if (flag) {
                dp[i] = 1;

                for (int j=i-1;j>=0;j--) {
                    if (arr[j] < arr[i] && dp[j]==1) {
                        dp[i]=0;
                        break;
                    }
                }
            }
        }

        int count=0;

        for (int x: dp) {
            if (x==1) {
                count++;
            }
        }

        return count;
    }
}
