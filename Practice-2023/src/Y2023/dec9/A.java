package Y2023.dec9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/9/2023
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);

            str = br.readLine().split(" ");
            int xk = Integer.parseInt(str[0]);
            int yk = Integer.parseInt(str[1]);

            str = br.readLine().split(" ");
            int xq = Integer.parseInt(str[0]);
            int yq = Integer.parseInt(str[1]);

            pw.append(numTimes(a,b,xk,yk,xq,yq)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static int numTimes(int a, int b, int xk, int yk, int xq, int yq) {
        TreeSet<String> positions = new TreeSet<>();

        int kx = xk-a;
        int ky = yk-b;
        possibleQueenPlaces(kx,ky,a,b,xq,yq,positions);

        kx = xk-a;
        ky = yk+b;
        possibleQueenPlaces(kx,ky,a,b,xq,yq,positions);

        kx = xk+a;
        ky = yk-b;
        possibleQueenPlaces(kx,ky,a,b,xq,yq,positions);

        kx = xk+a;
        ky = yk+b;
        possibleQueenPlaces(kx,ky,a,b,xq,yq,positions);

        kx = xk-b;
        ky = yk-a;
        possibleQueenPlaces(kx,ky,a,b,xq,yq,positions);

        kx = xk-b;
        ky = yk+a;
        possibleQueenPlaces(kx,ky,a,b,xq,yq,positions);

        kx = xk+b;
        ky = yk-a;
        possibleQueenPlaces(kx,ky,a,b,xq,yq,positions);

        kx = xk+b;
        ky = yk+a;
        possibleQueenPlaces(kx,ky,a,b,xq,yq,positions);

        return positions.size();
    }

    static void possibleQueenPlaces(int kx, int ky, int a, int b, int xq, int yq, TreeSet<String> positions) {
        if ((xq-a) == kx && (yq-b)==ky) {
            positions.add(kx+","+ky);
        }

        if ((xq-a) == kx && (yq+b)==ky) {
            positions.add(kx+","+ky);
        }

        if ((xq+a) == kx && (yq-b)==ky) {
            positions.add(kx+","+ky);
        }

        if ((xq+a) == kx && (yq+b)==ky) {
            positions.add(kx+","+ky);
        }

        if ((xq-b) == kx && (yq-a)==ky) {
            positions.add(kx+","+ky);
        }

        if ((xq-b) == kx && (yq+a)==ky) {
            positions.add(kx+","+ky);
        }

        if ((xq+b) == kx && (yq-a)==ky) {
            positions.add(kx+","+ky);
        }

        if ((xq+b) == kx && (yq+a)==ky) {
            positions.add(kx+","+ky);
        }
    }
}
