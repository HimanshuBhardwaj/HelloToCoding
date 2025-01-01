package Y2023.aug3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class KnapsackImplementation {
    public static void main(String[] args) {
        Item item1 = new Item(3,5);
        Item item2 = new Item(3,7);
        Item item3 = new Item(3,11);
        Item item4 = new Item(4,13);
        Item item5 = new Item(1,11);
        Item item6 = new Item(3,21);
        Item item7 = new Item(1,31);

        ArrayList<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
        items.add(item7);

        System.out.println(new KnapsackBruteForce().maxValue(items, 15));
        System.out.println(new KnapsackDP().maxValue(items, 15));
    }
}

class KnapsackDP implements KnapsackI {

    @Override
    public int maxValue(List<Item> items, int weight) {
       int [][] dp = new int [items.size()+1][weight+1];
       // item, weight;
       for (int i=0;i <= items.size();i++) {
           dp[i][0] = 0;
       }

        for (int i=0;i <= weight;i++) {
            dp[0][i] = 0;
        }

        for (int i=1;i<=items.size();i++) {
            for (int j=1;j<=weight;j++) {
                if (items.get(i-1).weight <= j) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-items.get(i-1).weight]+items.get(i-1).value);
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[items.size()][weight];
    }
}

class KnapsackBruteForce implements KnapsackI {

    HashMap<Entry, Integer> valueMemorisation = new HashMap<>();

    @Override
    public int maxValue(List<Item> items, int weight) {
        return maxValueHelper(0, items, weight);
    }

    private int maxValueHelper(int index, List<Item> items, int weight) {
        if (index >= items.size() || weight < 0) {
            return 0;
        }

        Entry entry = new Entry(index, weight);

        if (valueMemorisation.containsKey(entry)) {
            return valueMemorisation.get(entry);
        }

        int max1 = maxValueHelper(index+1, items, weight);
        int max2 = Integer.MIN_VALUE;

        if (weight >= items.get(index).weight) {
            max2 = maxValueHelper(index + 1, items, weight - items.get(index).weight) + items.get(index).value;
        }
        valueMemorisation.put(entry, Math.max(max1, max2));

        return Math.max(max1, max2);
    }
}

class Entry {
    public Entry(int i, int w) {
        this.i = i;
        this.w = w;
    }

    int i;
    int w;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return i == entry.i && w == entry.w;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, w);
    }
}

class Item {
    int value;
    int weight;

    public Item( int weight, int value) {
        this.value = value;
        this.weight = weight;
    }
}
interface KnapsackI {
    int maxValue(List<Item> items, int weight);
}