package com.himanshu.coding.July19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InfiniteSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        long k = getLargestCompleteSeq1(n,1,n);

        System.out.print((n==sqs(k))?k:n-sqs(k));
    }

    static long sqs(long l) {
        return ((l*(l+1))/2);
    }

    private static long getLargestCompleteSeq1(long n, long start, long end) {
        if (start==end) {
            if (sqs(start)>n)
            {
                return Integer.MIN_VALUE;
            }
            return start;
        }

        if (start+1 == end) {
            if (sqs(end) > n) {
                return start;
            } else {
                return end;
            }
        }

        long mid = (start+end)/2;

        if (sqs(mid) > n) {
            return getLargestCompleteSeq1(n,start,mid-1);
        } else {
            return getLargestCompleteSeq1(n,mid,end);
        }
    }
}
