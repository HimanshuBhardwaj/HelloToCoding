package nov3;

public class TC {
    public static void main(String[] args) {
        UGraph uGraph = new UGraph(8);
        uGraph.insert(0,1);
        uGraph.insert(0,3);
        uGraph.insert(1,2);
        uGraph.insert(2,3);

        uGraph.insert(4,5);
        uGraph.insert(4,6);
        uGraph.insert(5,6);
        //uGraph.insert(6,7);
        //uGraph.insert(3,4);
        uGraph.print();
        uGraph.transitiveClosure();
        System.out.println();
        uGraph.print();
    }
}

class UGraph {
    boolean [][] ajMat;
    int numNodes;

    public UGraph(int n) {
        this.numNodes = n;
        ajMat = new boolean[n][n];
    }

    public void insert(int s, int d) {
        ajMat[s][d] = true;
        ajMat[d][s] = true;
    }

    public void print() {
        for (int i=0;i<numNodes;i++) {
            System.out.print(i+": ");
            for (int j=0;j<numNodes;j++) {
                if (ajMat[i][j]) {
                    System.out.print(j+", ");
                }
            }
            System.out.println();
        }
    }

    public void transitiveClosure() {

        for (int i=0;i<numNodes;i++) {
            for (int j=0;j<numNodes;j++) {
                for (int k=0;k<numNodes;k++) {
                    if (ajMat[i][k] && ajMat[k][j]) {
                        ajMat[i][j] = true;
                    }
                }
            }
        }
    }
}
