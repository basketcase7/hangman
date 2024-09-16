package backend.academy.hangman.states;

import backend.academy.hangman.configuration.ConfigRandomizer;
import backend.academy.hangman.configuration.ConfigWord;
import backend.academy.hangman.configuration.Word;
import backend.academy.hangman.configuration.WordRandomizer;
import backend.academy.hangman.enums.Categories;
import backend.academy.hangman.enums.Difficulties;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Scanner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static backend.academy.hangman.OutputClass.CHOOSE_CATEGORY;
import static backend.academy.hangman.OutputClass.CHOOSE_DIFFICULTY;
import static backend.academy.hangman.OutputClass.printSmth;


@NoArgsConstructor
public class MenuState implements State {

    @Setter
    private SecureRandom random = new SecureRandom();

    @Setter
    private ConfigRandomizer configRandomizer = new ConfigRandomizer();

    @Setter
    private WordRandomizer wordRandom;

    @Setter
    private Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);

    @Getter
    private Difficulties selectedDifficulty;

    @Getter
    private Categories selectedCategory;

    @Getter
    private ConfigWord selectedConfig;

    @Getter
    private Word secretWord;

    @Getter
    private int currentAttempts;

    private final int easyAttempts = 8;
    private final int mediumAttempts = 6;
    private final int hardAttempts = 4;

    @Override
    public void handle() {

        printSmth(CHOOSE_CATEGORY);
        selectedCategory = selectCategories();
        printSmth(CHOOSE_DIFFICULTY);
        selectedDifficulty = selectDifficulties();

        selectedConfig = new ConfigWord(selectedCategory, selectedDifficulty);
        currentAttempts = takeAttempts(selectedConfig);
        WordRandomizer wordRandomizer = new WordRandomizer(random, selectedConfig);
        secretWord = wordRandomizer.randomWord();

    }

    Difficulties selectDifficulties() {
        String input = sc.nextLine().trim();
        switch (input) {
            case "1" -> selectedDifficulty = Difficulties.EASY;
            case "2" -> selectedDifficulty = Difficulties.MEDIUM;
            case "3" -> selectedDifficulty = Difficulties.HARD;
            default -> selectedDifficulty = configRandomizer.randomDifficulties();
        }
        return selectedDifficulty;
    }

    Categories selectCategories() {
        String input = sc.nextLine().trim();
        switch (input) {
            case "1" -> selectedCategory = Categories.COUNTRIES;
            case "2" -> selectedCategory = Categories.BRANDS;
            case "3" -> selectedCategory = Categories.MUSIC;
            default -> selectedCategory = configRandomizer.randomCategory();
        }
        return selectedCategory;
    }

    int takeAttempts(ConfigWord selectedConfig) {
        return switch (selectedConfig.difficulties()) {
            case EASY -> easyAttempts;
            case MEDIUM -> mediumAttempts;
            case HARD -> hardAttempts;
        };
    }
}
