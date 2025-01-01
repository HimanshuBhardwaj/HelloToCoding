package nov4;

public class APSP {
    public static void main(String[] args) {
        Graph graph = new Graph(5);

        graph.insert(0,1,1);
        graph.insert(0,3,3);
        graph.insert(1,2,2);
        graph.insert(2,3,2);
        graph.insert(2,4,1);
        graph.insert(3,4,4);

        graph.print();
        graph.APSP();
        System.out.println();
        graph.print();
    }
}

class Graph {
    int [][] adjMat;
    int n;

    public Graph(int n) {
        this.n = n;
        adjMat = new int[n][n];
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                adjMat[i][j] = Integer.MAX_VALUE/3;
            }
        }
    }

    public void insert(int s, int d, int w) {
        adjMat[s][d] = w;
        adjMat[d][s] = w;
    }

    public void print() {
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                System.out.print(adjMat[i][j]+", ");
            }
            System.out.println();
        }
    }

    public void APSP() {
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                for (int k=0;k<n;k++) {
                    adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k]+adjMat[k][j]);
                }
            }
        }
    }
}