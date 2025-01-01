package dec26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class SQRTDecomposition {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sa = br.readLine();
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        SegmentI segmentTree = new SQRTDecompositionImpl(sa.toCharArray());

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

//6:00 -> 16 mins
class SQRTDecompositionImpl implements SegmentI {
    char [] charArray;
    int sqrtDecomposition[];
    int segSize;

    public SQRTDecompositionImpl(char[] charArray) {
        this.charArray = charArray.clone();
        this.segSize = (int)Math.ceil(Math.sqrt(this.charArray.length));
        this.sqrtDecomposition = new int[(int)Math.ceil(this.charArray.length/(double)segSize)];

        for (int i=0;i<charArray.length;i++) {
            sqrtDecomposition[segNumber(i)] |= getBitRepresentation(charArray[i]);
        }
    }

    int getBitRepresentation(char c) {
        return 1<<(c-'a');
    }

    int segNumber(int index) {
        return index/segSize;
    }

    int indexStart(int segNumber) {
        return segNumber*segSize;
    }

    int indexEnd(int segNumber) {
        return ((segNumber+1)*segSize)-1;
    }


    @Override
    public void replace(int p, char c) {
        charArray[p]=c;

        int segNum = segNumber(p);
        int ss = indexStart(segNum);
        int es = indexEnd(segNum);

        if (ss < 0) {
            ss=0;
        }

        if (es >= charArray.length) {
            es = charArray.length-1;
        }

        sqrtDecomposition[segNum]=0;

        for (int i=ss;i<=es;i++) {
            sqrtDecomposition[segNum] |= getBitRepresentation(charArray[i]);
        }
    }

    @Override
    public int distinctChar(int l, int r) {
        int ss = segNumber(l);
        int se = segNumber(r);

        int setC=0;

        for (int i=l;i<=r && segNumber(i)==ss;i++) {
            setC |= getBitRepresentation(charArray[i]);
        }

        for (int i=r;i>=l && segNumber(i)==se && segNumber(i) != ss;i--) {
            setC |= getBitRepresentation(charArray[i]);
        }

        for (int i=ss+1;i<se;i++) {
            setC |= sqrtDecomposition[i];
        }
        return numberOfSetBits(setC);
    }

    private int numberOfSetBits(int setC) {
        int c=0;

        while (setC > 0) {
            c+= setC&1;
            setC = setC/2;
        }

        return c;
    }
}

interface SegmentI {
    void replace(int p, char c);
    int distinctChar(int l, int r);
}