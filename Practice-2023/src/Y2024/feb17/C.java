package Y2024.feb17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/17/2024
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            Node [] a = new Node[n];
            String [] str = br.readLine().split(" ");

            for (int i=0;i<n;i++) {
                a[i] = new Node(Long.parseLong(str[i]), i);
            }
            pw.append(generateOutput(a)).append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static String generateOutput(Node[] a) {
        RangeMaxValueI rangeMaxValueI = new RangeMaxValueImpl(a);

        TreeSet<Long> treeSet = new TreeSet<>();

        for (int i=0;i<a.length;i++) {
            Value value =  rangeMaxValueI.getMax();
            treeSet.add(value.value);
            rangeMaxValueI.updateDeletion((int)value.index);
        }

        StringBuffer sb = new StringBuffer();

        Iterator<Long> it = treeSet.descendingIterator();
        while (it.hasNext()){
            sb.append(it.next()).append(" ");
        }

        return sb.toString();
    }
}

class Node {
    long v;
    long idx;

    public Node(long v, long idx) {
        this.v = v;
        this.idx = idx;
    }
}


class RangeMaxValueImpl implements RangeMaxValueI {
    Value [] sqrt;
    int numSegMents;
    int segSize;

    int [] segDeleted;

    boolean [] isDeleted;

    Node [] a;

    public RangeMaxValueImpl(Node [] a ) {
        this.segSize = (int)Math.ceil(Math.sqrt(a.length));
        this.numSegMents =  (int)Math.ceil(((double)a.length)/segSize);
        sqrt = new Value[numSegMents];
        isDeleted = new boolean[a.length];
        this.segDeleted = new int[numSegMents];

        for (int i=0;i<a.length;i++) {
            isDeleted[i] = false;
            int si = segNum(i);
            if (sqrt[si] == null) {
                sqrt[si] = new Value();
            }

            if ((a[i].v+a[i].idx+1) > sqrt[si].value) {
                sqrt[si].value = a[i].v+a[i].idx+1;
                sqrt[si].index = i;
            }
        }
        this.a = a;
    }

    int segNum(int i) {
        return i/segSize;
    }

    @Override
    public Value getMax() {
        Value value = new Value();
        value.index = sqrt[0].index;
        value.value = sqrt[0].value;

        for (int i=1;i<sqrt.length;i++) {
            if (sqrt[i].value > value.value) {
                value.value = sqrt[i].value;
                value.index = sqrt[i].index;
            }
        }

        return value;
    }

    @Override
    public void updateDeletion(int index) {
        isDeleted[index] = true;
        int segN = segNum(index);
        int st = getStart(segN);
        int se = getEnd(segN);
        int deleted=0;

        for (int i=0;i<segN;i++) {
            deleted+= segDeleted[i];
        }

        segDeleted[segN]++;
        sqrt[segN].value=0;


        for (int i=Math.max(st,0);i<=Math.min(se,isDeleted.length-1);i++) {
            if (!isDeleted[i]) {
                if (sqrt[segN].value < a[i].v+a[i].idx+1-deleted) {
                    sqrt[segN].value = a[i].v+a[i].idx+1-deleted;
                    sqrt[segN].index = i;
                }
            } else {
                deleted++;
            }
        }

        for (int i=segN+1;i<segDeleted.length;i++) {
            sqrt[i].value--;
            segDeleted[i]++;
        }
    }

    private int getEnd(int segN) {
        return getStart(segN+1)-1;
    }

    private int getStart(int segN) {
        return segN*segSize;
    }
}

interface RangeMaxValueI {
    Value getMax();
    void updateDeletion(int index);
}

class Value {
    long value;
    long index;
}

