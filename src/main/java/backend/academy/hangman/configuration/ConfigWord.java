package backend.academy.hangman.configuration;

import backend.academy.hangman.enums.Categories;
import backend.academy.hangman.enums.Difficulties;

/**
 * Record с информацией о конфигурации игры
 *
 * @param categories   категория слов
 * @param difficulties уровень сложности
 */
public record ConfigWord(Categories categories, Difficulties difficulties) {

}
