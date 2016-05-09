```
1. Project Description
This program finds the word from which one can remove the most letters, one at a time, such that 
each resulting word is itself a valid word. For example, you can remove seven letters from "starting":
 
starting => stating => statin => satin => sati => sat => at => a


The program must take the path to a dictionary as input. The dictionary will contain words, one per line. 
The program must output the longest chains which can be created from the words in the dictionary. 
Please note that the input is only the dictionary; the input does not include a hypothetical starting word.  
The format must be as above with one space between each word and the following "=>" and one space after the "=>". 
If there are multiple words that produce equal length chains, then print each chain on a line by itself.
This program must work with large dictionaries with more than a hundred thousand words.

2. Input

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
Input File 1. Sample dictionary contains 11 words below located at "./src/main/resources/simpleDictionary.txt"

Output:
"starting => stating => statin => satin => sati => sat => at => a"

Time:
Process ./src/main/resources/simpleDictionary.txt Took 0.108 Seconds

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
Input File  2.  English dictionary has 109,582 words located at "./src/main/resources/wordsEnglish.txt"

Output
glassiness => glassines => glassies => lassies => lasses => lases => lass => ass => as => a
carrousels => carousels => carouses => arouses => arouse => arose => arse => ars => as => a

Time:
Process ./src/main/resources/wordsEnglish.txt Took 0.573 Seconds

3. Comment Lines: 

Make Project: 
mvn package 

Run Project:
1)  mvn exec:java -Dexec.mainClass="geek.tech.Main" -Dexec.args="./src/main/resources/simpleDictionary.txt"
2)  mvn exec:java -Dexec.mainClass="geek.tech.Main" -Dexec.args="./src/main/resources/wordsEnglish.txt"

4. Solution 1:
1) Read Input File
    a. Read file by Stream parallel() 
    b. all the words are sorted by length of the word
    c. all words are stored in LinkedList;
2) Find the longest removable words from the sorted words at LinkedList 
    a. Use a HashMap<String, String>  to store all removable words, The key is a removable word, and value is the word 
       has one letter less and also removable word
    b. put all one letter words to the HashMap as <word, "">
    c. for all words, from the shorter word to Longer word. we remove one letter and get new word, if one of the new word
       is removable word stored in the HashMap, we can put the word to the HashMap as <Word, newWord> pair
    d. All removalbe words are added to a ArrayList<String>, sort the ArrayList<String>, the Longest String at the beginning
    e. Find the longest words. and print all track
     
5. Running Time

1). Sort all words in file take O(nLog(n)), we use Stream parallel here, so it very fast
2). For each word with m letters, we will try all the way to remove one letter, so it take time O(m*n). 
    If you see in detail, everytime, we get a new word by remove one letter, it cost O(m) so the running time is O(n*m*m)
3) The running time is Max(O(nLog(n), O(n*m*m));

6. Solution 2:
1) Read Input File
    a. Read file by Stream parallel() and put them into dictionary HashSet 
2) Find the longest removable words at HashMap<String, String>  
    a. Use a HashMap<String, String>  to store all removable words, The key is a removable word, and value is the word 
       has one letter less and also removable word
    b. for each word in dictionary HashSet, we remove one letter once and get new words.
        if a word is not in dictionary HashSet, return false;
        if a word is one letter and in dictionary HashSet, add new pair <word, ""> into  HashMap for removable words, 
        and return true
        if the new word is in HashMap for removable words, add new pair <word, newWord>, return true;
        otherwise, recursively to check if the new Word is removable
        
        
Running Time 
1. The running time is O(n*m*m));
   
Compare:
Solution 2 is fast than Solution 1 in general, especial for small input file.
The running time for each word, the Solution 1 is more even because, each word only check one letter shorter word

BTW, with my personal Macbook, after several times testing, no one is always better than another solution for larger 
input size.

   for small input Solution 2 is much better:
   findLongestRemovableWords
   starting => stating => statin => satin => sati => sat => at => a
   Process ./src/main/resources/simpleDictionary.txt Took 0.071 Seconds
   
   findLongestRemovableWordsDP
   starting => stating => statin => satin => sati => sat => at => a
   Process ./src/main/resources/simpleDictionary.txt Took 0.002 Seconds
   
   - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
   
   For larger input files 
   findLongestRemovableWords
   glassiness => glassines => glassies => lassies => lasses => lases => lass => ass => as => a
   carrousels => carousels => carouses => arouses => arouse => arose => arse => ars => as => a
   Process ./src/main/resources/wordsEnglish.txt Took 0.551 Seconds
   
   findLongestRemovableWordsDP
   glassiness => glassines => glassies => lassies => lasses => lases => lass => ass => as => a
   carrousels => carousels => carouses => arouses => arouse => arose => arse => ars => as => a
   Process ./src/main/resources/wordsEnglish.txt Took 0.524 Seconds
   
   - - - - - - - - - - - - - - - - - -
   
   findLongestRemovableWords
   glassiness => glassines => glassies => lassies => lasses => lases => lass => ass => as => a
   carrousels => carousels => carouses => arouses => arouse => arose => arse => ars => as => a
   Process ./src/main/resources/wordsEnglish.txt Took 0.605 Seconds
   
   findLongestRemovableWordsDP
   glassiness => glassines => glassies => lassies => lasses => lases => lass => ass => as => a
   carrousels => carousels => carouses => arouses => arouse => arose => arse => ars => as => a
   Process ./src/main/resources/wordsEnglish.txt Took 0.652 Seconds
   
   
7. Testing
1) Only LongestRemovableWord and LongestRemovableWordDP classes were tested because of limited time

8. Main 
1) The time was printout
           Process ./src/main/resources/simpleDictionary.txt Took 0.078 Seconds
           Process ./src/main/resources/wordsEnglish.txt Took 0.502 Seconds
           ...
           
9) Limitation:
code of class LongestRemovableWord is not neat enough, it will modify later on.

```