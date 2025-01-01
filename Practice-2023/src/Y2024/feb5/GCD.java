package Y2024.feb5;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/5/2024
 */
public class GCD {
    public static void main(String[] args) {
        GCDI gcdi = new GCDImpl();
        System.out.println(gcdi.gcd(10,100));
        System.out.println(gcdi.gcd(130,3900));
    }
}

class GCDImpl implements GCDI {

    @Override
    public int gcd(int x, int y) {
        if (x > y) {
            return gcd(y,x);
        }

        if (x==0) {
            return y;
        }
        if (x==1) {
            return 1;
        }
        return gcd(y%x,x);
    }
}
interface GCDI {
    int gcd(int x, int y);
}
