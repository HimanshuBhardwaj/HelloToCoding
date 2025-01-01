package Y2023.dec28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/28/2023
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            char [][] arr = new char[3][3];
            arr[0]=br.readLine().toCharArray();
            arr[1]=br.readLine().toCharArray();
            arr[2]=br.readLine().toCharArray();

            int a[] = new int[26];

            for (int i=0;i<3;i++) {
                for (int j=0;j<3;j++) {
                    if (arr[i][j] != '?') {
                        a[(int) (arr[i][j] - 'A')]++;
                    }
                }
            }

            if (a[0]==2) {
                pw.append('A').append("\n");
            }

            if (a[1]==2) {
                pw.append('B').append("\n");
            }

            if (a[2]==2) {
                pw.append('C').append("\n");
            }
        }
        pw.flush();
        pw.close();
    }
}
