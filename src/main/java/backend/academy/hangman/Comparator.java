package backend.academy.hangman;

public class Comparator {

    private final String word;

    public Comparator(String word) {
        this.word = word;
    }

    public boolean compare(String letter) {
        return word.contains(letter.toUpperCase());
    }

    public boolean checkInput(String input) {
        String inputUpperCase = input.toUpperCase();
        return inputUpperCase.matches("[A-Z]+") && input.length() == 1;
    }
}
