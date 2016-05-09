package geek.tech;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;

/**
 *
 * Read a file of words into a HashSet
 * Created by Selina on 6/4/15.
 */
public class DictionaryReader {

    private final HashSet<String> dictionary;

    DictionaryReader(HashSet<String> dictionary) {
        this.dictionary = dictionary;
    }

    public HashSet<String> getDictionary() {
        return this.dictionary;
    }

    /**
     *
     * Read and split and sort all number from Files
     * @param reader ex: Reader reader = new FileReader(OUTPUT_DIR+fileName)
     * @return : a new DictionaryReader object
     * @throws java.io.IOException
     */
    static DictionaryReader fromFile(BufferedReader reader) throws IOException {

        HashSet<String> dictionary =  new HashSet<>();

        reader.lines()
                .parallel()
                .forEachOrdered(dictionary::add);

        return new DictionaryReader(dictionary);
    }
}
