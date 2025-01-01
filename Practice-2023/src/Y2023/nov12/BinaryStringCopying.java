package Y2023.nov12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Himanshu Bhardwaj
 * @Date 11/12/2023
 */
public class BinaryStringCopying {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());


        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int m = Integer.parseInt(str[1]);
            String s = br.readLine();
            int count = 1;
            for (int i=1;i<=m;i++) {
                str = br.readLine().split(" ");
                boolean isS = isSorted(s,Integer.parseInt(str[0])-1, Integer.parseInt(str[1])-1);
                if (!isS) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }

    private static boolean isSorted(String str, int s, int e) {
        if (s==e) {
            return true;
        }

        for (int i=s+1;i<=e;i++) {
            if (str.charAt(i) < str.charAt(i-1)) {
                return false;
            }
        }
        return true;
    }
}
