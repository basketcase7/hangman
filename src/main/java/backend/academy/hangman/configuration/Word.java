package backend.academy.hangman.configuration;

/**
 * Record с информацией о слове и подсказке
 *
 * @param word содержит слово
 * @param help содержит подсказку
 */
public record Word(String word, String help) {

}
