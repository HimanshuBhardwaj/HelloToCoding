package Y2024.jan10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.swap;

/**
 * @author Himanshu Bhardwaj
 * @Date 1/10/2024
 */
public class AllPermutuation {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(0);
        list.add(0);
        new GenePWD().generatePermuruation(list);
    }
}

class GenePWD implements GeneratePermutuation {

    @Override
    public void generatePermuruation(ArrayList<Integer> list) {
        Collections.sort(list);
        generatePermuruationHelper(0,list);
    }

    private void generatePermuruationHelper(int index, ArrayList<Integer> list) {
        if (index==list.size()) {
            System.out.println(list);
            return;
        }

        for (int j=index;j<list.size();j++) {
            if (isLastOccurence(list.get(j),j,list)) {
                Collections.swap(list, index, j);
                generatePermuruationHelper(index + 1, list);
                Collections.swap(list, index, j);
            }
        }
    }

    private boolean isfirstOccurence(Integer integer, int j, ArrayList<Integer> list) {
        for (int i=j-1;i>=0;i--) {
            if (Objects.equals(list.get(i), integer)) {
                return false;
            }
        }
        return true;
    }

    private boolean isLastOccurence(Integer integer, int j, ArrayList<Integer> list) {
        for (int i=j+1;i<list.size();i++) {
            if (list.get(i)==integer) {
                return false;
            }
        }
        return true;
    }
}

interface GeneratePermutuation {
    void generatePermuruation(ArrayList<Integer> list);
}
