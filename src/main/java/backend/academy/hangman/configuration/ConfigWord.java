package backend.academy.hangman.configuration;

import backend.academy.hangman.enums.Categories;
import backend.academy.hangman.enums.Difficulties;

public record ConfigWord(Categories categories, Difficulties difficulties) {

}
