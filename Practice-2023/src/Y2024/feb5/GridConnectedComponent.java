package Y2024.feb5;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/5/2024
 */
public class GridConnectedComponent {
    public static void main(String[] args) {
        boolean graph[][] = {{true, true, false, false, true},
                {true, false, false, true, false},
                {false, false, false, false, true},
                {true, true, false, false, false},
                {true,true,false,false,true}};
        GraphConnectedComponent graphConnectedComponent = new GraphOps(graph,5);
        graphConnectedComponent.printConnectedComponent();
    }
}

class GraphOps implements GraphConnectedComponent {
    boolean [][] graph;
    int numNodes;

    public GraphOps(boolean [][] graph, int numNodes) {
        this.graph = graph;
        this.numNodes = numNodes;
    }

    @Override
    public void printConnectedComponent() {
        int [][] connectedComponents = new int[numNodes][numNodes];

        for (int i=0;i<numNodes;i++) {
            for (int j=0;j<numNodes;j++) {
                connectedComponents[i][j] = -1;
            }
        }
        int component=1;

        for (int i=0;i<numNodes;i++) {
            for (int j=0;j<numNodes;j++) {
                if (connectedComponents[i][j]==-1 && graph[i][j]) {
                    connectedComponentHelper(i,j,component++,connectedComponents);
                }
            }
        }

        for (int i=0;i<numNodes;i++) {
            for (int j=0;j<numNodes;j++) {
                System.out.print(connectedComponents[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void connectedComponentHelper(int i, int j, int component, int[][] connectedComponents) {
        if (i < 0 || j<0 || i>=numNodes || j>= numNodes || (!graph[i][j]) || (connectedComponents[i][j] != -1)) {
            return;
        }
        connectedComponents[i][j] = component;
        connectedComponentHelper(i+1,j,component,connectedComponents);
        connectedComponentHelper(i-1,j,component,connectedComponents);
        connectedComponentHelper(i,j+1,component,connectedComponents);
        connectedComponentHelper(i,j-1,component,connectedComponents);
        connectedComponentHelper(i+1,j-1,component,connectedComponents);
        connectedComponentHelper(i+1,j+1,component,connectedComponents);
        connectedComponentHelper(i-1,j-1,component,connectedComponents);
        connectedComponentHelper(i-1,j+1,component,connectedComponents);
    }
}
interface GraphConnectedComponent {
    void printConnectedComponent();
}
