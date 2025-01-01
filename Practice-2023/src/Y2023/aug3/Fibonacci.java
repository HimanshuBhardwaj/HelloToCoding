package Y2023.aug3;

import java.util.HashMap;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(new FibonacciMemorisationImpl().getFibNum(35));
        System.out.println(new FibonacciBFImpl().getFibNum(35));
        System.out.println(new FibonacciDPImpl().getFibNum(35));
        System.out.println(new FibonacciMatExponentialImpl().getFibNum(35));
    }
}

class FibonacciMatExponentialImpl implements FibonacciI {
    @Override
    public long getFibNum(int n) {
        if (n<=1) {
            return 1;
        }

        int [][] a = {{1,1},{1,0}};
        MatMulI matMul = new MatMul();
        a = matMul.pow(a, n-2);
        return matMul.mul(new int [][]{{1,1}},a)[0][0];
    }
}

class FibonacciDPImpl implements FibonacciI {

    @Override
    public long getFibNum(int n) {
        long [] dp = new long[n+1];
        dp[1] = 1;
        dp[2] = 1;

        for (int i=3;i<=n;i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }

        return dp[n];
    }
}

class FibonacciMemorisationImpl implements FibonacciI {
    HashMap<Integer, Long> memorised = new HashMap<Integer, Long>();

    @Override
    public long getFibNum(int n) {
        if (n==0) {
            return 0;
        }
        if (n<=2) {
            return 1;
        }

        if (memorised.containsKey(n)) {
            return memorised.get(n);
        }

        long n1 =  getFibNum(n-1);
        long n2 = getFibNum(n-2);

        memorised.put(n, n1+n2);

        return n1+n2;
    }
}

class FibonacciBFImpl implements FibonacciI {

    @Override
    public long getFibNum(int n) {
        if (n<=2) {
            return 1;
        }

        return getFibNum(n-1)+ getFibNum(n-2);
    }
}

class MatMul implements MatMulI {

    @Override
    public int[][] mul(int[][] a, int[][] b) {
        int [][] c = new int[a.length][b[0].length];

        for (int i=0;i<c.length;i++) {
            for (int j=0;j<c[0].length;j++) {
                for (int k=0; k<a[0].length;k++) {
                    c[i][j] += a[i][k]*b[k][j];
                }
            }
        }
        return c;
    }

    @Override
    public int[][] pow(int[][] a, int n) {
        if (n==1) {
            return a;
        }
        if (n == 2) {
            return this.mul(a,a);
        }

        int [][]temp = pow(a,n/2);

        if (n % 2 == 0) {
            return this.mul(temp, temp);
        } else {
            return this.mul(this.mul(temp, temp), a);
        }
    }
}

interface MatMulI {
    int [][] mul(int [][] a, int [][] b);
    int [][] pow(int [][] a, int n);
}

interface FibonacciI {
    long getFibNum(int n);
}