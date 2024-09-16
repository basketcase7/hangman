package backend.academy.hangman.states;

import backend.academy.hangman.configuration.Word;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StateHandlerTest {

    @Test
    public void testEmptySecretWordThrowsException() throws Exception {

        MenuState menuState = mock(MenuState.class);
        when(menuState.secretWord()).thenReturn(new Word("", ""));

        StateHandler stateHandler = new StateHandler();

        stateHandler.menuState = menuState;

        Exception exception = assertThrows(Exception.class, () -> {
            stateHandler.checkLength();
        });

        assertEquals("The selected word has an incorrect length", exception.getMessage());
    }

    @Test
    public void testLongSecretWordThrowsException() throws Exception {

        MenuState menuState = mock(MenuState.class);
        when(menuState.secretWord()).thenReturn(new Word("HAHAHAHAHAHAHAHAHHAHAHAH", ""));

        StateHandler stateHandler = new StateHandler();

        stateHandler.menuState = menuState;

        Exception exception = assertThrows(Exception.class, () -> {
            stateHandler.checkLength();
        });

        assertEquals("The selected word has an incorrect length", exception.getMessage());
    }

    @Test
    public void testValidSecretWordDoesNotThrowException() throws Exception {

        MenuState menuState = mock(MenuState.class);

        when(menuState.secretWord()).thenReturn(new Word("USA", "The biggest country in the world"));

        StateHandler stateHandler = new StateHandler();

        stateHandler.menuState = menuState;

        assertDoesNotThrow(() -> {
            stateHandler.checkLength();
        });
    }
}
