package backend.academy.hangman.states;

import backend.academy.hangman.configuration.ConfigWord;
import backend.academy.hangman.configuration.Word;
import lombok.Getter;
import lombok.Setter;
import static backend.academy.hangman.OutputClass.printSmth;

/**
 * Класс, реализующий состояние результатов игры
 */
public class ResultState implements State {

    @Getter
    private final Word secretWord;

    @Getter
    private final ConfigWord selectedConfig;

    @Setter
    @Getter
    private int currentAttempts;

    private final String word;

    public ResultState(Word secretWord, ConfigWord selectedConfig, int currentAttempts) {
        this.secretWord = secretWord;
        this.selectedConfig = selectedConfig;
        this.currentAttempts = currentAttempts;
        this.word = secretWord.word();
    }

    @Override
    public void handle() {
        checkResult(currentAttempts);
    }

    private void checkResult(int currentAttempts) {
        if (currentAttempts == 0) {
            loseMessage();
        } else {
            winMessage();
        }
    }

    /**
     * Вывод слов в случае победы пользователя
     */
    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    private void winMessage() {
        printSmth("\n**************************************\n"
                + "Congratulations! You have won!\n"
                + "The hidden word was "
                + word
                + " in "
                + selectedConfig.categories()
                + " category and "
                + selectedConfig.difficulties()
                + " difficulty level.\n"
                + "You have "
                + currentAttempts
                + " attempts left!\n"
                + "**************************************");
    }

    /**
     * Вывод слов в случае поражения пользователя
     */
    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    private void loseMessage() {
        printSmth("\n**************************************\n"
                + "You have lost!\n"
                + "The hidden word was "
                + word
                + " in "
                + selectedConfig.categories()
                + " category and "
                + selectedConfig.difficulties()
                + " difficulty level.\n"
                + "**************************************");
    }

}
