package backend.academy.hangman.configuration;

import backend.academy.hangman.enums.Categories;
import backend.academy.hangman.enums.Difficulties;
import java.util.HashMap;
import java.util.List;

public record HangmanDictionary(HashMap<ConfigWord, List<Word>> dictionary) {

    public static HangmanDictionary init() {

        HashMap<ConfigWord, List<Word>> dictionary = new HashMap<>();

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

        dictionary.put(configWord1, easyCountries);
        dictionary.put(configWord2, mediumCountries);
        dictionary.put(configWord3, hardCountries);

        dictionary.put(configWord4, easyBrands);
        dictionary.put(configWord5, mediumBrands);
        dictionary.put(configWord6, hardBrands);

        dictionary.put(configWord7, easyMusic);
        dictionary.put(configWord8, mediumMusic);
        dictionary.put(configWord9, hardMusic);

        return new HangmanDictionary(dictionary);
    }
}
