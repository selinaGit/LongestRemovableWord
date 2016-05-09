package geek.tech;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 *
 * Read all words in one file
 * Created by Selina on 6/3/15.
 */
public class WordsReader {

    private final LinkedList<String> words;

    WordsReader(LinkedList<String> words) {
        this.words = words;
    }

    public LinkedList<String> getWords() {
        return this.words;
    }

    /**
     *
     * Read and split and sort all number from Files
     * @param reader ex: Reader reader = new FileReader(OUTPUT_DIR+fileName)
     * @return : a new DictionaryReader object
     * @throws java.io.IOException
     */
    static WordsReader fromFile(BufferedReader reader) throws IOException {

        LinkedList<String> words;
        Stream<String> streamLines;

        streamLines = reader.lines();
        words = sortWords(streamLines);

        return new WordsReader(words);
    }

    /**
     *
     * Sort the Stream as the length of Words and then output to LinkedList
     * @param streamlines : a Stream of String
     * @return : LinkedList contains all String from Stream
     */
    static LinkedList<String> sortWords(Stream<String> streamlines) {

        LinkedList words = new LinkedList<String>();

        streamlines.parallel()
                .sorted(Comparator.comparing(String::length))
                .forEachOrdered(words::add);
        return words;
    }

}
