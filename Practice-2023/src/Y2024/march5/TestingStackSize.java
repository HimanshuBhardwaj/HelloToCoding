package Y2024.march5;

/**
 * @author Himanshu Bhardwaj
 * @Date 3/5/2024
 */
public class TestingStackSize {
    public static void main(String[] args) {
        poc(0,0, 0);
    }

    static void poc (long i, long j, long k) {
        System.out.println(i);
        poc(++i,++j, ++k);
    }
}
