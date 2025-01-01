package Y2024.Jan11;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Himanshu Bhardwaj
 * @Date 1/11/2024
 */
public class AllPermutuation {
    public static void main(String[] args) {
        AllPermutuation allPermutuation = new AllPermutuation();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(0);
        Collections.sort(list);
        allPermutuation.allPermutuation(0,list);
    }

    void allPermutuation(int index, ArrayList<Integer> num) {
        if (index>=num.size()) {
            System.out.println(num);
        }

        for (int i=index;i<num.size();i++) {
            if(lastOccurence(num.get(i),i,num)){
                Collections.swap(num, index, i);
                allPermutuation(index + 1, num);
                Collections.swap(num, index, i);
            }
        }
    }

    private boolean lastOccurence(int index, int nn, ArrayList<Integer> num) {
        for (int i=nn+1;i<num.size();i++) {
            if (num.get(i) == index) {
                return false;
            }
        }
        return true;
    }
}


