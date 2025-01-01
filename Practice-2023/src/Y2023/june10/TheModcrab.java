package Y2023.june10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class TheModcrab {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");
        int h1 = Integer.parseInt(str[0]);
        int a1 = Integer.parseInt(str[1]);
        int c1 = Integer.parseInt(str[2]);

        str = br.readLine().split(" ");
        int h2 = Integer.parseInt(str[0]);
        int a2 = Integer.parseInt(str[1]);

        List<String> list = new LinkedList<String>();

        while (h2 > 0 && h1 > 0) {
            if ((h1 >= a2) || ((h2 - a1) <=0)) {
                h2 = h2 - a1;
                list.add("STRIKE");

                if (h2 <= 0) {
                    break;
                }
                h1 = h1-a2;
            }
            else
            {
                h1 = h1 + c1;
                list.add("HEAL");
                h1 = h1-a2;
            }
        }

        // STRIKE
        // HEAL

        System.out.println(list.size());
        for (String s: list) {
            System.out.println(s);
        }


    }
}
