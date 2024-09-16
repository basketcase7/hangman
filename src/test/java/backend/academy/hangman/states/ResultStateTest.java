package backend.academy.hangman.states;

import backend.academy.hangman.OutputClass;
import backend.academy.hangman.configuration.ConfigWord;
import backend.academy.hangman.configuration.Word;
import backend.academy.hangman.enums.Categories;
import backend.academy.hangman.enums.Difficulties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResultStateTest {

    private ResultState resultState;

    @Mock
    private Word secretWord;

    @Mock
    private ConfigWord selectedConfig;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        secretWord = mock(Word.class);
        selectedConfig = mock(ConfigWord.class);

        when(secretWord.word()).thenReturn("USA");
        when(secretWord.help()).thenReturn("Country in North America");
        when(selectedConfig.categories()).thenReturn(Categories.COUNTRIES);
        when(selectedConfig.difficulties()).thenReturn(Difficulties.EASY);

        resultState = new ResultState(secretWord, selectedConfig, 8);
    }

    @Test
    public void testCheckResultWin() {

        try (MockedStatic<OutputClass> mockedStatic = Mockito.mockStatic(OutputClass.class)) {

            resultState.currentAttempts(3);

            resultState.handle();

            String expectedMessage = "\n**************************************\n"
                + "Congratulations! You have won!\n"
                + "The hidden word was "
                + secretWord.word()
                + " in "
                + selectedConfig.categories()
                + " category and "
                + selectedConfig.difficulties()
                + " difficulty level.\n"
                + "You have "
                + resultState.currentAttempts()
                + " attempts left!\n"
                + "**************************************";
            mockedStatic.verify(() -> OutputClass.printSmth(expectedMessage));
        }
    }

    @Test
    public void testCheckResultLose() {

        try (MockedStatic<OutputClass> mockedStatic = Mockito.mockStatic(OutputClass.class)) {

            resultState.currentAttempts(0);

            resultState.handle();

            String expectedMessage = "\n**************************************\n"
                + "You have lost!\n"
                + "The hidden word was "
                + secretWord.word()
                + " in "
                + selectedConfig.categories()
                + " category and "
                + selectedConfig.difficulties()
                + " difficulty level.\n"
                + "**************************************";
            mockedStatic.verify(() -> OutputClass.printSmth(expectedMessage));
        }
    }
}
