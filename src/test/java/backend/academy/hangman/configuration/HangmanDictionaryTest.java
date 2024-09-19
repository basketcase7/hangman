package backend.academy.hangman.configuration;

import backend.academy.hangman.enums.Categories;
import backend.academy.hangman.enums.Difficulties;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Проверка HangmanDictionary")
public class HangmanDictionaryTest {
    @DisplayName("Проверка инициализации словаря")
    @Test
    void testInitCreatesExpectedDictionary() {

        Map<ConfigWord, List<Word>> expectedDictionary = new HashMap<>();

        ConfigWord configWord1 = new ConfigWord(Categories.COUNTRIES, Difficulties.EASY);
        ConfigWord configWord2 = new ConfigWord(Categories.COUNTRIES, Difficulties.MEDIUM);
        ConfigWord configWord3 = new ConfigWord(Categories.COUNTRIES, Difficulties.HARD);

        ConfigWord configWord4 = new ConfigWord(Categories.BRANDS, Difficulties.EASY);
        ConfigWord configWord5 = new ConfigWord(Categories.BRANDS, Difficulties.MEDIUM);
        ConfigWord configWord6 = new ConfigWord(Categories.BRANDS, Difficulties.HARD);

        ConfigWord configWord7 = new ConfigWord(Categories.MUSIC, Difficulties.EASY);
        ConfigWord configWord8 = new ConfigWord(Categories.MUSIC, Difficulties.MEDIUM);
        ConfigWord configWord9 = new ConfigWord(Categories.MUSIC, Difficulties.HARD);

        List<Word> easyCountries = List.of(
            new Word("USA", "Country in North America"),
            new Word("RUSSIA", "The biggest country in the world")
        );

        List<Word> mediumCountries = List.of(
            new Word("AUSTRALIA", "\"Reversed country\""),
            new Word("CHINA", "A lot of people")
        );

        List<Word> hardCountries = List.of(
            new Word("ALGERIA", "Country in Africa"),
            new Word("BELGIUM", "Country in Europe")
        );

        List<Word> easyBrands = List.of(
            new Word("ADIDAS", "Sport"),
            new Word("GUCCI", "Luxury brand")
        );
        List<Word> mediumBrands = List.of(
            new Word("INTEL", "CPU"),
            new Word("APPLE", "Fruit")
        );
        List<Word> hardBrands = List.of(
            new Word("NVIDIA", "GPU"),
            new Word("MARS", "Chocolate")
        );
        List<Word> easyMusic = List.of(
            new Word("ROCK", "Noise"),
            new Word("DUBSTEP", "Electronics")
        );
        List<Word> mediumMusic = List.of(
            new Word("JAZZ", "Harmony"),
            new Word("BLUES", "Secular")
        );
        List<Word> hardMusic = List.of(
            new Word("GRUNGE", "Dirty"),
            new Word("FOLK", "National")
        );

        expectedDictionary.put(configWord1, easyCountries);
        expectedDictionary.put(configWord2, mediumCountries);
        expectedDictionary.put(configWord3, hardCountries);

        expectedDictionary.put(configWord4, easyBrands);
        expectedDictionary.put(configWord5, mediumBrands);
        expectedDictionary.put(configWord6, hardBrands);

        expectedDictionary.put(configWord7, easyMusic);
        expectedDictionary.put(configWord8, mediumMusic);
        expectedDictionary.put(configWord9, hardMusic);

        HangmanDictionary actualDictionary = HangmanDictionary.init();

        assertEquals(expectedDictionary, actualDictionary.dictionary(),
            "The initialized dictionary does not match the expected dictionary.");
    }

    @DisplayName("Проверка списка слов по выбранному уровню сложности и категории")
    @Test
    void testInitHasExpectedWordsForCategoryAndDifficulty() {
        HangmanDictionary dictionary = HangmanDictionary.init();

        ConfigWord countriesEasy = new ConfigWord(Categories.COUNTRIES, Difficulties.EASY);
        List<Word> easyCountries = dictionary.dictionary().get(countriesEasy);
        assertTrue(easyCountries.contains(new Word("USA", "Country in North America")),
            "The word does not correspond to the category or level of difficulty");
        assertTrue(easyCountries.contains(new Word("RUSSIA", "The biggest country in the world")),
            "The word does not correspond to the category or level of difficulty");

        ConfigWord brandsMedium = new ConfigWord(Categories.BRANDS, Difficulties.MEDIUM);
        List<Word> mediumBrands = dictionary.dictionary().get(brandsMedium);
        assertTrue(mediumBrands.contains(new Word("INTEL", "CPU")),
            "The word does not correspond to the category or level of difficulty");
        assertTrue(mediumBrands.contains(new Word("APPLE", "Fruit")),
            "The word does not correspond to the category or level of difficulty");

        ConfigWord musicHard = new ConfigWord(Categories.MUSIC, Difficulties.HARD);
        List<Word> hardMusic = dictionary.dictionary().get(musicHard);
        assertTrue(hardMusic.contains(new Word("GRUNGE", "Dirty")),
            "The word does not correspond to the category or level of difficulty");
        assertTrue(hardMusic.contains(new Word("FOLK", "National")),
            "The word does not correspond to the category or level of difficulty");

    }

}
