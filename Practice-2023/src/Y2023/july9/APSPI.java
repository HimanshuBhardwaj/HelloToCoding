package Y2023.july9;

public class APSPI {
    public static void main(String[] args) {
        DGraphI graph = new DGraph(6);
        graph.insert(0,1,13);
        graph.insert(0,2,5);
        graph.insert(1,5,2);
        graph.insert(1,3,2);
        graph.insert(2,5,2);
        graph.insert(2,3,13);
        graph.insert(2,4,4);
        graph.insert(3,4,1);
        graph.insert(5,4,1);
        graph.insert(4,0,1);

        graph.print();
        graph.apsp();
        System.out.println("\n\n\nAPSP");
        graph.print();

    }
}


class DGraph implements DGraphI {
    int [][] adjMat;
    int numNodes;

    int MAX_VALUE = Integer.MAX_VALUE/2;

    public DGraph(int n) {
        this.numNodes = n;
        adjMat = new int[n][n];

        for (int i=0;i<numNodes;i++) {
            for (int j=0; j<numNodes;j++) {
                adjMat[i][j] = MAX_VALUE;
            }
        }
    }

    @Override
    public void insert(int s, int d, int w) {
        adjMat[s][d]=w;
    }

    @Override
    public void print() {
        for (int i=0;i<numNodes;i++) {
            System.out.print(i+": ");
            for (int j=0;j<numNodes;j++) {
                if (adjMat[i][j]!=MAX_VALUE) {
                    System.out.print("("+j+","+adjMat[i][j]+"), ");
                }
            }
            System.out.println();
        }
    }

    @Override
    public void apsp() {
        for (int k=0;k<numNodes;k++) {
            for (int i=0;i<numNodes;i++) {
                for (int j=0;j<numNodes;j++) {
                    if (adjMat[i][k] != MAX_VALUE && adjMat[k][j] != Integer.MAX_VALUE) {
                        adjMat[i][j] = Math.min(adjMat[i][j],adjMat[i][k]+adjMat[k][j]);
                    }
                }
            }
        }
    }
}


interface DGraphI {
    void insert(int s, int d, int w);
    void print();
    void apsp();
}