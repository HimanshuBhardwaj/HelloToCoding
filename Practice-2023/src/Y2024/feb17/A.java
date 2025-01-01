    package Y2024.feb17;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.io.PrintWriter;
    import java.util.Arrays;

    /**
     * @author Himanshu Bhardwaj
     * @Date 2/17/2024
     */
    public class A {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int t = Integer.parseInt(br.readLine());
            PrintWriter pw = new PrintWriter(System.out);

            while (t-- > 0) {
                int n = Integer.parseInt(br.readLine());
                long [] a = new long[2*n];
                String [] str = br.readLine().split(" ");

                for (int i=0;i<str.length;i++) {
                    a[i] = Long.parseLong(str[i]);
                }

                pw.append(maxNum(a)+"\n");
            }

            pw.flush();
            pw.close();
        }

        private static long maxNum(long[] a) {
            Arrays.sort(a);
            long max=0;

            for (int i= a.length-2;i>=0;i--) {
                max+=a[i];
                i--;
            }
            return max;
        }
    }
