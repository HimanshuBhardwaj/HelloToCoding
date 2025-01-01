package Y2023.oct12;

import java.sql.Array;
import java.util.*;

/**
 * @author Himanshu Bhardwaj
 * @Date 10/12/2023
 */
public class Subset {
    public static void main(String[] args) {
        ArrayList<Integer> set = new ArrayList<>();
        set.add(0);
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(2);
        // set.add(3);
        // set.add(4);
        // set.add(5);

        System.out.println(new UniqueueSubsetUniqueElements().subsetUniqueElements(set));
        System.out.println(new UniqueueSubsetUniqueElements().subsetUniqueElements(set).size());
        System.out.println("------------------------------------------------------------------------");
        System.out.println(new UniqueueSubsetDuplicateElements().subsetUniqueElements(set));
        System.out.println(new UniqueueSubsetDuplicateElements().subsetUniqueElements(set).size());
    }
}

class UniqueueSubsetDuplicateElements implements SubsetInterface {

    @Override
    public ArrayList<ArrayList<Integer>> subsetUniqueElements(List<Integer> numbers) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        for (int n: numbers) {
            if (!frequencyMap.containsKey(n)) {
                frequencyMap.put(n,0);
            }
            frequencyMap.put(n, frequencyMap.get(n)+1);
        }

        int [] maxFre = new int[frequencyMap.size()];

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            maxFre[entry.getKey()] = entry.getValue();
        }

        ArrayList<ArrayList<Integer>> listOfSubsets = new ArrayList<>();

        subsetUniqueElementsHelper(new LinkedList<>(),0, new ArrayList<>(frequencyMap.keySet()), maxFre, listOfSubsets);

        return listOfSubsets;
    }

    private void subsetUniqueElementsHelper(LinkedList<Integer> activeList, int index, List<Integer> numbers, int[] maxFre,  ArrayList<ArrayList<Integer>> listOfSubsets) {
        if (index == numbers.size()) {
            listOfSubsets.add(new ArrayList<>(activeList));
            return;
        }
        subsetUniqueElementsHelper(activeList,index+1, numbers, maxFre, listOfSubsets);
        for (int i=0;i<maxFre[index];i++) {
            activeList.addLast(numbers.get(index));
            subsetUniqueElementsHelper(activeList,index+1, numbers, maxFre, listOfSubsets);
        }

        for (int i=0;i<maxFre[index];i++) {
            activeList.removeLast();
        }
    }
}

class UniqueueSubsetUniqueElements implements SubsetInterface {
    @Override
    public ArrayList<ArrayList<Integer>> subsetUniqueElements(List<Integer> numbers) {
        long size = (long)Math.pow(2,numbers.size());
        ArrayList<ArrayList<Integer>> subsets = new ArrayList<>();
        ArrayList<Integer> numbersArrayList = new ArrayList<>(numbers);

        for (int i=0;i<size;i++) {
            subsets.add(getSubset(i,numbersArrayList));
        }

        return subsets;
    }

    private ArrayList<Integer> getSubset(int index, ArrayList<Integer> numbers) {
        ArrayList<Integer> returnedSet = new ArrayList<>();
        int numBytes = (int)Math.ceil(Math.log(index)/Math.log(2));

        for (int i=0;i<=numBytes;i++) {
            if ((1<<i & index) != 0 && i<numbers.size() ) {
                returnedSet.add(numbers.get(i));
            }
        }
        return returnedSet;
    }
}

interface SubsetInterface {
    ArrayList<ArrayList<Integer>> subsetUniqueElements(List<Integer> numbers);
}