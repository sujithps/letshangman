package in.upcode.hangman;

import in.upcode.hangman.model.Word;

import java.util.Arrays;
import java.util.Scanner;

import static in.upcode.hangman.utils.ArrayUtils.hasBlanks;
import static in.upcode.hangman.utils.ArrayUtils.printArray;
import static in.upcode.hangman.utils.WordUtils.getARandomWord;
import static in.upcode.hangman.utils.WordUtils.isInWord;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Word word = getARandomWord();

        char[] guessedWord = new char[word.length()];

        Arrays.fill(guessedWord, '_');

        int tries = 0;
        boolean wordIsGuessed = false;
        while (!wordIsGuessed && tries != word.length()) {
            System.out.print("Current word: ");
            printArray(guessedWord);
            System.out.printf("You have %d tries left.\n", word.length() - tries);
            System.out.println("Enter a single character: ");
            char c = input.next().charAt(0);
            if (isInWord(word, c)) {
                updateGuessedWordIfTheLetterPresentInTheWord(word, guessedWord, c);
                if (!hasBlanks(guessedWord)) {
                    wordIsGuessed = true;
                }
            } else {
                System.out.println(c + " is not in the word.");
                tries++;
            }
        }
        if (wordIsGuessed) {
            System.out.println("Congratulations! The word was " + word);
        } else {
            System.out.println("Sorry, you ran out of tries. The word was " + word);
        }
    }

    private static void updateGuessedWordIfTheLetterPresentInTheWord(Word word, char[] guessedWord, char c) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == c) {
                guessedWord[i] = c;
            }
        }
    }
}