package dec25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SegmentTree {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sa = br.readLine();
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        SegmentI segmentTree = new SegmentTreeImpl(sa.toCharArray());

        for (int i=0;i<n;i++) {
            String [] str = br.readLine().split(" ");

            if ("1".equals(str[0])) {
                segmentTree.replace(Integer.parseInt(str[1])-1,str[2].charAt(0));
            } else {
                pw.append(segmentTree.distinctChar(Integer.parseInt(str[1])-1, Integer.parseInt(str[2])-1)+"\n");
            }
        }

        pw.flush();
        pw.close();
    }
}

class SegmentTreeImpl implements SegmentI {
    char [] sa;
    int segTreSize;

    int [] segTree;

    public SegmentTreeImpl(char [] sa) {
        this.sa = sa;
        segTreSize = (int)Math.pow(2,Math.ceil(Math.log(sa.length)/Math.log(2)));
        segTreSize = (segTreSize*2)-1;
        segTree = new int[segTreSize];

        for (int i=segTreSize/2;i<segTreSize;i++) {
            if ((i- (segTreSize/2)) < sa.length) {
                segTree[i]=setBit(sa[i- (segTreSize/2)]-'a');
            }
        }

        for (int i= segTreSize/2 - 1;i>=0;i--) {
            int lp = 2*i+1;
            int rp = 2*i+2;
            segTree[i]=segTree[lp]|segTree[rp];
        }
    }

    @Override
    public void replace(int p, char c) {
        int pS = segTreSize/2+p;
        segTree[pS] = setBit(c-'a');

        while (pS>=0) {
            pS = (pS-1)/2;
            int lp = 2*pS+1;
            int rp = 2*pS+2;
            if (rp < segTree.length) {
                segTree[pS]=segTree[lp]|segTree[rp];
            }
            if (pS==0) {
                break;
            }
        }
    }

    private Integer setBit(int i) {
        return 1<<(i);
    }

    @Override
    public int distinctChar(int l, int r) {
        return countsetBit(distinctCharHelper(0,0,segTreSize/2,l,r));
    }

    private int countsetBit(int distinctCharHelper) {
        int c=0;
        while (distinctCharHelper>0) {
            c+=distinctCharHelper&1;
            distinctCharHelper = distinctCharHelper>>1;
        }
        return c;
    }

    private Integer distinctCharHelper(int index, int ss, int se, int s, int e) {
        if (ss<0 || se>(segTreSize/2) || index <0 || index >= segTree.length || ss>se || ss>e || se < s) {
            return 0;
        }

        if (ss>=s && se<=e) {
            return segTree[index];
        }

        int mid = (ss+se)/2;
        return distinctCharHelper(2*index+1,ss,mid,s,e)|distinctCharHelper(2*index+2,mid+1,se,s,e);
    }
}

interface SegmentI {
    void replace(int p, char c);
    int distinctChar(int l, int r);
}