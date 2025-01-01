package Y2023.june2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BuggyRobot {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int l = 0;
        int r = 0;
        int u = 0;
        int d = 0;

        String commands = br.readLine();

        for (int i=0; i<commands.length();i++) {
            switch (commands.charAt(i))
            {
                case 'L': l++;
                break;
                case 'R': r++;
                break;
                case 'U': u++;
                break;
                case 'D': d++;
            }
        }

        System.out.println((Math.min(l,r)+Math.min(u,d))*2);
    }
}
