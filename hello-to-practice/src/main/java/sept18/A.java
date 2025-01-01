package sept18;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<>();
        list.add(14);
        list.add(11);
        //list.add(9);
        //list.add(10);
        //list.add(12);

        System.out.println(Result.getMinMoves(list));

    }
}


class Result {

    /*
     * Complete the 'getMinMoves' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY plates as parameter.
     */

    public static int getMinMoves(List<Integer> plates) {
        ArrayList<Integer> list = new ArrayList<>(plates);
        int swap=0;
        int min=list.get(0);
        int minPos=0;
        for (int i=1;i<list.size();i++) {
            if (min > list.get(i)) {
                min=list.get(i);
                minPos=i;
            }
        }

        swap+=minPos;

        ArrayList<Integer> nList = new ArrayList<>();

        for (int i=0;i<list.size();i++) {
            if (i!=minPos) {
                nList.add(list.get(i));
            }
        }

        int max=nList.get(0);
        int maxPos=0;

        for (int i=1;i<nList.size();i++) {
            if (max < nList.get(i)) {
                maxPos=i;
                max=nList.get(i);
            }
        }

        swap+= nList.size()-1-maxPos;

        return swap;

    }
}
