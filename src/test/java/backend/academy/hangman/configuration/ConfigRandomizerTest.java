package backend.academy.hangman.configuration;

import backend.academy.hangman.enums.Categories;
import backend.academy.hangman.enums.Difficulties;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConfigRandomizerTest {

    private final ConfigRandomizer randomizer = new ConfigRandomizer();

    @Test
    void testRandomCategory() {

        Set<Categories> categoriesSet = new HashSet<>();
        int numTests = 100;

        for (int i = 0; i < numTests; i++) {
            Categories category = randomizer.randomCategory();
            categoriesSet.add(category);
        }

        assertTrue(categoriesSet.containsAll(Set.of(Categories.values())),
            "The method does not return all possible categories");

    }

    @Test
    void testRandomDifficultyLevel() {

        Set<Difficulties> difficultiesSet = new HashSet<>();
        int numTests = 100;

        for (int i = 0; i < numTests; i++) {
            Difficulties difficulty = randomizer.randomDifficulties();
            difficultiesSet.add(difficulty);
        }

        assertTrue(difficultiesSet.containsAll(Set.of(Difficulties.values())),
            "The method does not return all possible difficulty levels");

    }

}
