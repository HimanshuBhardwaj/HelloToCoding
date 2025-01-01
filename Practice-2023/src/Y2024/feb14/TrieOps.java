package Y2024.feb14;

import java.util.ArrayList;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/14/2024
 */
public class TrieOps {
    public static void main(String[] args) {
        TrieNode head = new TrieNode();
        TrieI trieI = new TrieOperation();
        trieI.insert(head,"himanshua",1);
        trieI.insert(head,"himanshubhardwajb",2);
        trieI.insert(head,"himanshubhardwajc",3);
        trieI.insert(head,"himanshubhardwajd",4);
        trieI.insert(head,"himanshubhardwaje",5);
        trieI.insert(head,"himanshubhardwajf",6);
        trieI.insert(head,"himanshubhardwajg",7);
//        System.out.println(trieI.contains(head, "himanshu"));
//        System.out.println(trieI.contains(head, "himanshubhardwaj"));
        System.out.println(trieI.contains(head, "himanshubhardwajh"));
        System.out.println(trieI.prefixKeys(head,"himanshu"));
    }
}

class TrieOperation implements TrieI {

    @Override
    public void insert(TrieNode head, String str, int value) {
        insertHelper(head,0, str, value);
    }

    // head will never be null
    private void insertHelper(TrieNode head, int index, String str, int value) {
        if (index==str.length()) {
            head.values.add(value);
            return;
        }

        if (head.child[(int)(str.charAt(index)-'a')]==null) {
            head.child[(int)(str.charAt(index)-'a')] = new TrieNode();
        }
        insertHelper(head.child[(int)(str.charAt(index)-'a')],index+1,str,value);
    }

    @Override
    public TrieNode delete(TrieNode head, String str) {
        return null;
    }

    @Override
    public ArrayList<Integer> prefixKeys(TrieNode head, String prefix) {
        return prefixKeysHelper(head,0,prefix);
    }

    //head will never be null
    private ArrayList<Integer> prefixKeysHelper(TrieNode head, int index, String prefix) {
        if (index<prefix.length()) {
            if (head.child[(int)(prefix.charAt(index)-'a')] != null) {
                ArrayList<Integer> keys = prefixKeysHelper(head.child[(int)(prefix.charAt(index)-'a')], index+1, prefix);
                return keys;
            }
        } else {
            ArrayList<Integer> keys = new ArrayList<>();
            keys.addAll(head.values);

            for (TrieNode node : head.child) {
                if (node != null) {
                    keys.addAll(prefixKeysHelper(node, index+1, prefix));
                }
            }
            return keys;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean contains(TrieNode head, String key) {
        return containsHelper(head,0,key);
    }

    // head will not be null
    private boolean containsHelper(TrieNode head, int index, String key) {
        if (index==key.length()) {
            return head.values.size()>0;
        }
        if (head.child[(int)(key.charAt(index)-'a')] == null) {
            return false;
        }
        return containsHelper(head.child[(int)(key.charAt(index)-'a')], index+1, key);
    }

    @Override
    public int size(TrieNode head) {
        return 0;
    }
}


interface TrieI {
    void insert(TrieNode head, String str, int value);
    TrieNode delete(TrieNode head, String str);
    ArrayList<Integer> prefixKeys(TrieNode head, String prefix);
    boolean contains(TrieNode head, String key);
    int size(TrieNode head);
}

class TrieNode {
    ArrayList<Integer> values;
    TrieNode [] child;

    public TrieNode() {
        child = new TrieNode[26];
        values = new ArrayList<>();
    }
}
