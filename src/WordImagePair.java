import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Klasse für Wort-Bild-Paare
class WordImagePair {
    private String word;
    private String imageUrl;

    public WordImagePair(String word, String imageUrl) {
        this.word = word;
        this.imageUrl = imageUrl;
    }

    public String getWord() {
        return word;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}