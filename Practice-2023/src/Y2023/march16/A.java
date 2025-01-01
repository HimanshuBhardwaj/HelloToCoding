package Y2023.march16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Himanshu Bhardwaj
 * @Date 3/16/2024
 */
public class A {
    public static void main(String[] args) {
        System.out.println("Hello");
    }


    public static List<Integer> minimalHeaviestSetA(List<Integer> arr) {
        ArrayList<Integer> elementList = new ArrayList<>(arr);
        long totalSum=0l;

        for (int x: elementList) {
            totalSum+=x;
        }

        Collections.sort(elementList, Comparator.reverseOrder());
        List<Integer> collectedItems = new ArrayList<>();
        long collectedW=0l;
        long remainingW=totalSum;

        for (int x:elementList) {
            collectedW+=x;
            remainingW-=x;
            collectedItems.add(x);
            if (collectedW > remainingW) {
             Collections.sort(collectedItems);
             return collectedItems;
            }
        }
        return null;
    }
}
