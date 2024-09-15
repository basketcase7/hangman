package backend.academy.hangman.configuration;

import java.security.SecureRandom;
import java.util.List;

public class WordRandomizer {
    private final SecureRandom random = new SecureRandom();
    private final HangmanDictionary dictionary = HangmanDictionary.init();

    private final ConfigWord configWord;

    public WordRandomizer(ConfigWord configWord) {
        this.configWord = configWord;
    }

    public Word randomWord() {
        List<Word> words = dictionary.dictionary().get(configWord);
        return words.get(random.nextInt(words.size()));
    }
}
