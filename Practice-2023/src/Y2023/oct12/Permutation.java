package Y2023.oct12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Himanshu Bhardwaj
 * @Date 10/12/2023
 */
public class Permutation {
    public static void main(String[] args) {
        PermutationI<Integer> permutationI = new PermutationImpl<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        //list.add(5);

        PermutationI<String> permutationS = new PermutationImpl<>();
        ArrayList<String> listS = new ArrayList<>();
        listS.add("Himnshu");
        listS.add("Bhardwaj");
        listS.add("is");
        listS.add("a");
        listS.add("great");

        permutationS.generatePermutation(listS);
    }
}

class PermutationImpl<T> implements PermutationI<T> {
    int count=0;

    @Override
    public void generatePermutation(List<T> numbers) {
        T[] numberArray = (T[])numbers.toArray();

        for (int i=0;i<numbers.size();i++) {
            numberArray[i] = numbers.get(i);
        }


        generatePermutationHelper(0, numberArray);
    }

    private void generatePermutationHelper(int index, T[] numbers) {
        if (index >= numbers.length) {
            System.out.print(++count+":\t");
            for (int i=0;i<numbers.length;i++) {
                System.out.print(numbers[i]+", ");
            }
            System.out.println();
            return;
        }

        for (int i=index;i<numbers.length;i++) {
            swap(index,i,numbers);
            generatePermutationHelper(index+1, numbers);
            swap(index,i,numbers);
        }
    }

    private void swap(int i1, int i2, T[] numbers) {
        T temp = numbers[i1];
        numbers[i1] = numbers[i2];
        numbers[i2] = temp;
    }
}

interface PermutationI<T> {
    void generatePermutation(List<T> numbers);
}
