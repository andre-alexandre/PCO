package test;

/**
 * An implementation of the "Mastermind" game for the "Bulls and Cows" variant.
 * This class defines the specific hinting and scoring rules of the game.
 *
 * @author PCO Team
 */
public class BullsAndCows extends AbstractMastermindGame {
	
    private int currentScore;
    
    
    
    public BullsAndCows(int seed, int size, Colour[] colours) {
        super(seed, size, colours);
        this.currentScore = 0;
    }
    
    
    
    @Override
    public int score() {
        return currentScore;
    }
    
    
    
    @Override
    public boolean updateScore() {
        if (wasSecretRevealed()) {
            currentScore += 2000;
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
        this.currentScore /= 2;
        return super.hint();
    }    
}