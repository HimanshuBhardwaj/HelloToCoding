package Y2023.oct2023;

import java.io.IOException;
import java.util.*;

public class B {
    public static void main(String[] args) throws IOException {
        List<String> words = new ArrayList<>();
        words.add("allot");
        words.add("cat");
        words.add("peach");
        words.add("dusty");
        words.add("act");
        words.add("cheap");


        List<String> queries = new ArrayList<>();
        queries.add("tac");
        queries.add("study");
        queries.add("peahc");

        List<List<String>> result = getSearchResults(words, queries);

    }

    public static List<List<String>> getSearchResults(List<String> words, List<String> queries) {
        HashMap<String, ArrayList<String>> anagramMap = getMapOfWords(words);
        List<List<String>> queryResult = new ArrayList<>();

        for (String query : queries) {
            List<String> result = anagramMap.get(getAnagramMap(query));

            if (result == null) {
                result = new ArrayList<>();
            }

            queryResult.add(result);
        }

        return queryResult;
    }

    public static HashMap<String, ArrayList<String>> getMapOfWords(List<String> words)
    {
        HashMap<String, ArrayList<String>> mapOfwords = new HashMap<>();

        for (String word: words) {
            String map = getAnagramMap(word);
            if (!mapOfwords.containsKey(map)) {
                mapOfwords.put(map,new ArrayList<>());
            }
            mapOfwords.get(map).add(word);
        }

        for (Map.Entry<String,  ArrayList<String>> entry: mapOfwords.entrySet()) {
            Collections.sort(entry.getValue());
        }

        return mapOfwords;
    }

    private static String getAnagramMap(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        StringBuffer sb = new StringBuffer();

        for (char c: chars) {
            sb.append(c);
        }

        return sb.toString();
    }
}
