package Y2023.march16;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * @Date 3/16/2024
 */
public class D {
    public static void main(String[] args) {
        System.out.println(countDistinctPasswordsBF("abcb"));
        System.out.println(countDistinctPasswords("abcb"));
    }

    static HashMap<String, String> reverseMap = new HashMap<>();

    public static long countDistinctPasswords(String password) {
        HashMap<Character, Integer> frequency = getfrequencyMap(password);

        long ans = choose2(password.length())+1;

        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            ans -= choose2(entry.getValue());
        }
        return ans;
    }
    public static long countDistinctPasswordsBF(String password) {
        TreeSet<String> treeSet = new TreeSet<>();

        for (int i=0;i<password.length();i++) {
            for (int j=i+1;j<=password.length();j++) {
                String newS = password.substring(0,i)+reverse(password.substring(i,j))+password.substring(j);
                treeSet.add(newS);
            }
        }

        return treeSet.size();
    }

    private static HashMap<Character, Integer> getfrequencyMap(String pwd) {
        HashMap<Character, Integer> frequency = new HashMap<>();

        for (int i=0;i<pwd.length();i++) {
            if (!frequency.containsKey(pwd.charAt(i))) {
                frequency.put(pwd.charAt(i),0);
            }

            frequency.put(pwd.charAt(i), frequency.get(pwd.charAt(i))+1);
        }
        return frequency;
    }

    private static String reverse(String substring) {
        if (reverseMap.containsKey(substring)) {
            return reverseMap.get(substring);
        }

        StringBuffer sb = new StringBuffer();

        for (int i=substring.length()-1;i>=0;i--) {
            sb.append(substring.charAt(i));
        }
        reverseMap.put(substring,sb.toString());

        return sb.toString();
    }

    private static long choose2(int n) {
        return (((long)n)*(n-1))/2;
    }
}
