package dec27;

public class NthTerm {
    public static void main(String[] args) {
        int[] f = new int[3];
        f[0]=0;
        f[1]=0;
        f[2]=1;

        System.out.println(new NthtermBF().value(2,1,1,50,f));
        System.out.println(new NthTermMatMul().value(2,1,1,50,f));
    }
}

class NthTermMatMul implements NthTermI {

    @Override
    public long value(int a, int b, int c, int n, int[] f) {
        long [][] coff = {{a,b,c},{1,0,0},{0,1,0}};
        coff = matPow(coff,n-1);
        long [][] prefMat = {{f[2]},{f[1]},{f[0]}};
        return matMul(coff,prefMat)[1][0];
    }

    long [][] matPow(long [][] a, int n) {
        if (n==1) {
            return a;
        }
        if (n==2) {
            return matMul(a,a);
        }

        int min = n/2;

        long [][] tempMat = matPow(a,min);
        tempMat = matMul(tempMat,tempMat);

        if (n%2 ==1) {
            tempMat = matMul(tempMat,a);
        }

        return tempMat;
    }

    long [][] matMul(long [][]a, long [][]b) {
        int n = a.length;
        int m = b[0].length;
        long[][] mul = new long[n][m];

        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                for (int k=0;k<a[0].length;k++) {
                    mul[i][j] += (a[i][k]*b[k][j]);
                }
            }
        }

        return mul;
    }

    void printMat(long [][]a) {
        for (int i=0;i<a.length;i++) {
            for (int j=0;j<a[0].length;j++) {
                System.out.print(a[i][j]+"\t");
            }
            System.out.println();
        }
    }
}

class NthtermBF implements NthTermI {

    @Override
    public long value(int a, int b, int c, int n, int[] f) {
        long val[] = new long[n+1];
        val[0]=f[0];
        val[1]=f[1];
        val[2]=f[2];

        for (int i=3;i<=n;i++) {
            val[i] = (a*val[i-1])+(b*val[i-2])+(c*val[i-3]);
        }

        return val[n];
    }
}

interface NthTermI {
    long value(int a, int b, int c, int n, int []f);
}
