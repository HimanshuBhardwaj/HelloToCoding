package Y2024.jan26;

/**
 * @author Himanshu Bhardwaj
 * @Date 1/26/2024
 */
public class RMQPOCs {
    public static void main(String[] args) {
        int [] arr =  {-1,2,-3,2,-5,11,10};
        RMQ rmq = new RMQSegmentTree(arr);
        rmq.updateElement(0,30);
        rmq.updateElement(1,30);
        rmq.updateElement(2,30);
        rmq.updateElement(3,30);
        rmq.updateElement(4,30);
        rmq.updateElement(5,30);
        //rmq.updateElement(6,30);
        System.out.println(rmq.minimumRange(0,6));
        rmq.print();
    }
}

class RMQSegmentTree implements RMQ {
    int [] arr;
    int segTSize;
    int segTree [];

    public RMQSegmentTree(int [] arr) {
        this.arr = arr;
        segTSize = (int)Math.pow(2,Math.ceil(Math.log(arr.length)/Math.log(2)));
        segTSize = segTSize+segTSize-1;
        segTree = new int[segTSize];

        for (int i=0;i<=segTSize/2;i++) {
            if (i<arr.length) {
                segTree[i+(segTSize/2)] = arr[i];
            } else {
                segTree[i+(segTSize/2)] = Integer.MAX_VALUE;
            }
        }

        for (int i=(segTSize/2)-1;i>=0;i--) {
            int lp = 2*i+1;
            int rp = 2*i+2;
            segTree[i] = Math.min(segTree[lp],segTree[rp]);
        }

        for (int x: segTree) {
            System.out.print(x+", ");
        }
        System.out.println();
    }

    @Override
    public int minimumRange(int s, int e) {
        return minimumRangeHelper(0,0,segTSize/2,s,e);
    }

    @Override
    public void updateElement(int i, int v) {
        int si = segTSize/2+i;
        segTree[si] = v;
        while (si>0) {
            si = (si-1)/2;
            int l = (2*si)+1;
            int r = (2*si)+2;
            segTree[si] = Math.min(segTree[l], segTree[r]);
        }
    }

    @Override
    public void print() {
        for (int x:segTree) {
            System.out.print(x+", ");
        }
        System.out.println();
    }

    private int minimumRangeHelper(int in, int ss, int se, int s, int e) {
        if (ss>=s && se<=e) {
            return segTree[in];
        }

        if (ss==se || ss>e || se<s || in < 0 || in>=segTSize) {
            return Integer.MAX_VALUE;
        }

        int lm = minimumRangeHelper(2*in+1,ss,(ss+se)/2,s,e);
        int rm = minimumRangeHelper(2*in+2,((ss+se)/2)+1,se,s,e);

        return Math.min(lm,rm);
    }
}

class RMQBF implements RMQ {
    int [] arr;

    public RMQBF(int [] arr) {
        this.arr = arr;
    }

    @Override
    public int minimumRange(int s, int e) {
        int min = arr[s];
        for (int i=s+1;i<=e;i++) {
            min = Math.min(arr[i],min);
        }
        return min;
    }

    @Override
    public void updateElement(int i, int v) {
        arr[i] = v;
    }

    @Override
    public void print() {
        for (int x:arr) {
            System.out.print(x+",");
        }
        System.out.println();
    }
}

interface RMQ {
    int minimumRange(int s, int e);
    void updateElement(int i, int v);

    void print();
}
