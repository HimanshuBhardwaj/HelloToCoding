package com.himanshu.coding.aug21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class FourSegments {
    static long [] sum;
    static long [] index;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long arr[] = new long[n];

        String [] str = br.readLine().split(" ");
        long totalSum=0;

        for (int i=0;i<str.length;i++) {
            arr[i] = Long.parseLong(str[i]);
            totalSum+=arr[i];
        }

        long totalSumSubArray[] = maximumSumSubArray(arr);

        long prefixSum=0;

        long maximum = totalSumSubArray[0]- (totalSum-totalSumSubArray[0]);

        for (int i=0;i<arr.length;i++) {
            prefixSum+=arr[i];
            long positiveSum=prefixSum;

            if(i < (arr.length-1)) {
                positiveSum = prefixSum+ totalSumSubArray[i+1];
            }

            maximum = Math.max(maximum, positiveSum- (totalSum-positiveSum));
        }

    }

    static void printArray(long[]arr) {
        for (int i=0;i<arr.length;i++) {
            System.out.print(arr[i]+"\t");
        }
        System.out.println();
    }

    //O(n^2)
    //given an i, this will return subarray with maximum sum from i to end
    static long [] maximumSumSubArray(long arr[]) {
        sum = new long[arr.length];

        long maxSum=0;
        Queue<Long> queue = new LinkedList<>();

        for (int i=arr.length-1;i>=0;i--) {
            if ((maxSum+arr[i])>0) {
                maxSum=maxSum+arr[i];
                queue.add(arr[i]);
                sum[i]=maxSum;
                index[i]=(i<(arr.length-1))?(index[i+1]):i;
            } else {
                if (queue.isEmpty()) {
                    if (arr[i]>0) {
                        queue.add(arr[i]);
                        maxSum+=arr[i];
                        index[i] = i;
                    }
                } else {
                    int count=0;
                    while (!queue.isEmpty() && (arr[i]+maxSum)<=0) {
                        long num = queue.poll();
                        maxSum-=num;
                        count++;
                    }
                    if ((arr[i]+maxSum)>0) {
                        maxSum+=arr[i];
                        queue.add(arr[i]);
                        sum[i]=maxSum;
                        index[i] = (i<arr.length-1)?(index[i+1]-count):(i);
                    }
                }
            }
        }

        printArray(sum);

        for (int i=sum.length-2;i>=0;i--) {
            sum[i] = Math.max(sum[i],sum[i+1]);
        }
        return sum;
    }
}
