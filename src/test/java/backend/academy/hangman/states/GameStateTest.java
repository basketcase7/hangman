package backend.academy.hangman.states;

import backend.academy.hangman.Comparator;
import backend.academy.hangman.configuration.ConfigWord;
import backend.academy.hangman.configuration.Word;
import backend.academy.hangman.enums.Categories;
import backend.academy.hangman.enums.Difficulties;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameStateTest {

    private Word secretWord;
    private ConfigWord selectedConfig;
    private GameState gameState;
    @Mock
    private Comparator comparator;
    @Mock
    private Scanner mockScanner;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        secretWord = mock(Word.class);
        selectedConfig = mock(ConfigWord.class);

        when(secretWord.word()).thenReturn("USA");
        when(secretWord.help()).thenReturn("Country in North America");
        when(selectedConfig.difficulties()).thenReturn(Difficulties.EASY);
        when(selectedConfig.categories()).thenReturn(Categories.COUNTRIES);

        gameState = new GameState(secretWord, selectedConfig, 8);
    }

    @Test
    public void testInitialization() {

        assertEquals("USA", secretWord.word());
        assertEquals("Country in North America", secretWord.help());
        assertEquals(Difficulties.EASY, selectedConfig.difficulties());
        assertEquals(Categories.COUNTRIES, selectedConfig.categories());
        assertEquals(8, gameState.currentAttempts());

    }

    @Test
    public void testCheckAnswerCorrectGuess() {

        String[] wordMassive = new String[] {"_", "_", "_"};
        List<String> incorrectWords = new ArrayList<>();

        when(mockScanner.nextLine()).thenReturn("U");
        when(comparator.compare("U")).thenReturn(true);
        when(comparator.checkInput("U")).thenReturn(true);

        gameState.checkAnswer("U", wordMassive, secretWord.word(), comparator, incorrectWords);

        assertArrayEquals(new String[] {"U", "_", "_"}, wordMassive);
        assertEquals(8, gameState.currentAttempts());
        assertEquals(0, incorrectWords.size());
    }

    @Test
    public void testGiveHelpWithLowAttempts() {

        gameState.currentAttempts(2);

        gameState.giveHelp();

        assertEquals(gameState.giveHelp(), "Help: Country in North America");
    }

    @Test
    public void testCheckAnswerIncorrectGuess() {

        when(mockScanner.nextLine()).thenReturn("G");
        when(comparator.compare("G")).thenReturn(false);
        when(comparator.checkInput("G")).thenReturn(true);

        String[] wordMassive = new String[] {"_", "_", "_"};
        List<String> incorrectWords = new ArrayList<>();

        gameState.checkAnswer("G", wordMassive, secretWord.word(), comparator, incorrectWords);

        assertArrayEquals(new String[] {"_", "_", "_"}, wordMassive);
        assertEquals(7, gameState.currentAttempts());
        assertEquals(1, incorrectWords.size());
        assertTrue(incorrectWords.contains("G"));
    }

}
