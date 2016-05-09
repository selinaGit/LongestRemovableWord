package geek.tech;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * This class will find a longest removable word from a LinkedList of String
 * Created by Selina on 6/3/15.
 */
public class LongestRemovableWord {

    /**
     *
     * find the longest removable Words
     * @param sortedWords   the sorted words at LinkedList from file
     * @return the linkedList contains the longest removable Words
     */
    public LinkedList<String> findLongestRemovableWords(LinkedList<String> sortedWords) {

        HashMap<String, String> map = new HashMap<>();
        LinkedList<String> output = new LinkedList<>();

        //find all removable words and store it into arrayList
        ArrayList<String> removableWords = findRemovableWords(sortedWords,map);

        //find the longest words from the ArrayList and store it into LinkedList
        LinkedList<String> longestRemovableWords = findLongestWords(removableWords);

        for (String word : longestRemovableWords) {
            String wordsChain = getWordChain(map, word);
            if (wordsChain != null) {
                output.add(wordsChain);
            }
        }
        return output;
    }

    /**
     *
     *  find all removable words and store it into arrayList
     * @param sortedWords : words from file and sorted by length
     * @return : all removable words stored at ArrayList
     */
    public ArrayList<String> findRemovableWords(LinkedList<String> sortedWords, HashMap<String, String> map){


        ArrayList<String> removableWords = new ArrayList<>();

        if (sortedWords == null || sortedWords.isEmpty()) {
            return removableWords;
        }
        for (String word : sortedWords) {
            if (word.length() == 1) {
                map.put(word, "");
                removableWords.add(word);
            } else if ( isRemovable(map, word)) {
                removableWords.add(word);
            }
        }
        return removableWords;
    }

    /**
     *
     * find the longest words from the ArrayList and store it into LinkedList
     * @param words : ArrayList of word
     * @return : The longest words stored in LinkedList from this ArrayList
     */
    public LinkedList<String> findLongestWords(ArrayList<String> words){

        LinkedList<String> longestWords = new LinkedList<>();
        if (words == null || words.size() == 0) {
            return longestWords;
        }
        Collections.sort(words, Comparator.comparing(String::length).reversed());

        int maxLen = words.get(0).length();

        for (String word : words) {
            if (word.length() == maxLen) {
                longestWords.add(word);
            } else {
                break;
            }
        }
        return longestWords;
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
        if ( map == null || map.size()==0 || word == null || word.length()==0) {
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

    /**
     *
     * remove any letter in String word, and check if
     * @param map : a HashMap to store all pair of removable word and it next word
     * @param word : String word
     * @return : boolean value, true or false
     */
    public boolean isRemovable(HashMap<String, String> map, String word) {

        boolean isRemovable = false;
        if (map.get(word) != null) {
            return true;
        }

        for ( int i = 0; i < word.length(); i++) {
            String newWord = word.substring(0, i) + word.substring(i + 1);
            if (map.get(newWord)!=null) {
                isRemovable = true;
                map.put(word, newWord);
                break;
            }
        }
        return isRemovable;
    }
}
