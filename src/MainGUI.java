import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainGUI {

    private static final String PERSISTENCE_PATH = "/Users/niklaskisser/Desktop/GPT_Test/src/data.json";

    public static void main(String[] args) throws IOException {
        List<WordImagePair> wordImagePairs = new ArrayList<>();
        wordImagePairs.add(new WordImagePair("Hund", "https://www.bmel.de/SharedDocs/Bilder/DE/_Tiere/Haus-Zootiere/tierschutz-hunde.jpg?__blob=portrait&v=3"));
        wordImagePairs.add(new WordImagePair("Katze", "https://media.4-paws.org/a/5/c/4/a5c4c9cdfd3a8ecb58e9b1a5bd496c9dfbc3cedc/VIER%20PFOTEN_2020-10-07_00132-2890x2000-1920x1329.jpg"));
        wordImagePairs.add(new WordImagePair("Ball", "https://www.planet-wissen.de/natur/forschung/mathematik/wfmathematikballgjpg100~_v-gseapremiumxl.jpg"));

        SpellingTrainer trainer = new SpellingTrainer(wordImagePairs);

        JSONObject persistedData = new JSONService().read(PERSISTENCE_PATH);
        if (persistedData != null) {
            trainer.setTotalGuesses(((Long) persistedData.get("rounds")).intValue());
            trainer.setCorrectGuesses(((Long) persistedData.get("score")).intValue());
            trainer.setSelectedPair(new WordImagePair((String) persistedData.get("word"), (String) persistedData.get("url")));
        }

        while (true) {
            if (persistedData == null) {
                trainer.selectRandomPair();
                WordImagePair selectedPair = trainer.getSelectedPair();
            }

            String message = "Statistik: Gesamtversuche - " + trainer.getTotalGuesses() +
                    ", Richtig - " + trainer.getCorrectGuesses();

            if (selectedPair != null) {

                Image image = ImageIO.read(new URL(selectedPair.getImageUrl()));
                BufferedImage resizedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);


                Graphics2D graphics2D = resizedImage.createGraphics();
                graphics2D.drawImage(image, 0, 0, 200, 200, null);
                graphics2D.dispose();


                ImageIcon icon = new ImageIcon(resizedImage);

                JOptionPane.showMessageDialog(null, icon);

                String userInput = JOptionPane.showInputDialog(message + "\nGib das Wort ein:");


                if (userInput != null && !userInput.isEmpty()) {
                    if (trainer.checkGuess(userInput)) {
                        JOptionPane.showMessageDialog(null, "Richtig!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Falsch!");
                    }
                } else {
                    new JSONService().save(PERSISTENCE_PATH, trainer.getTotalGuesses(), trainer.getCorrectGuesses(), trainer.getSelectedPair().getImageUrl(), trainer.getSelectedPair().getWord());
                    break;
                }
            }
        }
    }
}
