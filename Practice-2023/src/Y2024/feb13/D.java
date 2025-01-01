package Y2024.feb13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/13/2024
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            long n = Long.parseLong(str[0]);
            long x = Long.parseLong(str[1]);
            long y = Long.parseLong(str[2]);

            str = br.readLine().split(" ");
            long a[] = new long[str.length];

            for (int i=0;i<n;i++) {
                a[i] = Long.parseLong(str[i]);
            }
            pw.append(maxDivisiblePairs(n,x,y,a)+"\n");
        }

        pw.flush();
        pw.close();
    }

    private static long maxDivisiblePairs(long n, long x, long y, long[] a) {
        TreeMap<Long, ArrayList<Number>> modY = new TreeMap<>();
        Number[] numbers = new Number[(int)n];

        for (int i=0;i<n;i++) {
            numbers[i] = new Number(i,a[i],a[i]%y,a[i]%x,calculateNegativeModN(a[i], x));

            if (!modY.containsKey(numbers[i].nModY)) {
                modY.put(numbers[i].nModY, new ArrayList<>());
            }

            modY.get(numbers[i].nModY).add(numbers[i]);
        }

        long count=0;

        for (Map.Entry<Long, ArrayList<Number>> entry: modY.entrySet()) {
            count += getXModuloEqual(entry.getValue(), x);
        }

        return count;
    }

    private static long getXModuloEqual(ArrayList<Number> value, long x) {
        if (value.size() < 2) {
            return 0;
        }

        TreeMap<Long, ArrayList<Number>> modX = new TreeMap<>();

        for (int i=0;i<value.size();i++) {
            if (!modX.containsKey(value.get(i).nModX)) {
                modX.put(value.get(i).nModX, new ArrayList<>());
            }
            modX.get(value.get(i).nModX).add(value.get(i));
        }

        long count1=0;
        long count2=0;

        for (Map.Entry<Long, ArrayList<Number>> entry: modX.entrySet()) {
            long nModN = calculateNegativeModN(entry.getKey(), x);
            if (entry.getKey() == nModN) {
                count1+= (((entry.getValue().size()-(long)1)*(long)entry.getValue().size())/2);
            } else {
                long nModSize = modX.containsKey(nModN)?modX.get(nModN).size():0;
                count2 += ((entry.getValue().size())*(nModSize));
            }
        }

        return count1+((count2)/2);
    }

    private static long calculateNegativeModN(long key, long x) {
        return ((long)x - (key%x))%x;
    }
}


class Number {
    long index;
    long num;
    long nModY;
    long nModX;

    long minusNModX;

    public Number(long index, long num, long nModY, long nModX, long minusNModX) {
        this.index = index;
        this.num = num;
        this.nModY = nModY;
        this.nModX = nModX;
        this.minusNModX = minusNModX;
    }
}