package exempel.lv1.wordcount;

import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.System.in;
import static java.lang.System.out;

/*
 * Count number of words in some text
 *
 * See:
 * - UseCharacter
 * - UseString
 */
public class WordCount {

    public static void main(String[] args) {
        new WordCount().program();
    }

    void program() {
        out.println(countWords("") == 0);
        out.println(countWords("hello") == 1);
        out.println(countWords(" hello ") == 1);
        out.println(countWords("hello world") == 2);
        out.println(countWords("hello        world") == 2);
        out.println(countWords("   hello        world  ") == 2);
        String s = "Education is what remains after one has forgotten what one has learned in school.";
        out.println(countWords(s) == 14);

    }

    //--------------- Methods -----------------

    private int countWords(String text) {
        String[] words = text.split(" ");
        int wordCount = 0;
        for (String word : words) {
            if (!word.equals(" ") && !word.equals("")) {
                wordCount++;
            }
        }
        return wordCount;
    }
}
