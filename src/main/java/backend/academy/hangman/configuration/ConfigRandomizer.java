package backend.academy.hangman.configuration;

import backend.academy.hangman.enums.Categories;
import backend.academy.hangman.enums.Difficulties;
import java.security.SecureRandom;

public class ConfigRandomizer {

    private final SecureRandom random = new SecureRandom();

    public Categories randomCategory() {
        Categories[] categories = Categories.values();
        return categories[random.nextInt(categories.length)];
    }

    public Difficulties randomDifficulties() {
        Difficulties[] difficulties = Difficulties.values();
        return difficulties[random.nextInt(difficulties.length)];
    }
}
