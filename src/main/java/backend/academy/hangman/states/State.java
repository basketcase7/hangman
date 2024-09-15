package backend.academy.hangman.states;

import backend.academy.hangman.configuration.ConfigWord;
import backend.academy.hangman.configuration.Word;

public interface State {
    void handle();

    Word secretWord();

    ConfigWord selectedConfig();

    int currentAttempts();
}
