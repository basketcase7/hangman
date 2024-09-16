package backend.academy.hangman;

import java.io.PrintStream;
import java.util.List;
import lombok.experimental.UtilityClass;

/**
 * Класс, необходимый для вывода различной информации на экран
 */
@UtilityClass
public class OutputClass {

    public static PrintStream output = System.out;
    public static final String OPEN_MESSAGE = """
        **************************************
        *                                    *
        *                                    *
        *       Welcome to Hangman Game!     *
        *                                    *
        *  Try to guess the hidden word one  *
        *  letter at a time. You will have   *
        *  a limited number of incorrect     *
        *  guesses before the game is over.  *
        *                                    *
        *  Good luck and have fun!           *
        *                                    *
        **************************************
        """;
    public static final String CHOOSE_CATEGORY = """

        **************************************
        Choose a category:
        1. Country names
        2. Brands names
        3. Music genres

        If you do not select a category, it will be randomly selected

        **************************************
        """;

    public static final String CHOOSE_DIFFICULTY = """

        **************************************
        Choose a difficulty level:
        1. EASY
        2. MEDIUM
        3. HARD

        If you do not select a difficulty level, it will be randomly selected

        **************************************

        """;

    public static final String EMPTY_HANGMAN = """
              _______
             |/
             |
             |
             |
             |
             |
            _|___
        """;
    public static final String FIRST_HANGMAN = """
              _______
             |/      |
             |      (_)
             |
             |
             |
             |
            _|___
        """;
    public static final String SECOND_HANGMAN = """
              _______
             |/      |
             |      (_)
             |      \\|
             |
             |
             |
            _|___
        """;
    public static final String THIRD_HANGMAN = """
              _______
             |/      |
             |      (_)
             |      \\|/
             |
             |
             |
            _|___
        """;
    public static final String FOURTH_HANGMAN = """
              _______
             |/      |
             |      (_)
             |      \\|/
             |       |
             |
             |
            _|___
        """;
    public static final String FIFTH_HANGMAN = """
              _______
             |/      |
             |      (_)
             |      \\|/
             |       |
             |      /
             |
            _|___
        """;
    public static final String FULL_HANGMAN = """
              _______
             |/      |
             |      (_)
             |      \\|/
             |       |
             |      / \\
             |
            _|___
        """;

    public static void printSmth(String out) {
        output.println(out);
    }

    public static void printMassive(String[] massive) {
        for (String s : massive) {
            output.print(s + " ");
        }
        output.println();
    }

    public static void printList(List<String> list) {
        for (String s : list) {
            output.print(s + " ");
        }
        output.println();
    }
}
