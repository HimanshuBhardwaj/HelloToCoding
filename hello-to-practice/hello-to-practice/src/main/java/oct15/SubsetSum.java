package oct15;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SubsetSum {
    public static void main(String[] args) {
        ArrayList<Integer> set = new ArrayList<>();
        set.add(2);
        set.add(2);
        set.add(5);
        set.add(9);
        set.add(2);
        set.add(2);

        SubsetSum2 subsetSum2 = new SubSetSumBruteforce(set);
        SubsetSum2 subsetSum1 = new SubSetSumDP(set);
        for (int i=0;i<=23;i++) {
            System.out.println("Sum: "+i+"\t"+subsetSum2.subsetSum(i)+"\t"+subsetSum1.subsetSum(i));
        }
    }
}

class SubSetSumDP implements  SubsetSum2 {
    private ArrayList<Integer> set;

    public SubSetSumDP(List<Integer> set)
    {
        this.set = new ArrayList<>(set);
        System.out.println("Current Set:\t"+ set);
    }

    @Override
    public List<Integer> subsetSum(int sum) {
        boolean [][] dp = new boolean[set.size()][sum+1];
        dp[0][0] = true;
        dp[0][(sum <= set.get(0))?0:set.get(0)] = true;

        for (int i=1;i<set.size();i++) {
            for (int j=0;j<=sum;j++) {
                if (j>= set.get(i)) {
                    dp[i][j] = dp[i-1][j] || ((i>0)?(dp[i-1][j-set.get(i)]):false);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        List<Integer> list =  new LinkedList<>();
        list.add(dp[set.size()-1][sum]?1:0);

        return list;
    }
}

class SubSetSumBruteforce implements  SubsetSum2 {
    private ArrayList<Integer> set;

    public SubSetSumBruteforce(List<Integer> set)
    {
        this.set = new ArrayList<>(set);
        System.out.println("Current Set:\t"+ set);
    }

    @Override
    public List<Integer> subsetSum(int sum) {
        return subSetSumHelper(0,0,sum, new LinkedList<>());
    }

    private LinkedList<Integer> subSetSumHelper(int endIndex, int currentSum, int sum, LinkedList<Integer> subset) {
        if(sum == currentSum) {
            return subset;
        }

        if (currentSum < 0 || endIndex==set.size()) {
            return null;
        }

        subset.addLast(set.get(endIndex));
        currentSum=currentSum+set.get(endIndex);
        LinkedList<Integer> returnedSubset = subSetSumHelper(endIndex+1, currentSum, sum, subset);
        if (returnedSubset != null) {
            return returnedSubset;
        }

        int lastElement = subset.removeLast();
        currentSum -= lastElement;

        returnedSubset = subSetSumHelper(endIndex+1, currentSum, sum, subset);
        if (returnedSubset != null) {
            return returnedSubset;
        }
        return null;
    }
}

interface SubsetSum2 {
    List<Integer> subsetSum(int sum);
}
