package Y2023.dec9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/9/2023
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String [] str = br.readLine().split(" ");
            ArrayList<Elements> elements = new ArrayList<>(str.length);

            for (int i=0;i<str.length;i++) {
                elements.add(new Elements(Integer.parseInt(str[i]), i));
            }

            pw.append(elementSum(elements)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static String elementSum(ArrayList<Elements> elements) {
        Collections.sort(elements);

        ArrayList<Long> ps = new ArrayList<>();
        long sum=0;
        for (Elements x:elements) {
            sum+=x.value;
            ps.add(sum);
        }

        // jaha sey hum remove karna start kar sakte hai
        int removalNum=0;

        for (int i=0;i<elements.size();i++) {
            removalNum = getRemovalNum(i, removalNum, ps, elements);
            if (i > removalNum ) {
                elements.get(i).removalNum = removalNum;
            } else {
                elements.get(i).removalNum = removalNum-1;
            }
        }

        Comparator<Elements> comparator = new Comparator<Elements>() {
            @Override
            public int compare(Elements o1, Elements o2) {
                return Integer.compare(o1.index, o2.index);
            }
        };
        Collections.sort(elements, comparator);

        StringBuilder sb = new StringBuilder();

        for (Elements e: elements) {
            sb.append(e.removalNum+" ");
        }

        return sb.toString();
    }

    private static int getRemovalNum(int ep, int removalNum, ArrayList<Long> ps, ArrayList<Elements> elementsArrayList) {
        for (int i=removalNum;i<elementsArrayList.size();i++) {
            if (i==ep) {
                continue;
            }

            long cs = (i==0)?0:ps.get(i-1);

            if ((i-1) < ep) {
                cs += elementsArrayList.get(ep).value;
            }

            if (cs < elementsArrayList.get(i).value) {
                return i;
            }
        }
        return elementsArrayList.size();
    }
}


class Elements implements Comparable<Elements> {
    public Elements(int value, int index) {
        this.value = value;
        this.index = index;
    }

    int value;
    int index;

    int removalNum;

    @Override
    public int compareTo(Elements o) {
        if (this.value != o.value) {
            return Integer.compare(this.value, o.value);
        }
        return Integer.compare(this.index, o.index);
    }

    @Override
    public String toString() {
        return "value=" + value;
    }
}