package backend.academy.hangman.states;

import static backend.academy.hangman.OutputClass.OPEN_MESSAGE;
import static backend.academy.hangman.OutputClass.printSmth;

public class StateHandler {

    State menuState;
    State gameState;
    State resultState;
    private static final int MAX_LENGTH = 20;

    public void start() throws Exception {
        printSmth(OPEN_MESSAGE);
        menuState = new MenuState();
        menuState.handle();
        checkLength();
        gameState = new GameState(menuState.secretWord(), menuState.selectedConfig(), menuState.currentAttempts());
        gameState.handle();
        resultState = new ResultState(gameState.secretWord(), gameState.selectedConfig(), gameState.currentAttempts());
        resultState.handle();
    }

    public void checkLength() throws Exception {
        if (menuState.secretWord().word().isEmpty() || menuState.secretWord().word().length() > MAX_LENGTH) {
            throw new Exception("The selected word has an incorrect length");
        }
    }
}
