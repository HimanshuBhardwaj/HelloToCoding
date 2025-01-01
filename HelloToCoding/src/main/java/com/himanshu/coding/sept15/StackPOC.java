package com.himanshu.coding.sept15;

import java.util.Stack;

public class StackPOC {
    public static void main(String[] args) {
        Stack<Element> stack = new Stack<>();
        stack.add(new Element(9));
        stack.add(new Element(10));
        stack.add(new Element(11));
        stack.add(new Element(12));
        stack.add(new Element(13));

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }

    }
}

class Element {
    int value;

    public Element(int v) {
        this.value = v;
    }

    @Override
    public String toString() {
        return "Element{" +
                "value=" + value +
                '}';
    }
}