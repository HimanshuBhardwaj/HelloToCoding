package Y2023.oct8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 10/8/2023
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine());
        String [] str ;

        while (t-- > 0) {
            str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int p = Integer.parseInt(str[1]);

            str = br.readLine().split(" ");
            int [] a = new int[str.length];

            for (int i=0;i<str.length;i++) {
                a[i] = Integer.parseInt(str[i]);
            }

            str = br.readLine().split(" ");
            int [] b = new int[str.length];

            for (int i=0;i<str.length;i++) {
                b[i] = Integer.parseInt(str[i]);
            }

            pw.append(getMinimumCostToShareInformation(n,p,a,b)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static long getMinimumCostToShareInformation(int n, int p, int[] a, int[] b) {
        long cost = p;
        ArrayList<Person> people = new ArrayList<>();
        TreeSet<Person> treeSet = new TreeSet<>();
        TreeSet<Person> treeSetNew = new TreeSet<>();

        Person admin =new Person(0,  Integer.MAX_VALUE,p, false);
        treeSetNew.add(admin);

        for (int i=1;i<=n;i++) {
            Person person = new Person(i, a[i-1], b[i-1], false);
            treeSet.add(person);
        }

        Person f = treeSet.first();
        treeSet.remove(f);
        treeSetNew.add(f);


        while (!treeSet.isEmpty()) {
            Person first = treeSetNew.first();
            treeSetNew.remove(first);

            for (int i = 0;i < first.ai;i++) {
                if (treeSet.isEmpty()) {
                    break;
                }

                Person second = treeSet.first();
                treeSet.remove(second);
                treeSetNew.add(second);
                cost+= first.bi;
            }
        }

        return cost;
    }
}

class Person implements Comparable<Person>{
    int index;
    int ai;
    int bi;
    boolean isInformaed;
    public Person(int i, int ai, int bi, boolean isInformaed) {
        this.index = i;
        this.ai = ai;
        this.bi = bi;
        isInformaed = false;
    }

    @Override
    public int compareTo(Person o) {
        if (this.bi != o.bi) {
            return Integer.compare(this.bi, o.bi);
        } else {
            return Integer.compare(this.index, o.index);
        }
    }
}