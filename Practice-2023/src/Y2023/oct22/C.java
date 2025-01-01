package Y2023.oct22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Himanshu Bhardwaj
 * @Date 10/22/2023
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);
            str  = br.readLine().split(" ");
            int [] arr = new int[n];
            for (int i=0;i<n;i++) {
                arr[i] = Integer.parseInt(str[i]) % k;
            }
            pw.append(minNumToModify(k,arr)+"\n");
        }
        pw.close();
        pw.flush();
    }

    private static int minNumToModify(int k, int[] arr) {
        int maxNum = arr[0];
        int minNum = arr[0];

        for (int i=1;i<arr.length;i++) {
            maxNum = Math.max(maxNum, arr[i]);
            minNum = Math.min(minNum, arr[i]);
        }

        if (minNum == 0) {
            return 0;
        }

        Arrays.sort(arr);

        if (k==4) {
            if (maxNum == 3) {
                int n2 = number2(arr);
                if (n2 >=2) {
                    return 0;
                } else {
                    return k - maxNum;
                }
            } else if (maxNum == 2 && arr.length>=2){
                int n2 = number2(arr);

                if (n2 >=2) {
                    return 0;
                } else {
                    return 1;
                }
            } else if (maxNum == 1 && arr.length >= 2) {
                return 2;
            }
        }

        return k-maxNum;
    }

    private static int number2(int[] arr) {
        int count=0;

        for (int x :arr) {
            if (x==2) {
                count++;
            }
        }

        return count;
    }
}
