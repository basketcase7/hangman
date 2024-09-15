package backend.academy.hangman.configuration;

import java.security.SecureRandom;
import java.util.List;

public class WordRandomizer {
    private final SecureRandom random;
    private final HangmanDictionary dictionary = HangmanDictionary.init();

    private final ConfigWord configWord;

    public WordRandomizer(SecureRandom random, ConfigWord configWord) {
        this.random = random;
        this.configWord = configWord;
    }

    public Word randomWord() {
        List<Word> words = dictionary.dictionary().get(configWord);
        return words.get(random.nextInt(words.size()));
    }
}
