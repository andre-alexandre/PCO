package test;

/**
 * An implementation of the "Mastermind" game for the "Multi Colour" variant.
 * This class defines the specific hinting and scoring rules of the game.
 *
 * @author PCO Team
 */
public class MultiColourMastermindGame extends AbstractMastermindGame {

    private int currentScore;
    private int hintsUsedInRound;
    
    
    
    public MultiColourMastermindGame(int seed, int size, Colour[] colours) {
        super(seed, size, colours);
        this.currentScore = 0;
        this.hintsUsedInRound = 0;
    }
    
    
    
    @Override
    public int score() {
        return currentScore;
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
    public boolean isRoundFinished() {
        return wasSecretRevealed() || numberOfTrials >= MAX_TRIALS;
    }
    
    
    
    @Override
    public Colour hint() {
        hintsUsedInRound++;
        return super.hint();
    }
}
