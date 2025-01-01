package Y2023.june2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JapaneseCrosswordsStrikeBack {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int x = Integer.parseInt(str[1]);

        str = br.readLine().split(" ");

        long sum = 0;

        for (int i=0; i< str.length;i++) {
            sum += Integer.parseInt(str[i]);
        }

        sum+= (str.length)-1;

        if(sum==x) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}