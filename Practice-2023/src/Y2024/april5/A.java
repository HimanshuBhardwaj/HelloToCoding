package Y2024.april5;

/**
 * @author Himanshu Bhardwaj
 * @Date 4/5/2024
 */
/*
10000
01001
10101

 */
public class A {
    public static void main(String[] args) {
        int [][] grid = {{1,0,0,0,0}, {0,1,0,0,1},{1,0,1,0,1}};
        GraphI graph = new Graph(grid);
        GraphI graph2 = new GraphDecorator(graph);
        System.out.println(graph2.numIslands());
    }
}

class GraphDecorator implements GraphI {
    GraphI graph;

    public GraphDecorator(GraphI graph) {
        this.graph = graph;
    }

    @Override
    public int numIslands() {
        System.out.println("Starting numIslands");
        int numIsland = graph.numIslands();
        System.out.println("Ending numIslands");
        return numIsland;
    }
}

class Graph implements GraphI {

    int [][] grid;

    public Graph(int [][] grid) {
        this.grid = grid;
    }

    public int numIslands() {
        int count=0;
        boolean[][] isVisisted = new boolean[grid.length][grid[0].length];

        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                if (grid[i][j] == 1 && !isVisisted[i][j]) {
                    DFS(i,j,isVisisted);
                    count++;
                }
            }
        }

        return count;
    }

    private void DFS(int i, int j, boolean[][] isVisisted) {
        if (i<0 || j<0 || i>= isVisisted.length || j>= isVisisted[0].length || isVisisted[i][j] || grid[i][j]==0) {
            return;
        }

        isVisisted[i][j]=true;

        DFS(i+1,j, isVisisted);
        DFS(i-1,j, isVisisted);
        DFS(i,j+1, isVisisted);
        DFS(i,j-1, isVisisted);
    }

}

interface GraphI {
    int numIslands();
}