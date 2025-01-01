package oct24;

public class MatrixExponentiation {
    public static void main(String[] args) {
        FibonacciI fibonacciI = new FibonacciDP();
        System.out.println(fibonacciI.fibonacci(25));
        System.out.println();

        System.out.println("FibonacciBS");
        MatrixOperations matrixOperations = new MatrixOperations();
        FibonacciBS fibonacciBS = new FibonacciBS(matrixOperations);
        System.out.println(fibonacciBS.fibonacci(25));


        /*int [][] a= {{1,0, 0},{0,1, 0}, {0, 0, 2}};

        MatrixOperations matrixOperations = new MatrixOperations();
        MatrixExpoI matrixExpoI = new MatrixMulBruteforce(matrixOperations);

        int [][]b = matrixExpoI.pow(a,50);

        matrixOperations.print(b);

        System.out.println();
        System.out.println("Binary Search\n");
        MatrixExpoI matrixExpoI1 = new MatrixMulBinarySearch(matrixOperations);
        int [][] c = matrixExpoI1.pow(a,50);
        matrixOperations.print(c);
*/
/*        int [][] b = {{3, 4}, {4, 5}, {6, 7}};

        int [][] c = new MatrixOperations().multiply(a, b);

        for (int i=0;i<c.length;i++) {
            for (int j=0;j<c[i].length;j++) {
                System.out.print(c[i][j]+"\t");
            }
            System.out.println();
        }*/
    }
}

class FibonacciBS implements FibonacciI {
    MatrixOperations matrixOperations;
    MatrixMulBinarySearch matrixMulBinarySearch;

    public FibonacciBS(MatrixOperations matrixOperations) {
        this.matrixOperations = matrixOperations;
        this.matrixMulBinarySearch = new MatrixMulBinarySearch(matrixOperations);
    }

    @Override
    public int fibonacci(int n) {
        if (n<0) {
            return -1;
        }
        if (n<2) {
            return 1;
        }

        int [][] coffS = {{1, 1},{1, 0}};
        int [][] coffP = {{1,1}};
        int [][] coffSPN = matrixMulBinarySearch.pow(coffS,n-2);
        int [][] matMul = matrixOperations.multiply(coffP,coffSPN);
        return matMul[0][0];
    }
}


class FibonacciDP implements FibonacciI {

    @Override
    public int fibonacci(int n) {
        if (n==1) {
            return 1;
        }
        if (n==2) {
            return 1;
        }

        int [] fib = new int[n];
        fib[0]=1;
        fib[1]=1;

        for (int i=2;i<n;i++) {
            fib[i] = fib[i-1]+fib[i-2];
        }

        return fib[n-1];
    }
}

interface FibonacciI {
    int fibonacci(int n);
}

class MatrixMulBinarySearch implements MatrixExpoI {
    MatrixOperations matrixOperations;

    public MatrixMulBinarySearch(MatrixOperations matrixOperations) {
        this.matrixOperations = matrixOperations;
    }

    @Override
    public int[][] pow(int[][] a, int n) {
        if (n<=0 || a==null) {
            return null;
        }
        int [][] b = a.clone();

        int [][] c = pow(a,n/2);

        if (c== null) {
            return b;
        }

        b = matrixOperations.multiply(c,c);

        if (n%2 == 0) {
            return b;
        } else {
            return matrixOperations.multiply(b,a);
        }
    }
}

class MatrixMulBruteforce implements MatrixExpoI {
    MatrixOperations matrixOperations;

    public MatrixMulBruteforce(MatrixOperations matrixOperations) {
        this.matrixOperations = matrixOperations;
    }

    @Override
    public int[][] pow(int[][] a, int n) {
        int[][] c;

        c = a.clone();

        for (int i=1;i<n;i++) {
            c = matrixOperations.multiply(c,a);
        }

        return c;
    }
}

interface MatrixExpoI {
    int [][] pow(int [][] a, int n);
}

class MatrixOperations {
    int [][] multiply(int [][] a, int [][] b) {
        if(a==null || b==null
                || a.length==0 || (a[0].length != b.length) ) {
            return null;
        }

        int [][]c = new int[a.length][b[0].length];

        for (int i=0;i<a.length;i++) {
            for (int j=0; j<b[0].length;j++) {
                for (int k=0;k<b.length;k++) {
                    c[i][j] += (a[i][k]*b[k][j]);
                }
            }
        }

        return c;
    }

    public void print(int[][] a) {
        if (a == null) {
            System.out.println("null matrix");
        }
        System.out.println();
        for (int i=0;i<a.length;i++) {
            for (int j=0;j<a[i].length;j++) {
                System.out.print(a[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
