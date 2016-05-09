package geek.tech;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.*;

public class LongestRemovableWordTest {

    @Test
    public void testFindLongestRemovableWords1() throws Exception {

        LongestRemovableWord test = new LongestRemovableWord();

        //[a, at, be, bat, bee, sat, sati, satin, statin, stating, starting]
        String[] sortedInput ={"a", "at", "be", "bat", "bee", "sat", "sati", "satin", "statin", "stating", "starting"};
        LinkedList<String> sortedWords = new LinkedList<>(Arrays.asList(sortedInput));

        LinkedList<String> outputs = test.findLongestRemovableWords(sortedWords);
        assertEquals(1, outputs.size());
        assertEquals("starting => stating => statin => satin => sati => sat => at => a", outputs.get(0));
    }

    @Test
    public void testFindLongestRemovableWords2() throws Exception {

        LongestRemovableWord test = new LongestRemovableWord();

        //[a, at, be, bat, bee, sat, sati, satin, statin, stating, starting]
        String[] sortedInput ={"a", "at", "be", "bat", "bee", "sat", "sati", "satin", "statin", "stating", "starting","steating"};
        LinkedList<String> sortedWords = new LinkedList<>(Arrays.asList(sortedInput));

        LinkedList<String> outputs = test.findLongestRemovableWords(sortedWords);
        assertEquals(2, outputs.size());
        assertEquals("starting => stating => statin => satin => sati => sat => at => a", outputs.get(0));
        assertEquals("steating => stating => statin => satin => sati => sat => at => a", outputs.get(1));
    }

    @Test
    public void testFindLongestRemovableWords3() throws Exception {

        LongestRemovableWord test = new LongestRemovableWord();

        LinkedList<String> sortedWords = new LinkedList<>();

        LinkedList<String> outputs = test.findLongestRemovableWords(sortedWords);
        assertEquals(0, outputs.size());
    }

    @Test
    public void testGetWordChain() throws Exception {

        LongestRemovableWord test = new LongestRemovableWord();

        HashMap<String, String> dictionary = new HashMap<>();

        //starting => stating => statin => satin => sati => sat => at => a
        dictionary.put("at", "a");
        dictionary.put("sat", "at");
        dictionary.put("sati", "sat");
        dictionary.put("satin", "sati");
        dictionary.put("statin", "satin");
        dictionary.put("stating", "statin");
        dictionary.put("starting", "stating");

        String output1 = "starting => stating => statin => satin => sati => sat => at => a";
        assertEquals(output1, test.getWordChain(dictionary, "starting"));

        String output2 = "satin => sati => sat => at => a";
        assertEquals(output2, test.getWordChain(dictionary, "satin"));
    }

    @Test
    public void testFindLongestWords() throws Exception {

        LongestRemovableWord test = new LongestRemovableWord();

        String[] input ={"a", "at", "bat", "sat", "sati", "satin", "statin", "stating", "starting", "steating"};
        ArrayList<String> removableWords = new ArrayList<>(Arrays.asList(input));
        HashMap<String, String> dictionary = new HashMap<String, String>();

        LinkedList<String> outputs = test.findLongestWords(removableWords);
        assertEquals(2, outputs.size());
        assertEquals("starting", outputs.get(0));
        assertEquals("steating", outputs.get(1));
    }

    @Test
    public void testFindRemovableWords() throws Exception {

        LongestRemovableWord test = new LongestRemovableWord();

        String[] sortedInput ={"a", "at", "be", "bat", "bee", "sat", "sati", "satin", "statin", "stating", "starting"};
        LinkedList<String> sortedWords = new LinkedList<>(Arrays.asList(sortedInput));
        HashMap<String, String> dictionary = new HashMap<>();

        //outputs = [a, at, bat, sat, sati, satin, statin, stating, starting]
        ArrayList<String> outputs = test.findRemovableWords(sortedWords, dictionary);

        assertEquals(9, outputs.size());
        assertEquals("a", outputs.get(0));
        assertEquals("at", outputs.get(1));
        assertEquals("bat", outputs.get(2));
        assertEquals("sat", outputs.get(3));
        assertEquals("sati", outputs.get(4));
        assertEquals("satin", outputs.get(5));
        assertEquals("statin", outputs.get(6));
        assertEquals("stating", outputs.get(7));
        assertEquals("starting", outputs.get(8));
    }

    @Test
    public void testIsRemovable1() throws Exception {
        LongestRemovableWord test = new LongestRemovableWord();

        HashMap<String, String> dictionary = new HashMap<>();

        dictionary.put("a", "");

        assertTrue("at is removable", test.isRemovable(dictionary, "at"));
        assertTrue("att is removable", test.isRemovable(dictionary, "att"));
        assertTrue("att is removable", test.isRemovable(dictionary, "sat"));
        assertTrue("att is removable", test.isRemovable(dictionary, "sati"));
    }


    @Test
    public void testIsRemovable2() throws Exception {
        LongestRemovableWord test = new LongestRemovableWord();

        HashMap<String, String> dictionary = new HashMap<>();

        dictionary.put("a", "");

        assertFalse("att is removable", test.isRemovable(dictionary, "att"));
    }
}