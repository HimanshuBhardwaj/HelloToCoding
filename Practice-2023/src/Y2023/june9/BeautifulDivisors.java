package Y2023.june9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BeautifulDivisors {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(longestBeautifulDiviser(n));
    }

    static int longestBeautifulDiviser(int n)
    {
        int longestDiviser = 1;
        for (int k=1; Math.pow(2,k)*(Math.pow(2,k+1)-1)<=n; k++)
        {
            int num= (int) (Math.pow(2,k)*(Math.pow(2,k+1)-1));
            if (n%num == 0)
            {
                longestDiviser = num;
            }
        }

        return longestDiviser;
    }
}
