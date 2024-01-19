import java.util.List;
import java.util.Random;
class SpellingTrainer {
    private List<WordImagePair> wordImagePairs;
    private WordImagePair selectedPair;
    private int totalGuesses;
    private int correctGuesses;

    public SpellingTrainer(List<WordImagePair> wordImagePairs) {
        this.wordImagePairs = wordImagePairs;
        this.totalGuesses = 0;
        this.correctGuesses = 0;
        this.selectedPair = null;
    }

    public void selectRandomPair() {
        Random random = new Random();
        selectedPair = wordImagePairs.get(random.nextInt(wordImagePairs.size()));
    }

    public boolean checkGuess(String guess) {
        totalGuesses++;
        boolean isCorrect = guess.toLowerCase().equals(selectedPair.getWord().toLowerCase());
        if (isCorrect) {
            correctGuesses++;
            selectedPair = null;
        }
        return isCorrect;
    }

    public int getTotalGuesses() {
        return totalGuesses;
    }

    public int getCorrectGuesses() {
        return correctGuesses;
    }

    public void setTotalGuesses(int totalGuesses) {
        this.totalGuesses = totalGuesses;
    }

    public void setCorrectGuesses(int correctGuesses) {
        this.correctGuesses = correctGuesses;
    }

    public WordImagePair getSelectedPair() {
        return selectedPair;
    }

    public void setSelectedPair(WordImagePair selectedPair) {
        this.selectedPair = selectedPair;
    }
}