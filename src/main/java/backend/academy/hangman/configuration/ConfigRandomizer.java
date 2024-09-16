package backend.academy.hangman.configuration;

import backend.academy.hangman.enums.Categories;
import backend.academy.hangman.enums.Difficulties;
import java.security.SecureRandom;

/**
 * Класс, в котором случайным образом выбирается категория слов и уровень сложности, если этого не сделал пользователь
 */
@SuppressWarnings("JavadocStyle")
public class ConfigRandomizer {

    private final SecureRandom random = new SecureRandom();

    /**
     * @return Категория
     */
    public Categories randomCategory() {
        Categories[] categories = Categories.values();
        return categories[random.nextInt(categories.length)];
    }

    /**
     * @return Уровень сложности
     */
    public Difficulties randomDifficulties() {
        Difficulties[] difficulties = Difficulties.values();
        return difficulties[random.nextInt(difficulties.length)];
    }
}
