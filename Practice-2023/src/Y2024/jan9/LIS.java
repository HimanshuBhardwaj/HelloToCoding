package Y2024.jan9;

import java.util.*;

/**
 * @author Himanshu Bhardwaj
 * @Date 1/2/2024
 */
public class LIS {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(12);
        list.add(1);
        list.add(14);
        list.add(1);
        list.add(16);
        list.add(1);
        list.add(11);
        list.add(13);


        list.add(1);
//        list.add(-1);
//        list.add(2);
//        list.add(3);
//        list.add(3);
//        list.add(5);
//        list.add(6);
//        list.add(8);

        System.out.println(new LISBF().LongestIncreasingSubsequence(list));
        System.out.println();
        System.out.println(new LISnLogN().LongestIncreasingSubsequence(list));
    }
}

class LISnLogN implements LISI {
    @Override
    public List<Integer> LongestIncreasingSubsequence(ArrayList<Integer> nums) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int maxSize=0;
        LinkedList<Integer> maxSizeL = new LinkedList<>();


        for (int i=0;i<nums.size();i++) {
            Map.Entry<Integer, Integer> entry = treeMap.floorEntry(nums.get(i));

            if (entry == null) {
                treeMap.put(nums.get(i),1);
                maxSize= Math.max(1,maxSize);
            } else {
                    treeMap.put(nums.get(i), 1+entry.getValue());
                    maxSize= Math.max(1+entry.getValue(),maxSize);
            }
        }

        maxSizeL.add(maxSize);
        return maxSizeL;
    }
}

class LISBF implements LISI {
    @Override
    public List<Integer> LongestIncreasingSubsequence(ArrayList<Integer> nums) {
        int [] DP = new int[nums.size()];
        DP[0]=1;
        int maxVIndex=0;
        int maxV=1;

        for (int i=1;i<nums.size();i++) {
            for (int j=i-1;j>=0;j--) {
                if (nums.get(j)<=nums.get(i)) {
                    DP[i] = Math.max( DP[i], 1+DP[j]);
                }
            }

            if (DP[i] > maxV) {
                maxVIndex=i;
                maxV = DP[i];
            }
        }

        LinkedList<Integer> list = new LinkedList<>();


        while (maxVIndex >=0) {
            list.addFirst(nums.get(maxVIndex));

            int j=maxVIndex;
            while (j>=0 && DP[j] != (-1+maxV)) {
                j--;
            }

            if (j>=0) {
                maxV=DP[j];
            }
            maxVIndex=j;
        }


        return list;
    }
}

interface LISI {
    List<Integer> LongestIncreasingSubsequence(ArrayList<Integer> nums);
}