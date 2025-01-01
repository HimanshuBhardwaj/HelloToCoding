package Y2023.oct2023;

import java.io.IOException;
import java.util.TreeSet;

public class A {
    public static void main(String[] args) throws IOException {
        int [][] ma = {{0,1,1,1},{0,0,0,0}, {0,0,0,0}};
        System.out.println(shortestDistanceDP(ma,5));
        System.out.println(shortestDistanceBFS(ma,5));
    }

    static int shortestDistanceBFS(int [][]ma, int k1) {
        int r = ma.length;
        int c = ma[0].length;

        TreeSet<Index> treeSet = new TreeSet<>();
        Index[][] indices = new Index[r][c];

        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                indices[i][j] = new Index(i, j);
                treeSet.add(indices[i][j]);
            }
        }

        Index last = indices[r-1][c-1];
        treeSet.remove(last);
        last.sd = 0;
        treeSet.add(last);

        while (!treeSet.isEmpty()) {
            Index node = treeSet.first();
            treeSet.remove(node);

            if (node.r == 0 && node.c==0) {
                return (int)node.sd;
            }

            int tr = node.r;
            int tc = node.c;

            for (int k=1;k<=k1;k++) {
                if (!validPos(ma,tr,tc+k) || (ma[tr][tc+k]==1)) {
                    break;
                }

                Index next = indices[tr][tc+k];
                treeSet.remove(next);
                next.sd = Math.min(next.sd, node.sd+1);
                treeSet.add(next);
            }

            for (int k=1;k<=k1;k++) {
                if (!validPos(ma,tr+k,tc) || (ma[tr+k][tc]==1)) {
                    break;
                }

                Index next = indices[tr+k][tc];

                treeSet.remove(next);
                next.sd = Math.min(next.sd, node.sd+1);
                treeSet.add(next);
            }
        }
//
        return (int)indices[0][0].sd;
    }


    static class Index implements Comparable<Index> {
        int r;
        int c;
        long sd;

        public Index(int r, int c) {
            this.r = r;
            this.c = c;
            this.sd = Integer.MAX_VALUE;
        }

        @Override
        public int compareTo(Index o) {
            if (this.sd == o.sd) {
                if (this.r == o.r) {
                    return -1*Integer.compare(this.c, o.c);
                }
                else
                {
                    return -1*Integer.compare(this.r, o.r);
                }
            }
            else
            {
                return Long.compare(this.sd, o.sd);
            }
        }
    }

    static int shortestDistanceDP(int [][]ma, int k1) {
        int sd [][] = new int[ma.length][ma[0].length];
        int r = ma.length;
        int c = ma[0].length;


        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                sd[i][j] = Integer.MAX_VALUE;
            }
        }
        sd[r-1][c-1]=0;


        for (int i=r-1;i>=0;i--) {
            for (int j=c-1;j>=0;j--) {
                if (ma[i][j] != 1) {
                    for (int k = 1; k <= k1; k++) {
                        if (validPos(ma, i, j + k) && sd[i][j + k] != Integer.MAX_VALUE && ma[i][j+k] != 1) {
                            sd[i][j] = Math.min(sd[i][j + k] + 1, sd[i][j]);
                        }

                        if ( validPos(ma, i, j + k)  && ma[i][j+k] ==1) {
                            break;
                        }
                    }

                    for (int k = 1; k <= k1; k++) {
                        if (validPos(ma, i+k, j ) && ma[i+k][j] == 1) {
                            break;
                        }

                        if (validPos(ma, i+k, j ) && sd[i+k][j] != Integer.MAX_VALUE) {
                            sd[i][j] = Math.min(sd[i+k][j]+1,sd[i][j]);
                        }

                        if (validPos(ma, i+k, j)  && ma[i+k][j] ==1) {
                            break;
                        }
                    }
                }
            }
        }
        return sd[0][0];
    }

    private static boolean validPos(int[][] ma, int r, int c) {
        return r>=0 && r<ma.length && c>=0 && c< ma[0].length;
    }
}