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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Проверка MenuState")
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

    @DisplayName("Проверка выбора уровня сложности пользователем при корректном вводе")
    @Test
    void testSelectDifficulties_ValidInput() {

        when(mockScanner.nextLine()).thenReturn("2");

        Difficulties result = menuState.selectDifficulties();

        assertEquals(Difficulties.MEDIUM, result);
    }

    @DisplayName("Проверка выбора случайного уровня сложности при некорректном вводе")
    @Test
    void testSelectDifficulties_InvalidInput() {

        when(mockScanner.nextLine()).thenReturn("invalid");

        when(mockConfigRandomizer.randomDifficulties()).thenReturn(Difficulties.HARD);

        Difficulties result = menuState.selectDifficulties();

        assertEquals(Difficulties.HARD, result);
    }

    @DisplayName("Проверка выбора категории пользователем при корректном вводе")
    @Test
    void testSelectCategories_ValidInput() {

        when(mockScanner.nextLine()).thenReturn("1");

        Categories result = menuState.selectCategories();

        assertEquals(Categories.COUNTRIES, result);
    }

    @DisplayName("Проверка выбора случайной категории при некорректном вводе")
    @Test
    void testSelectCategories_InvalidInput() {

        when(mockScanner.nextLine()).thenReturn("invalid");

        when(mockConfigRandomizer.randomCategory()).thenReturn(Categories.MUSIC);

        Categories result = menuState.selectCategories();

        assertEquals(Categories.MUSIC, result);
    }

    @DisplayName("Проверка изначального количества попыток в игре в зависимости от уровня сложности")
    @Test
    void testTakeAttempts() {

        ConfigWord configEasy = new ConfigWord(Categories.COUNTRIES, Difficulties.EASY);
        ConfigWord configMedium = new ConfigWord(Categories.COUNTRIES, Difficulties.MEDIUM);
        ConfigWord configHard = new ConfigWord(Categories.COUNTRIES, Difficulties.HARD);

        assertEquals(8, menuState.takeAttempts(configEasy));
        assertEquals(6, menuState.takeAttempts(configMedium));
        assertEquals(4, menuState.takeAttempts(configHard));
    }

    @DisplayName("Проверка инициализации всех преднастроек для игры в зависимости от категории и уровня сложности")
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

