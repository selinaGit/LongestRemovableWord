package geek.tech;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Selina on 6/3/15.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        String javaVersion = Util.getVersion();

        if (!Util.isJava8(javaVersion)) {
            System.out.println("Need Java8 JDK to Run this Project");
            System.exit(-1);
        }

        if ( args.length != 1) {
            System.out.println("Need Argument for Input Dictionary for this Project.");

            System.out.println("Comment Lines:\n " +
             "mvn exec:java -Dexec.mainClass=\"geek.tech.Main\" -Dexec.args=\"./src/main/resources/simpleDictionary.txt\"\n" +
             "Or\n" +
             "mvn exec:java -Dexec.mainClass=\"geek.tech.Main\" -Dexec.args=\"./src/main/resources/wordsEnglish.txt");
            System.exit(-1);
        }

        String fileDir = args[0];
        BufferedReader reader = Util.getFileReader(fileDir);

        System.out.println("findLongestRemovableWords");
        findLongestRemovableWords(reader, fileDir);

        reader = Util.getFileReader(fileDir);
        System.out.println("\nfindLongestRemovableWordsDP");
        findLongestRemovableWordsDP(reader, fileDir);
    }

    public static void findLongestRemovableWords(BufferedReader reader, String fileDir)  throws IOException{

        long startTime = System.currentTimeMillis();
        WordsReader wordsReader = WordsReader.fromFile(reader);

        LinkedList<String> sortedWords = wordsReader.getWords();

        LongestRemovableWord test = new LongestRemovableWord();
        LinkedList<String> outputs = test.findLongestRemovableWords(sortedWords);

        long endTime = System.currentTimeMillis();

        for (String output : outputs) {
            System.out.println(output);
        }
        System.out.println("Process "+ fileDir + " Took "+ (endTime - startTime)/1000.0 + " Seconds");
    }

    public static void findLongestRemovableWordsDP(BufferedReader reader, String fileDir)  throws IOException{
        long startTime = System.currentTimeMillis();
        DictionaryReader dictReader = DictionaryReader.fromFile(reader);

        HashSet<String> dictionary = dictReader.getDictionary();

        LongestRemovableWordDP test = new LongestRemovableWordDP();

        LinkedList<String> outputs = test.findLongestRemovableWords(dictionary);

        long endTime = System.currentTimeMillis();

        for (String output : outputs) {
            System.out.println(output);
        }
        System.out.println("Process "+ fileDir + " Took "+ (endTime - startTime)/1000.0 + " Seconds");
    }
}
