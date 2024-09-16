package backend.academy;

import backend.academy.hangman.states.StateHandler;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) throws Exception {

        StateHandler stateHandler = new StateHandler();
        stateHandler.start();

    }
}
