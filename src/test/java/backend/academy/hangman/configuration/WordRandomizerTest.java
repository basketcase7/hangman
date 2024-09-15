package backend.academy.hangman.configuration;

import backend.academy.hangman.enums.Categories;
import backend.academy.hangman.enums.Difficulties;
import java.security.SecureRandom;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WordRandomizerTest {

    @Mock
    private SecureRandom random;

    @InjectMocks
    private WordRandomizer wordRandomizer;

    @Test
    void testRandomWordCountriesEasy() {

        ConfigWord configWord = new ConfigWord(Categories.COUNTRIES, Difficulties.EASY);
        List<Word> words = HangmanDictionary.init().dictionary().get(configWord);

        when(random.nextInt(words.size())).thenReturn(1);
        wordRandomizer = new WordRandomizer(random, configWord);
        Word actualWord = wordRandomizer.randomWord();
        Word expectedWord = new Word("RUSSIA", "The biggest country in the world");

        assertThat(actualWord).isEqualTo(expectedWord);
    }

    @Test
    void testRandomWordBrandsMedium() {

        ConfigWord configWord = new ConfigWord(Categories.BRANDS, Difficulties.MEDIUM);
        List<Word> words = HangmanDictionary.init().dictionary().get(configWord);

        when(random.nextInt(words.size())).thenReturn(0);
        wordRandomizer = new WordRandomizer(random, configWord);
        Word actualWord = wordRandomizer.randomWord();
        Word expectedWord = new Word("INTEL", "CPU");

        assertThat(actualWord).isEqualTo(expectedWord);
    }

    @Test
    void testRandomWordMusicHard() {

        ConfigWord configWord = new ConfigWord(Categories.MUSIC, Difficulties.HARD);
        List<Word> words = HangmanDictionary.init().dictionary().get(configWord);

        when(random.nextInt(words.size())).thenReturn(0);
        wordRandomizer = new WordRandomizer(random, configWord);
        Word actualWord = wordRandomizer.randomWord();
        Word expectedWord = new Word("GRUNGE", "Dirty");

        assertThat(actualWord).isEqualTo(expectedWord);
    }

}
