package Y2024.feb11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/11/2024
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            br.readLine();
            String [] str = br.readLine().split(" ");
            TreeSet<Number> numbers = new TreeSet<>();

            for (int i=0;i<str.length;i++) {
                numbers.add(new Number(Integer.parseInt(str[i])));
            }

            pw.append(maxCommonNumber(numbers,str.length)+"").append("\n");
        }
        pw.flush();
        pw.close();
    }

    private static int maxCommonNumber(TreeSet<Number> numbers, int n) {
        int i=0;
        for (Number num: numbers) {
            num.index = i;
            i++;
        }

        int max = Integer.MIN_VALUE;

        for (Number number: numbers) {
            Number newNum = new Number(number.number+n-1);
            Number nextPos = numbers.floor(newNum);
            max = Math.max(max, nextPos.index-number.index+1);
        }

        return max;
    }

}

class Number implements Comparable<Number> {
    int number;
    int index;

    @Override
    public int compareTo(Number o) {
        return Integer.compare(this.number, o.number);
    }

    public Number(int number) {
        this.number = number;
    }
}
