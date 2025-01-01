package Y2023.march16;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Himanshu Bhardwaj
 * @Date 3/16/2024
 */
public class C {
    public static void main(String[] args) {
        List<Integer> blocks = new ArrayList<>();
        blocks.add(2);
        blocks.add(4);
        blocks.add(3);
        blocks.add(1);
        blocks.add(6);

        System.out.println(getMinNumMoves(blocks));

    }

    public static int getMinNumMoves(List<Integer> blocks) {
        ArrayList<Integer> blockAL = new ArrayList<>(blocks);
        int psE = smallestPos(blockAL);
        int pLE = largestPos(blockAL);

        if (psE < pLE) {
            return psE+blockAL.size()-1-pLE;
        } else {
            return psE+blockAL.size()-2-pLE;
        }
    }

    private static int largestPos(ArrayList<Integer> blockAL) {
        int pos=0;
        int e=blockAL.get(0);

        for (int i=1;i<blockAL.size();i++) {
            if (e < blockAL.get(i)) {
                e = blockAL.get(i);
                pos = i;
            }
        }
        return pos;
    }

    private static int smallestPos(ArrayList<Integer> blockAL) {
        int pos=0;
        int e=blockAL.get(0);

        for (int i=1;i<blockAL.size();i++) {
            if (e > blockAL.get(i)) {
                e = blockAL.get(i);
                pos = i;
            }
        }
        return pos;
    }
}
