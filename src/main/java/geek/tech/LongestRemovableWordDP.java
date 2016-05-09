package geek.tech;

import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 *
 * Created by Selina on 6/4/15.
 */
public class LongestRemovableWordDP {

    public LinkedList<String> findLongestRemovableWords(HashSet<String>  dictionary){
        LinkedList<String> outputs = new LinkedList<>();
        HashMap<String, String> removableMap = new HashMap<>();


        for (String word : dictionary) {
            isRemovable(dictionary, removableMap, word);
        }

        LinkedList<String> longestWords = getLongestWords(removableMap);

        for (String word: longestWords) {
            String outputStr = getWordChain(removableMap, word);
            outputs.add(outputStr);
        }
        return outputs;
    }

    public boolean isRemovable(HashSet<String> dictionary, HashMap<String, String> removableMap, String word) {

        boolean isRemovable = false;
        if (word.length() == 1 && dictionary.contains(word)) {
            removableMap.put(word, "");
            return true;
        }

        for ( int i = 0; i < word.length(); i++) {
            String newWord = word.substring(0, i) + word.substring(i + 1);
            //only consider the word in dictionary
            if (dictionary.contains(newWord)){

                //the newWord is removable, add the word, newWord pair to removableMap
                if (removableMap.get(newWord) != null) {
                    removableMap.put(word, newWord);
                    return true;
                } else {
                    //try to if the newWord is removable or not
                    isRemovable = isRemovable(dictionary, removableMap, newWord);
                    if (isRemovable) {
                        removableMap.put(word, newWord);
                        return true;
                    }
                }
            }
        }
        return isRemovable;
    }

    public LinkedList<String> getLongestWords(HashMap<String, String> map) {

        LinkedList<String> outputs = new LinkedList<>();
        int maxLen = 0;
        for (String word: map.keySet()) {
            if (word.length() == maxLen) {
                outputs.add(word);
            }
            if (word.length() > maxLen) {
                outputs.clear();
                outputs.add(word);
                maxLen = word.length();
            }
        }
        return outputs;
    }



    /**
     *
     * give a word, we track all removable words by HashMap
     * @param map  : a HashMap<String, String></String,> to store all removable words and its next removable words.
     * @param word : a String word
     * @return : the output string. ex:starting => stating => statin => satin => sati => sat => at => a
     */
    public String getWordChain(HashMap<String, String> map, String word) {

        StringBuilder output = new StringBuilder();
        if ( map == null || map.size() == 0 || word == null || word.length() == 0) {
            return null;
        }

        output.append(word);
        String nextWord = map.get(word);

        while (nextWord!=null && !nextWord.isEmpty()){

            output.append(" => ");
            output.append(nextWord);
            nextWord = map.get(nextWord);
        }

        return output.toString();
    }
}
