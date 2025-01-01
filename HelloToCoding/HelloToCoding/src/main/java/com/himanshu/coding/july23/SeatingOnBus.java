package com.himanshu.coding.july23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SeatingOnBus {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        int bus[][] = new int[n][4];

        int person=1;
        int currentRow=0;

        while ( (person <= m) && currentRow<n ) {
            bus[currentRow][0]=person;
            person++;

            if (person<=m) {
                bus[currentRow][3]=person;
                person++;
            }
            currentRow++;
        }

        currentRow=0;

        while ( (person <= m) && currentRow<n ) {
            bus[currentRow][1]=person;
            person++;

            if (person<=m) {
                bus[currentRow][2]=person;
                person++;
            }
            currentRow++;
        }


/*        for (int i=0;i<n;i++) {
            for (int j=0;j<4;j++) {
                System.out.print(bus[i][j]+"\t");
            }
            System.out.println();
        }*/

        StringBuilder sb = new StringBuilder();

        for (int i=0;i<n;i++) {
            if (bus[i][1] != 0) {
                sb.append(bus[i][1]);
                sb.append(" ");
            }

            if (bus[i][0] != 0) {
                sb.append(bus[i][0]);
                sb.append(" ");
            }

            if (bus[i][2] != 0) {
                sb.append(bus[i][2]);
                sb.append(" ");
            }

            if (bus[i][3] != 0) {
                sb.append(bus[i][3]);
                sb.append(" ");
            }
        }

        System.out.print(sb.toString());
    }
}
