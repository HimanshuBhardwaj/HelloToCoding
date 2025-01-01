package com.himanshu.coding.sept15;

import java.util.LinkedList;
import java.util.Queue;

public class QueuePOC {
    public static void main(String[] args) {
        Queue<POC> queue = new LinkedList<>();
        queue.add(new POC(10));
        queue.add(new POC(19));
        queue.add(new POC(15));
        queue.add(new POC(11));
        queue.add(new POC(12));
        queue.add(new POC(13));


        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }
}



class POC {
    int value;

    public POC(int v) {
        this.value = v;
    }

    @Override
    public String toString() {
        return "POC{" +
                "value=" + value +
                '}';
    }
}
