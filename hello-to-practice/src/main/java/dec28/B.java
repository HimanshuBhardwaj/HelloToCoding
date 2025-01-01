package dec28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            ArrayList<Segment> segments = new ArrayList<>();

            for (int i=0;i<n;i++) {
                String str[] = br.readLine().split(" ");
                segments.add(new Segment(str[0], str[1]));
            }
            pw.append(printPossibleValues(segments)+"\n");
        }
        pw.flush();
        pw.close();
    }

    private static String printPossibleValues(ArrayList<Segment> segments) {
        TreeMap<Integer, Value> map = new TreeMap<>();

        for (Segment s: segments) {
            if (s.e==s.s) {
                if (!map.containsKey(s.e)) {
                    map.put(s.e,getValueE(0));
                }
                map.put(s.e,getValueE(map.get(s.e).value+1));
            }
        }

        for (Integer v : map.keySet()) {
            Integer less = map.lowerKey(v);
            if (less != null) {
                map.put(v, getValueE(map.get(v).value+map.get(less).value));
            }
        }

        int pos=0;
        for (Map.Entry<Integer, Value> entry : map.entrySet()) {
            entry.getValue().index=pos++;
        }


        StringBuilder sb = new StringBuilder();

        for (Segment s: segments) {
            int segLength = s.e - s.s+1;
            if (s.s == s.e) {
                if ((getValue(s.e,map)-getValue(s.s-1,map)==1)) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
            }
            else if((getIndex(s.e,map)-getIndex(s.s,map) == segLength-1)) {
                sb.append("0");
            } else {
                sb.append("1");
            }
        }

        return sb.toString();
    }

    private static int getIndex(int i, TreeMap<Integer, Value> map) {
        if (map.containsKey(i)) {
            return map.get(i).index;
        }
        return -1;
    }

    private static Value getValueE(int i) {
        return new Value(i);
    }

    private static int getValue(int i, TreeMap<Integer, Value> map) {
        if (map.containsKey(i)) {
            return map.get(i).value;
        }
        Integer floor = map.floorKey(i);

        if (floor == null) {
            return 0;
        }
        return map.get(floor).value;
    }
}


class Value {
    int value;

    public Value(int value) {
        this.value = value;
    }

    int index;
}
class Segment {
    public Segment(String s, String e) {
        this.s = Integer.parseInt(s);
        this.e = Integer.parseInt(e);
    }

    int s;
    int e;
}
