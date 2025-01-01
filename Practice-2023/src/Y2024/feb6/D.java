package Y2024.feb6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/6/2024
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String [] str = br.readLine().split(" ");
            int [] arr = new int[str.length];
            for (int i=0;i<str.length;i++) {
                arr[i] = Integer.parseInt(str[i]);
            }

            MinMax minMax = new MinMaxSegmentTree(arr);
            int q = Integer.parseInt(br.readLine());
            for (int i=0;i<q;i++) {
                str = br.readLine().split(" ");
                int l = Integer.parseInt(str[0])-1;
                int r = Integer.parseInt(str[1])-1;
                Info minInfo = minMax.getMin(l,r);
                Info maxInfo = minMax.getMax(l,r);

                if (minInfo.element == maxInfo.element) {
                    pw.append("-1 -1").append("\n");
                } else {
                    pw.append((minInfo.index+1)+" "+(maxInfo.index+1)).append("\n");
                }
            }
            pw.append("\n");
        }
        pw.flush();
        pw.close();
    }
}

class Info {
    int element;
    int index;
}

class MinMaxSegmentTree implements MinMax {
    int [] arr;
    int numE;
    Info [] maxInfo;
    Info [] minInfo;
    int segSize;

    public MinMaxSegmentTree(int [] arr) {
        numE = arr.length;
        this.arr = arr;
        segSize = (int)Math.ceil(Math.sqrt(numE));
        //int numSeg = Math.ceilDiv(numE,segSize);
        int numSeg = (int)Math.ceil(((double)numE)/segSize);
        maxInfo = new Info[numSeg];
        minInfo = new Info[numSeg];

        for (int i=0;i<numE;i++) {
            int segNum = getSegNum(i);
            if (maxInfo[segNum] == null) {
                maxInfo[segNum] = new Info();
                minInfo[segNum] = new Info();
            }

            if (minInfo[segNum].element==0) {
                minInfo[segNum].element = arr[i];
                minInfo[segNum].index = i;
            } else {
                if (minInfo[segNum].element > arr[i]) {
                    minInfo[segNum].element = arr[i];
                    minInfo[segNum].index = i;
                }
            }

            if (maxInfo[segNum].element==0) {
                maxInfo[segNum].element = arr[i];
                maxInfo[segNum].index = i;
            } else {
                if (maxInfo[segNum].element < arr[i]) {
                    maxInfo[segNum].element = arr[i];
                    maxInfo[segNum].index = i;
                }
            }
        }
    }

    int getSegNum(int index) {
        return index/segSize;
    }


    @Override
    public Info getMin(int l, int r) {
        int startSeg = getSegNum(l)+1;
        int endSeg = getSegNum(r)-1;

        Info mI = null;

        for (int i=startSeg;i<=endSeg;i++) {
            if (mI==null) {
                mI = minInfo[i];
            } else {
                if (mI.element > minInfo[i].element) {
                    mI = minInfo[i];
                }
            }
        }

        for (int i=l;i<=r && i< arr.length && getSegNum(i)<startSeg;i++) {
            if (mI == null) {
                mI = new Info();
                mI.element = arr[i];
                mI.index = i;
            } else if (arr[i] < mI.element) {
                mI = new Info();
                mI.element = arr[i];
                mI.index = i;
            }
        }

        for (int i=r;i>=l && i>=0 && getSegNum(i)>endSeg;i--) {
            if (mI == null) {
                mI = new Info();
                mI.element = arr[i];
                mI.index = i;
            }
            else if (arr[i] < mI.element) {
                mI = new Info();
                mI.element = arr[i];
                mI.index = i;
            }
        }


        return mI;
    }

    @Override
    public Info getMax(int l, int r) {
        int startSeg = getSegNum(l)+1;
        int endSeg = getSegNum(r)-1;

        Info mI = null;

        for (int i=startSeg;i<=endSeg;i++) {
            if (mI==null) {
                mI = maxInfo[i];
            } else {
                if (mI.element < maxInfo[i].element) {
                    mI = maxInfo[i];
                }
            }
        }

        for (int i=l;i<=r && i< arr.length && getSegNum(i)<startSeg;i++) {
            if (mI == null) {
                mI = new Info();
                mI.element = arr[i];
                mI.index = i;
            } else if (arr[i] > mI.element) {
                mI = new Info();
                mI.element = arr[i];
                mI.index = i;
            }
        }

        for (int i=r;i>=l && i>=0 && getSegNum(i)>endSeg;i--) {
            if (mI == null) {
                mI = new Info();
                mI.element = arr[i];
                mI.index = i;
            } else if (arr[i] > mI.element) {
                mI = new Info();
                mI.element = arr[i];
                mI.index = i;
            }
        }

        return mI;
    }
}

interface MinMax {
    Info getMin(int l, int r);
    Info getMax(int l, int r);
}