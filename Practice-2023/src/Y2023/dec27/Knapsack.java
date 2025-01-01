package Y2023.dec27;

import java.util.*;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/27/2023
 */
public class Knapsack {
    public static void main(String[] args) {
        Item item1 = new Item(1,1,20);
        Item item2 = new Item(1,2,21);
        Item item3 = new Item(1,3,22);
        Item item4 = new Item(1,4,4);
        Item item5 = new Item(1,5,5);

        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);


        KnapsackI knapsackI = new KnapsackBF((ArrayList)items);
        KnapsackI knapsackI2 = new KnapsackDP((ArrayList)items);

        int weight=100000;

        System.out.println(knapsackI.getItemsWithMaxValue(weight));
        System.out.println(knapsackI2.getItemsWithMaxValue(weight));

    }
}

class KnapsackDP implements KnapsackI {
    ArrayList<Item> items;
    int n;

    public KnapsackDP(ArrayList<Item> items) {
        this.items = items;
        this.n = items.size();
    }

    @Override
    public List<Item> getItemsWithMaxValue(int weight) {
        int dp [][] = new int[n][weight+1];

        for (int i=items.get(0).weight;i<=weight;i++) {
            dp[0][i]= items.get(0).value;
        }

        for (int i=1;i<n;i++) {
            for (int j=0;j<=weight;j++) {
                dp[i][j] = dp[i-1][j];
                if (j >= items.get(i).weight) {
                   dp[i][j] = Math.max(dp[i][j],(dp[i-1][j-items.get(i).weight]+items.get(i).value ));
                }
            }
        }

        for (int i=0;i<n;i++) {
            for (int j=0;j<=weight;j++) {
                System.out.print(dp[i][j]+"\t");
            }
            System.out.println();
        }
        return null;
    }
}


class KnapsackBF implements KnapsackI {
    ArrayList<Item> items;
    int n;

    ArrayList<Item> tempSelected;
    int tempValue;

    HashSet<String> isVisited;

    public KnapsackBF(ArrayList<Item> items) {
        this.items = items;
        this.n = items.size();
        this.isVisited = new HashSet<>();
    }

    @Override
    public List<Item> getItemsWithMaxValue(int weight) {
        this.tempSelected = new ArrayList<>();
        this.tempValue = Integer.MIN_VALUE;
        LinkedList<Item> itemsS = new LinkedList<>();
        getItemsWithMaxValueInternal(0,weight,itemsS,0);
        return this.tempSelected;
    }

    private void getItemsWithMaxValueInternal(int index, int weight, LinkedList<Item> itemsS, int currentValue) {
        if (weight < 0) {
            return;
        }

        if (index < 0 || index >= n ) {
            if (currentValue > this.tempValue) {
                this.tempValue = currentValue;
                this.tempSelected = new ArrayList<>(itemsS);
            }
            return;
        }

        if (this.isVisited.contains(index+"|"+weight)) {
            System.out.println("Cache hit: "+index+"|"+weight+"|"+currentValue);
            return;
        }

        itemsS.addLast(items.get(index));
        getItemsWithMaxValueInternal(index+1, weight-items.get(index).weight,itemsS, currentValue+items.get(index).value);
        itemsS.removeLast();
        getItemsWithMaxValueInternal(index+1, weight,itemsS, currentValue);
        this.isVisited.add(index+"|"+weight+"|"+currentValue);
    }
}

interface KnapsackI {
    List<Item> getItemsWithMaxValue(int weight);
}

class Item {
    int index;

    int weight;

    int value;

    public Item(int index, int weight, int value) {
        this.index = index;
        this.weight = weight;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "index=" + index +
                ", weight=" + weight +
                ", value=" + value +
                "}\n";
    }
}