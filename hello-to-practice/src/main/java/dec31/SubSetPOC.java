package dec31;

import java.util.*;

public class SubSetPOC {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("Himanshu");
        list.add("Bhardwaj");
        list.add("Great");
        list.add("Man");

        ArrayList<ArrayList<String>> listSS = new SubSetImpl().subSets(list);
        System.out.println(listSS.size());
        System.out.println(listSS);
    }
}

class SubSetImpl implements SubSet {

    @Override
    public ArrayList<ArrayList<String>> subSets(ArrayList<String> set) {
        TreeMap<Integer, String> posMap = getPosMap(set);
        System.out.println(posMap);
        TreeMap<String, Integer> elementMap = elemantMap(posMap);

        ArrayList<ArrayList<String>> subSets = new ArrayList<>();

        for (int i=0;i<=Math.pow(2,posMap.size())-1;i++) {
            subSets.add(getSubSet(i,posMap));
        }

        return subSets;
    }

    private ArrayList<String> getSubSet(int index, TreeMap<Integer, String> posMap) {
        ArrayList<String> list = new ArrayList<>();

        for (int i=0;i<32;i++)
        {
            int temp = (1<<i)&index;
            if ((1<<i&index)>0) {
                list.add(posMap.get(i));
                if (posMap.get(i)==null) {
                    System.out.println(posMap);
                    System.out.println(temp);
                    System.out.println(index+"\t."+i);
                }
            }
        }

        return list;
    }

    private TreeMap<String, Integer> elemantMap(TreeMap<Integer, String> posMap) {
        TreeMap<String, Integer> em = new TreeMap<>();

        for (Map.Entry<Integer, String> entry : posMap.entrySet()) {
            em.put(entry.getValue(),entry.getKey());
        }
        return em;
    }

    private TreeMap<Integer, String> getPosMap(ArrayList<String> set) {
        TreeSet<String> tSet = new TreeSet<>(set);
        int pos=0;

        TreeMap<Integer, String>  treeMap = new TreeMap<>();

        for (String e: tSet) {
            treeMap.put(pos++,e);
        }
        return treeMap;
    }
}


// 7:05
interface SubSet {
    ArrayList<ArrayList<String>> subSets(ArrayList<String> set);
}
