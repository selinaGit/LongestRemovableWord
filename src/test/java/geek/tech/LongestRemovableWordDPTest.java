package geek.tech;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Selina on 6/4/15.
 */
public class LongestRemovableWordDPTest {


    @Test
    public void testIsRemovable() {

        LongestRemovableWordDP test = new LongestRemovableWordDP();
        HashSet<String> dictionary = new HashSet<>();
        HashMap<String, String> removableMap = new HashMap<>();

        String[] sortedInput ={"starting", "a", "bee", "sat", "sati", "bat", "satin", "at", "statin", "be", "stating"};

        for (String word: sortedInput){
            dictionary.add(word);
        }

        assertTrue(test.isRemovable(dictionary, removableMap, "stating"));
        System.out.println(removableMap);
        assertTrue(test.isRemovable(dictionary, removableMap, "starting"));
        System.out.println(removableMap);
    }

    @Test
    public void testGetLongestWords1() {

        LongestRemovableWordDP test = new LongestRemovableWordDP();

        HashMap<String, String> map = new HashMap<>();
        map.put("a", "");
        map.put("statin", "satin");
        map.put("starting", "stating");
        map.put("at", "a");

        LinkedList<String> outputs = test.getLongestWords(map);
        assertEquals(1, outputs.size());
        assertEquals("starting", outputs.get(0));
    }

    @Test
    public void testGetLongestWords2() {

        LongestRemovableWordDP test = new LongestRemovableWordDP();

        HashMap<String, String> map = new HashMap<>();
        map.put("a", "");
        map.put("statin", "satin");
        map.put("starting", "stating");
        map.put("at", "a");
        map.put("staiting", "stating");

        LinkedList<String> outputs = test.getLongestWords(map);
        assertEquals(2, outputs.size());
        assertEquals("staiting", outputs.get(0));
        assertEquals("starting", outputs.get(1));
    }

    @Test
    public void testFindLongestRemovableWords1() {
        LongestRemovableWordDP test = new LongestRemovableWordDP();

        HashSet<String>  dictionary = new HashSet<>();
        String[] sortedInput ={"starting", "a", "bee", "sat", "sati", "bat", "satin", "at", "statin", "be", "stating"};
        for (String word: sortedInput){
            dictionary.add(word);
        }
        LinkedList<String> output = test.findLongestRemovableWords(dictionary);

        assertEquals(1, output.size());
        assertEquals("starting => stating => statin => satin => sati => sat => at => a", output.get(0));
    }

    @Test
    public void testFindLongestRemovableWords2() {
        LongestRemovableWordDP test = new LongestRemovableWordDP();

        HashSet<String>  dictionary = new HashSet<>();
        String[] sortedInput ={"starting", "a", "bee", "sat", "sati", "bat", "satin", "at", "statin", "be", "stating","staiting"};
        for (String word: sortedInput){
            dictionary.add(word);
        }
        LinkedList<String> output = test.findLongestRemovableWords(dictionary);

        assertEquals(2, output.size());
        assertEquals("staiting => stating => statin => satin => sati => sat => at => a", output.get(0));
        assertEquals("starting => stating => statin => satin => sati => sat => at => a", output.get(1));
    }
}
