package Y2023.aug3.dec25;

import java.util.*;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/25/2023
 */
public class SubsetSum {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        SubsetSumDP subsetSumBF = new SubsetSumDP(list);

        List<Integer> ss =  subsetSumBF.subsetSum(10);

        System.out.println(ss);

    }
}

class SubsetSumDP implements SubsetSumI {

    ArrayList<Integer> list;
    int n;

    public SubsetSumDP(List<Integer> list) {
        this.list = new ArrayList<>(list);
        this.n = list.size();
    }

    @Override
    public List<Integer> subsetSum(int sum) {

        boolean [][] DP = new boolean[n][sum+1];
        DP[0][0] = true;

        if (list.get(0) <= sum) {
            DP[0][list.get(0)] = true;
        }

        for (int i=1;i<n;i++) {
            for (int j=0;j<=sum;j++) {
                DP[i][j] = DP[i-1][j];

                if ((j-list.get(i) >=0)) {
                    DP[i][j]  = DP[i][j]  || DP[i-1][j-list.get(i)];
                }
            }
        }

        if (DP[n-1][sum]) {
            return generateList(DP,sum);
        }

        return null;
    }

    private List<Integer> generateList(boolean[][] dp, int sum) {
        List<Integer> subset = new ArrayList<>();

        for (int i=dp.length-1;i>=0;i--) {
            for (int j=sum;j>=0;j--) {
                if (dp[i][j]==true && j>=this.list.get(i) && dp[i][j-list.get(i)]==true) {
                    subset.add(list.get(i));
                    sum=sum-list.get(i);
                    break;
                }
            }
        }
        return subset;
    }
}



class SubsetSumBF implements SubsetSumI {
    ArrayList<Integer> list;
    int n;

    HashMap<String, Boolean> caching;

    public SubsetSumBF(List<Integer> list) {
        this.list = new ArrayList<>(list);
        this.n = list.size();
        this.caching = new HashMap<>();
    }

    @Override
    public List<Integer> subsetSum(int sum) {
        LinkedList<Integer> subsetSum = new LinkedList<>();
        boolean isPossible = subsetSumInternal(0, sum, subsetSum);

        System.out.println("isPossible: "+isPossible);

        if (isPossible) {
            return subsetSum;
        } else {
            return null;
        }
    }

    private boolean subsetSumInternal(int index, int sum, LinkedList<Integer> subsetSum) {
        if (sum==0) {
            return true;
        }

        if (sum < 0 || index >= n) {
            return false;
        }

        if (caching.containsKey(index+"|"+sum)) {
            System.out.println("Cache hit: "+index+"|"+sum);
            return caching.get(index+"|"+sum);
        }

        boolean isPossible = false;

        for (int i=index;i<n && !isPossible;i++) {
            subsetSum.addLast(list.get(i));
            if (subsetSumInternal(i+1,sum-list.get(i),subsetSum)) {
                isPossible = true;
            } else {
                subsetSum.removeLast();
                isPossible = subsetSumInternal(i+1, sum, subsetSum);
            }
        }

        caching.put(index+"|"+sum, isPossible);

        return isPossible;
    }
}

interface SubsetSumI {
    List<Integer> subsetSum(int sum);
}