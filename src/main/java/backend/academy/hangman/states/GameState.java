package backend.academy.hangman.states;

import backend.academy.hangman.Comparator;
import backend.academy.hangman.configuration.ConfigWord;
import backend.academy.hangman.configuration.Word;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;
import static backend.academy.hangman.OutputClass.EMPTY_HANGMAN;
import static backend.academy.hangman.OutputClass.FIFTH_HANGMAN;
import static backend.academy.hangman.OutputClass.FIRST_HANGMAN;
import static backend.academy.hangman.OutputClass.FOURTH_HANGMAN;
import static backend.academy.hangman.OutputClass.FULL_HANGMAN;
import static backend.academy.hangman.OutputClass.SECOND_HANGMAN;
import static backend.academy.hangman.OutputClass.THIRD_HANGMAN;
import static backend.academy.hangman.OutputClass.printList;
import static backend.academy.hangman.OutputClass.printMassive;
import static backend.academy.hangman.OutputClass.printSmth;

public class GameState implements State {

    @Getter
    private final Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);

    @Getter
    private final Word secretWord;

    @Getter
    private final ConfigWord selectedConfig;

    private final String word;
    private final String help;

    @Setter
    @Getter
    private int currentAttempts;

    private final int maxAttempts;
    private final float zero = 0f;
    private final float zeroTwo = 0.2f;
    private final float zeroFour = 0.4f;
    private final float zeroSix = 0.6f;
    private final float zeroEight = 0.8f;
    private final float one = 1f;

    public GameState(Word secretWord, ConfigWord selectedConfig, int currentAttempts) {
        this.secretWord = secretWord;
        this.selectedConfig = selectedConfig;
        this.currentAttempts = currentAttempts;

        word = secretWord.word();
        help = secretWord.help();

        maxAttempts = currentAttempts;
    }

    @Override
    public void handle() {
        yourConfig();
        String[] wordMassive = new String[word.length()];
        List<String> incorrectLetters = new ArrayList<>();
        wordMassive = initWordMassive(wordMassive);
        Comparator comparator = new Comparator(word);
        boolean isPlaying = true;
        printSmth(EMPTY_HANGMAN);
        printMassive(wordMassive);

        while (currentAttempts > 0 && isPlaying) {
            printSmth("Enter a letter of the English alphabet\n");
            String input = sc.nextLine();
            isPlaying = checkAnswer(input, wordMassive, word, comparator, incorrectLetters);
            drawCurrentHangman();
            printSmth("You have " + currentAttempts + " attempts left.\n");
            printMassive(wordMassive);
            printSmth(giveHelp());
            printSmth("Incorrect letters: ");
            printList(incorrectLetters);
            printSmth("*********************************");
        }
    }

    public boolean checkAnswer(
        String input,
        String[] wordMassive,
        String word,
        Comparator comparator,
        List<String> incorrectLetters
    ) {
        if (comparator.compare(input) && comparator.checkInput(input)) {
            printSmth("Great! \"" + input.toUpperCase() + "\" is in the word!\n");
            putLetter(wordMassive, word, input.toUpperCase());
            if (!Arrays.asList(wordMassive).contains("_")) {
                return false;
            }
        } else if (!comparator.checkInput(input)) {
            printSmth("This is not a letter of the English alphabet!");
        } else {
            printSmth("\"" + input.toUpperCase() + "\" is not in the word\n");
            if (!incorrectLetters.contains(input.toUpperCase())) {
                incorrectLetters.add(input.toUpperCase());

            }
            currentAttempts--;
        }
        return loseCheck(currentAttempts);
    }

    public void yourConfig() {
        printSmth("Selected category is " + selectedConfig.categories());
        printSmth("Selected difficulty level is " + selectedConfig.difficulties());
    }

    public String giveHelp() {
        if (currentAttempts <= 2) {
            return "Help: " + help;
        }
        return "";
    }

    private boolean loseCheck(int currentAttempts) {
        return currentAttempts > 0;
    }

    private String[] initWordMassive(String[] wordMassive) {
        Arrays.fill(wordMassive, "_");
        return wordMassive;
    }

    private void putLetter(String[] wordMassive, String word, String letter) {
        for (int i = 0; i < wordMassive.length; i++) {
            if (word.charAt(i) == letter.charAt(0)) {
                wordMassive[i] = letter;
            }
        }
    }

    private void drawCurrentHangman() {
        float percentage = (float) currentAttempts / maxAttempts;
        if (percentage == one) {
            printSmth(EMPTY_HANGMAN);
        } else if (percentage >= zeroEight && percentage < one) {
            printSmth(FIRST_HANGMAN);
        } else if (percentage >= zeroSix && percentage < zeroEight) {
            printSmth(SECOND_HANGMAN);
        } else if (percentage >= zeroFour && percentage < zeroSix) {
            printSmth(THIRD_HANGMAN);
        } else if (percentage >= zeroTwo && percentage < zeroFour) {
            printSmth(FOURTH_HANGMAN);
        } else if (percentage > zero && percentage < zeroTwo) {
            printSmth(FIFTH_HANGMAN);
        } else if (percentage == zero) {
            printSmth(FULL_HANGMAN);
        }
    }
}
