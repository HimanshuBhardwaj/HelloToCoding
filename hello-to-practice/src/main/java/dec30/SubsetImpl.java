package dec30;

import java.util.*;

public class SubsetImpl {
    public static void main(String[] args) {
        Subset subset = new SubsetWithOutRepetition();
        ArrayList<Integer> set = new ArrayList<>();
        set.add(0);
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(3);

        List<List<Integer>> sets = subset.allSubSets(set);
        System.out.println(sets.size());
        for (List<Integer> se: sets) {
            System.out.println(se);
        }
    }
}

// 8:17 - 8:40
class SubsetWithOutRepetition implements Subset {

    @Override
    public List<List<Integer>> allSubSets(List<Integer> set) {
        int[] mapping = getMapping(set);
        int [] mapArray = getMapArray(mapping, set);
        List<List<Integer>> list = new ArrayList<>();

        getMapping(0,mapArray,new int[mapArray.length],list,mapping);

        return list;
    }

    private int[] getMapArray(int[] mapping, List<Integer> set) {
        int [] ma = new int[mapping.length];

        for (int e: set) {
            int pos = findPos(mapping,e);
            ma[pos]++;
        }
        return ma;
    }

    private int findPos(int[] mapping, int e) {
        for (int i=0;i<mapping.length;i++) {
            if (mapping[i]==e) {
                return i;
            }
        }
        return -1;
    }

    private void getMapping(int index, int[] mapArray, int[] currentSubset, List<List<Integer>> list, int[] mapping) {
        if (index == currentSubset.length) {
            ArrayList<Integer> subset = new ArrayList<>();

            for (int i=0;i<currentSubset.length;i++) {
                for (int j=0;j<currentSubset[i];j++) {
                    subset.add(mapping[i]);
                }
            }

            list.add(subset);
            return;
        }

        for (int i=0;i<=mapArray[index];i++) {
            currentSubset[index]=i;
            getMapping(index+1, mapArray, currentSubset, list, mapping);
        }
    }

    private int[] getMapping(List<Integer> set) {
        TreeSet<Integer> treeSet = new TreeSet<>(set);
        int [] mapping = new int[treeSet.size()];
        int pos=0;
        for (int k: treeSet) {
            mapping[pos++]=k;
        }
        return mapping;
    }
}


// 8:10 - 8:16
class SubSetWitRepetition implements Subset {

    @Override
    public List<List<Integer>> allSubSets(List<Integer> set) {
        List<List<Integer>> allSubsets = new ArrayList<>();
        allSubsetHelper(0,new ArrayList<>(set), new LinkedList<>(), allSubsets);
        return allSubsets;
    }

    private void allSubsetHelper(int pos, ArrayList<Integer> integers,LinkedList<Integer> currentSubset, List<List<Integer>> subSet) {
        if (pos == integers.size()) {
            subSet.add(new ArrayList<>(currentSubset));
            return;
        }

        currentSubset.addLast(integers.get(pos));
        allSubsetHelper(pos+1,integers,currentSubset,subSet);
        currentSubset.removeLast();
        allSubsetHelper(pos+1, integers, currentSubset, subSet);
    }
}


interface Subset{
    List<List<Integer>> allSubSets(List<Integer> set);
}