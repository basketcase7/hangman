package backend.academy.hangman;

/**
 * Класс, необходимый для обработки введенной буквы
 */
public class Comparator {

    private final String word;

    public Comparator(String word) {
        this.word = word;
    }

    /**
     * Проверяет, есть ли в загаданном слове введенная буква
     *
     * @param letter введенная буква
     * @return true, если введенная буква есть в слове
     */
    public boolean compare(String letter) {
        return word.contains(letter.toUpperCase());
    }

    /**
     * Проверяет, является ли введенная буква символом английского алфавита
     *
     * @param input введенная буква
     * @return true, если введенная буква - символ английского алфавита
     */
    public boolean checkInput(String input) {
        String inputUpperCase = input.toUpperCase();
        return inputUpperCase.matches("[A-Z]+") && input.length() == 1;
    }
}
