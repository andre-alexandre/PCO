package test;
import java.util.*;


public class BullsAndCows extends AbstractMastermindGame {
    private int currentScore;
    
    public BullsAndCows(int seed, int size, Colour[] colours) {
        super(seed, size, colours);
        this.currentScore = 0;
    }
    
    @Override
    public boolean isRoundFinished() {
        return wasSecretRevealed() || numberOfTrials >= MAX_TRIALS;
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
    public int score() {
        return currentScore;
    }
    
    @Override
    public Colour hint() {
        currentScore /= 2;
        return super.hint();
    }

    @Override
    public void startNewRound() {
        // Reset trials and results for the new round
        trials.clear();
        results.clear();
        numberOfTrials = 0;
        
        // Generate new secret code
        List<Colour> newSecret = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            newSecret.add(colours[random.nextInt(colours.length)]);
        }
        secretCode = new BullsAndCowsCode(castList(newSecret, BinaryColour.class));
    }
    
    @SuppressWarnings("unchecked")
    private static <T> List<T> castList(List<?> list, Class<T> clazz) {
        return (List<T>) list;
    }
}