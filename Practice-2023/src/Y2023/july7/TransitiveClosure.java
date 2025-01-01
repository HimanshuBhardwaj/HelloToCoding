package Y2023.july7;

public class TransitiveClosure {
    public static void main(String[] args) {
        GraphI uGraphIpl = new UGraphIpl(8);
        uGraphIpl.insert(0,1);
        uGraphIpl.insert(0,2);
        uGraphIpl.insert(1,3);
        uGraphIpl.insert(2,5);
        uGraphIpl.insert(2,4);
        uGraphIpl.insert(6,5);
        uGraphIpl.insert(6,7);

        //uGraphIpl.insert(4,3);

        uGraphIpl.print();
        System.out.println();
        System.out.println(uGraphIpl.hasCycle());

        uGraphIpl.transitiveClosure();
        System.out.println();
        System.out.println();
        uGraphIpl.print();
    }
}

class UGraphIpl implements GraphI {
    int [][] adjMat;
    int numNodes;

    public UGraphIpl(int n) {
        this.numNodes = n;
        adjMat = new int[n][n];
    }

    @Override
    public void insert(int s, int d) {
        adjMat[s][d] = 1;
        adjMat[d][s] = 1;
    }

    @Override
    public void print() {
        for (int i=0;i<numNodes;i++) {
            System.out.print(i+": ");
            for (int j=0;j<numNodes;j++) {
                if (adjMat[i][j] > 0) {
                    System.out.print(j+", ");
                }
            }
            System.out.println();
        }
    }

    @Override
    public int[][] transitiveClosure() {
        int [][] transitiveClosure = adjMat.clone();

        for (int k=0;k<numNodes;k++) {
            for (int i=0;i<numNodes;i++) {
                for (int j=0;j<numNodes;j++) {
                    if (transitiveClosure[i][k] > 0 && transitiveClosure[k][j] > 0) {
                        transitiveClosure[i][j]=1;
                    }
                }
            }
        }
        adjMat = transitiveClosure;
        return transitiveClosure;
    }

    @Override
    public boolean hasCycle() {

        boolean[] visited = new boolean[numNodes];

        for (int i=0;i<numNodes;i++) {
            if(!visited[i] && hasCycleHelper(i, -1, visited)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCycleHelper(int node, int parent, boolean[] visited) {
        if (visited[node]) {
            return true;
        }

        visited[node] = true;

        for (int i=0 ; i<adjMat[node].length ; i++) {
            if (adjMat[node][i] > 0 && i != parent) {
                if (hasCycleHelper(i, node, visited)) {
                    return true;
                }
            }
        }

        return false;
    }
}

interface GraphI {
    void insert(int s, int d);
    void print();
    int [][] transitiveClosure();

    boolean hasCycle();
}
