package test;

public class MultiColourMastermindGame extends AbstractMastermindGame {
    private int currentScore;
    private int hintsUsedInRound;
    
    public MultiColourMastermindGame(int seed, int size, Colour[] colours) {
        super(seed, size, colours);
        this.currentScore = 0;
        this.hintsUsedInRound = 0;
    }
    
    @Override
    public boolean isRoundFinished() {
        return wasSecretRevealed() || numberOfTrials >= MAX_TRIALS;
    }
    
    @Override
    public boolean updateScore() {
        if (wasSecretRevealed()) {
            int roundScore;
            if (numberOfTrials <= 2) {
                roundScore = 100;
            } else if (numberOfTrials <= 5) {
                roundScore = 50;
            } else {
                roundScore = 20;
            }
            currentScore += roundScore / (hintsUsedInRound + 1);
            return true;
        }
        return false;
    }
    
    @Override
    public int score() {
        return currentScore;
    }
    
    @Override
    public Colour hint() {
        hintsUsedInRound++;
        return super.hint();
    }
    
    @Override
    public void startNewRound() {
        super.startNewRound();
        hintsUsedInRound = 0;
    }
}
