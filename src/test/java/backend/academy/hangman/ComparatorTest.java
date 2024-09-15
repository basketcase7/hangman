package backend.academy.hangman;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComparatorTest {

    @Test
    public void testCompareWithCorrect() {
        Comparator comparator = new Comparator("MAN");
        assertTrue(comparator.compare("M"));
        assertTrue(comparator.compare("A"));
        assertTrue(comparator.compare("N"));
    }

    @Test
    public void testCompareWithIncorrect() {
        Comparator comparator = new Comparator("MAN");
        assertFalse(comparator.compare("H"));
        assertFalse(comparator.compare("Z"));
        assertFalse(comparator.compare("Q"));
    }

    @Test
    public void testCheckInputWithCorrect() {
        Comparator comparator = new Comparator("MAN");
        assertTrue(comparator.checkInput("M"));
        assertTrue(comparator.checkInput("A"));
        assertTrue(comparator.checkInput("N"));
    }

    @Test
    public void testCheckInputWithIncorrect() {
        Comparator comparator = new Comparator("MAN");
        assertFalse(comparator.checkInput("Щ"));
        assertFalse(comparator.checkInput("щ"));
        assertFalse(comparator.checkInput("2"));
        assertFalse(comparator.checkInput("!"));
        assertFalse(comparator.checkInput(" "));
        assertFalse(comparator.checkInput("UUU"));
    }
}
