package Y2024.feb4;

import java.util.ArrayList;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/4/2024
 */
public class RangeMaximumQuery {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(10);
        list.add(-10);
        list.add(100);
        list.add(20);

        RMQ rmq = new RMQSQRTDecomposition(list);
        System.out.println();
        //System.out.println(rmq.max(0,1));
        System.out.println(rmq.max(1,2));
        //System.out.println(rmq.max(1,1));

    }
}

class RMQSQRTDecomposition implements RMQ {
    ArrayList<Integer> array;
    int segSize;

    ArrayList<Integer> segMax;


    public RMQSQRTDecomposition(ArrayList<Integer> array) {
        this.array = new ArrayList<>(array);
        segSize = (int)Math.ceil(Math.sqrt(array.size()));
        segMax = new ArrayList<>();

        for (int i=0;i<Math.ceilDiv(array.size(),segSize);i++) {
            segMax.add(Integer.MIN_VALUE);
        }

        for (int i=0;i<array.size();i++) {
            int seg = getSegment(i);
            int max = Math.max(array.get(i),segMax.get(seg));
            segMax.set(seg,max);
        }
    }

    int getSegment(int index) {
        return index/segSize;
    }

    @Override
    public int max(int l, int r) {
        int max = Integer.MIN_VALUE;

        int startSeg = getSegment(l)+1;
        int endSeg = getSegment(r)-1;

        for (int i=startSeg;i<=endSeg;i++) {
            max = Math.max(segMax.get(i), max);
        }

        for (int i=l; i< array.size() && (getSegment(i) != startSeg) && i<=r;i++) {
            max = Math.max(array.get(i), max);
        }

        for (int i=r;  (i>= 0) &&(getSegment(i) != endSeg) && i>=l;i--) {
            max = Math.max(array.get(i), max);
        }

        return max;
    }
}

interface RMQ {
    int max(int l, int r);
}
