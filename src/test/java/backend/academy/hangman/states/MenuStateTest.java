package backend.academy.hangman.states;

import backend.academy.hangman.configuration.ConfigRandomizer;
import backend.academy.hangman.configuration.ConfigWord;
import backend.academy.hangman.configuration.Word;
import backend.academy.hangman.configuration.WordRandomizer;
import backend.academy.hangman.enums.Categories;
import backend.academy.hangman.enums.Difficulties;
import java.security.SecureRandom;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MenuStateTest {

    @InjectMocks
    private MenuState menuState;

    @Mock
    private SecureRandom mockRandom;

    @Mock
    private ConfigRandomizer mockConfigRandomizer;

    @Mock
    private WordRandomizer mockWordRandomizer;

    @Mock
    private Scanner mockScanner;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        menuState.random(mockRandom);
        menuState.configRandomizer(mockConfigRandomizer);
        menuState.wordRandom(mockWordRandomizer);
    }

    @Test
    void testSelectDifficulties_ValidInput() {

        when(mockScanner.nextLine()).thenReturn("2");

        Difficulties result = menuState.selectDifficulties();

        assertEquals(Difficulties.MEDIUM, result);
    }

    @Test
    void testSelectDifficulties_InvalidInput() {

        when(mockScanner.nextLine()).thenReturn("invalid");

        when(mockConfigRandomizer.randomDifficulties()).thenReturn(Difficulties.HARD);

        Difficulties result = menuState.selectDifficulties();

        assertEquals(Difficulties.HARD, result);
    }

    @Test
    void testSelectCategories_ValidInput() {

        when(mockScanner.nextLine()).thenReturn("1");

        Categories result = menuState.selectCategories();

        assertEquals(Categories.COUNTRIES, result);
    }

    @Test
    void testSelectCategories_InvalidInput() {

        when(mockScanner.nextLine()).thenReturn("invalid");

        when(mockConfigRandomizer.randomCategory()).thenReturn(Categories.MUSIC);

        Categories result = menuState.selectCategories();

        assertEquals(Categories.MUSIC, result);
    }

    @Test
    void testTakeAttempts() {

        ConfigWord configEasy = new ConfigWord(Categories.COUNTRIES, Difficulties.EASY);
        ConfigWord configMedium = new ConfigWord(Categories.COUNTRIES, Difficulties.MEDIUM);
        ConfigWord configHard = new ConfigWord(Categories.COUNTRIES, Difficulties.HARD);

        assertEquals(8, menuState.takeAttempts(configEasy));
        assertEquals(6, menuState.takeAttempts(configMedium));
        assertEquals(4, menuState.takeAttempts(configHard));
    }

    @Test
    void testHandle() {

        when(mockScanner.nextLine()).thenReturn("1").thenReturn("1");
        when(mockConfigRandomizer.randomDifficulties()).thenReturn(Difficulties.EASY);
        when(mockConfigRandomizer.randomCategory()).thenReturn(Categories.COUNTRIES);

        Word mockWord = new Word("USA", "Country in North America");

        when(mockWordRandomizer.randomWord()).thenReturn(mockWord);

        menuState.handle();

        assertEquals(Categories.COUNTRIES, menuState.selectedCategory());
        assertEquals(Difficulties.EASY, menuState.selectedDifficulty());
        assertEquals(8, menuState.currentAttempts());
        assertEquals(mockWord, menuState.secretWord());
    }
}

