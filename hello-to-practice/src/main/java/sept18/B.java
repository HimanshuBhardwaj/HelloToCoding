package sept18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "aaaab";
        System.out.println(Result2.getMaxFreqDeviation(str));
    }
}

class Result2 {

    /*
     * Complete the 'getMaxFreqDeviation' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int getMaxFreqDeviation(String s) {
        char [] charString = s.toCharArray();
        int maxDeviation=0;

        for (int i=0;i<charString.length;i++) {


            if((charString.length-i) < maxDeviation) {
                break;
            }

            HashMap<Character,Integer> frequency = new HashMap<>();
            for (int j=i;j<charString.length;j++) {
                if (!frequency.containsKey(charString[j])) {
                    frequency.put(charString[j],1);
                } else {
                    frequency.put(charString[j],1+frequency.get(charString[j]));
                }
                maxDeviation = Math.max(maxDeviation,computeDeviation(frequency));
            }
        }
        return maxDeviation;
    }

    private static int computeDeviation(HashMap<Character, Integer> frequency) {
        if (frequency.size()==1) {
            return 0;
        }

        int deviation=0;

        Integer min = null;
        Integer max = null;

        for (Map.Entry<Character, Integer> entry: frequency.entrySet()) {
            if (min==null) {
                min = entry.getValue();
            } else {
                min = Math.min(min,entry.getValue());
            }

            if (max == null) {
                max = entry.getValue();
            } else {
                max = Math.max(max,entry.getValue());
            }
        }

        return Math.abs(max-min);
    }

}