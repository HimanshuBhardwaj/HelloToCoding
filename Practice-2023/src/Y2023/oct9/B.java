package Y2023.oct9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Himanshu Bhardwaj
 * @Date 10/9/2023
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String [] str = br.readLine().split(" ");
            int Px = Integer.parseInt(str[0]);
            int Py = Integer.parseInt(str[1]);

            str = br.readLine().split(" ");
            int Ax = Integer.parseInt(str[0]);
            int Ay = Integer.parseInt(str[1]);

            str = br.readLine().split(" ");
            int Bx = Integer.parseInt(str[0]);
            int By = Integer.parseInt(str[1]);

            pw.append(getSmallestW(Px,Py,Ax,Ay,Bx,By)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static double getSmallestW(int px, int py, int ax, int ay, int bx, int by) {
        double start = 0;
        double end = 4000;

        if(isReachable(px,py,ax,ay,bx,by, start)) {
            return start;
        }


        while (Double.compare(Math.abs(start-end), 0.00000001) >= 0) {
            double mid = (start+end)/2;

            boolean ir = isReachable(px,py,ax,ay,bx,by, mid);

            if (ir)
            {
                end=mid;
            }
            else
            {
                start=mid;
            }
        }

        return start;
    }

    static boolean isReachable(int px, int py, int ax, int ay, int bx, int by,double d){
        if (containsInside(ax,ay,px,py,d) && containsInside(ax,ay,0,0,d)) {
            return true;
        }

        if (containsInside(bx,by,px,py,d) && containsInside(bx,by,0,0,d)) {
            return true;
        }

        if (Double.compare(getDistance(ax,ay,bx,by), 4*d*d) <= 0) {
            if (containsInside(ax,ay,px,py,d) && containsInside(bx,by,0,0,d)) {
                return true;
            }

            if (containsInside(ax,ay,0,0,d) && containsInside(bx,by,px,py,d)) {
                return true;
            }
        }

        return false;
    }

    static double getDistance(double ax,double ay, double bx, double by) {
        return ((ax-bx)*(ax-bx))+((ay-by)*(ay-by));
    }

    static boolean containsInside(int Cx, int Cy, int Ox, int Oy, double w) {
        if ( Double.compare(((Cx-Ox)*(Cx-Ox)) + ((Cy-Oy)*(Cy-Oy)),w*w) <=0 ) {
            return true;
        }
        return false;
    }
}
