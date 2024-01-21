import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SpellingTrainerTest {

    public List<WordImagePair> createList(){
        List<WordImagePair> wordImagePairs = new ArrayList<>();
        wordImagePairs.add(new WordImagePair("Hund", "https://www.bmel.de/SharedDocs/Bilder/DE/_Tiere/Haus-Zootiere/tierschutz-hunde.jpg?__blob=portrait&v=3"));
        wordImagePairs.add(new WordImagePair("Katze", "https://media.4-paws.org/a/5/c/4/a5c4c9cdfd3a8ecb58e9b1a5bd496c9dfbc3cedc/VIER%20PFOTEN_2020-10-07_00132-2890x2000-1920x1329.jpg"));
        wordImagePairs.add(new WordImagePair("Ball", "https://www.planet-wissen.de/natur/forschung/mathematik/wfmathematikballgjpg100~_v-gseapremiumxl.jpg"));
        return wordImagePairs;
    }
    @Test
    public void testCheckGuessCorrect() {

        //"Hund" wurde ausgewählt
        SpellingTrainer trainer = new SpellingTrainer(createList());
        trainer.setSelectedPair(new WordImagePair("Hund", "https://www.bmel.de/SharedDocs/Bilder/DE/_Tiere/Haus-Zootiere/tierschutz-hunde.jpg?__blob=portrait&v=3"));

        // Setze totalGuesses-Wert
        trainer.setTotalGuesses(5);

        // Überprüfe ob korrekt geraten wird
        assertTrue(trainer.checkGuess("Hund"));

        // Überprüft ob der totalGuesses-Wert nach einem erfolgreichen Versuch erhöht wird
        assertEquals(6, trainer.getTotalGuesses());
    }

    @Test
    public void testCheckGuessIncorrect() {
        // "Katze" wurde ausgewählt
        SpellingTrainer trainer = new SpellingTrainer(createList());
        trainer.setSelectedPair(new WordImagePair("Katze", "https://media.4-paws.org/a/5/c/4/a5c4c9cdfd3a8ecb58e9b1a5bd496c9dfbc3cedc/VIER%20PFOTEN_2020-10-07_00132-2890x2000-1920x1329.jpg"));

        // Setze totalGuesses-Wert
        trainer.setTotalGuesses(3);

        // Überprüft ob "Hund" falsch geraten wird
        assertFalse(trainer.checkGuess("Hund"));

        // Überprüft ob der totalGuesses-Wert nach einem falschen Versuch erhöht wird
        assertEquals(4, trainer.getTotalGuesses());
    }
}
