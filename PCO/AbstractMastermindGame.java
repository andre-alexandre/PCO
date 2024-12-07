package test;
import java.util.*;

/**
 * An abstract base class to implement the variants of the "MAstermind" game.
 * This class provides core functionality for managing game rounds, 
 * the trials, the guesses, and generating the secret code. 
 *
 * @author PCO Team
 */
public abstract class AbstractMastermindGame implements MastermindGame {
	
    protected final Random random;
    protected final int size;
    protected final Colour[] colours;
    protected Code secretCode;
    protected List<Code> trials;
    protected List<int[]> results;
    protected int numberOfTrials;
    
    
    
    protected abstract boolean updateScore();
    public abstract boolean isRoundFinished();
    public abstract int score();
    
    
    
    /**
     * Constructs a new game with the given parameters.
     *
     * @param seed the seed for the random number generator
     * @param size the length of the secret code
     * @param colours the array of colours available for creating the secret code
     */
    public AbstractMastermindGame(int seed, int size, Colour[] colours) {
        this.random = new Random(seed);
        this.size = size;
        this.colours = colours.clone();
        this.trials = new ArrayList<>();
        this.results = new ArrayList<>();
        this.numberOfTrials = 0;
        startNewRound();
    }
    
    
    
    @Override
    public void play(Code x) {
        if (isRoundFinished()) return;
        
        int[] result = secretCode.howManyCorrect(x);
        trials.add(x.clone());
        results.add(result);
        numberOfTrials++;
        
        if (result[0] == size) {
            updateScore();
        }
    }
    
    
    
    /**
     * Returns the total number of trials tried.
     *
     * @return the number of trials tried
     */
    @Override
    public int getNumberOfTrials() {
        return numberOfTrials;
    }
    
    
    
    /**
     * Return the best trial.
     *
     * @return the best trial
     */
    @Override
    public Code bestTrial() {
        if (trials.isEmpty()) return null;
        
        int bestIndex = 0;
        int[] bestResult = results.get(0);
        
        for (int i = 1; i < trials.size(); i++) {
            int[] currentResult = results.get(i);
            if (currentResult[0] > bestResult[0] || 
                (currentResult[0] == bestResult[0] && currentResult[1] > bestResult[1])) {
                bestResult = currentResult;
                bestIndex = i;
            }
        }
        
        return trials.get(bestIndex).clone();
    }
    
    
    
    /**
    * Says if the secret code has been revealed in the play.
    *
    * @return true if the secret code has been revealed, false otherwise
    */
    @Override
    public boolean wasSecretRevealed() {
        if (results.isEmpty()) return false;
        int[] lastResult = results.get(results.size() - 1);
        return lastResult[0] == size;
    }
    
    
    
    /**
    * Returns a string representation of the current game state. 
    * Includes the number of trials and the results of trials, the 
    * score and the secret code if revealed.
    *
    * @return a string representation of the game
    */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Number of Trials = ").append(numberOfTrials).append("\n");
        sb.append("Score = ").append(score()).append("\n");

        if (wasSecretRevealed()) {
            sb.append(secretCode); 
        } else {
            sb.append("[");
            for (int i = 0; i < size; i++) {
            	if (i==0) {
                    sb.append("?");
            	}
            	else {
                    sb.append(" ?");
            	}
                if (i < size - 1) {
                    sb.append(",");
                }
            }
            sb.append("]"); 
        }  
        
        int start = Math.max(0, trials.size() - 10);
        sb.append("\n \n"); 
        for (int i = start; i < trials.size(); i++) {
            sb.append(trials.get(i))
              .append("    ")
              .append(results.get(i)[0])
              .append(" ")
              .append(results.get(i)[1])
              .append("\n");
        }
        return sb.toString();
    }
    
    
    
    /**
     * Returns an color hint from the secret code.
     *
     * @return a random color hint from the secret code
     */
    @Override
    public Colour hint() {
        List<Colour> secretColours = secretCode.getCode();
        return secretColours.get(random.nextInt(secretColours.size()));
    }
    
    
    
    /**
     * Cleans previous trials and results and generates a new secret code.
     */
    @Override
    public void startNewRound() {
        trials.clear();
        results.clear();
        numberOfTrials = 0;
        
        List<Colour> newSecret = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            newSecret.add(colours[random.nextInt(colours.length)]);
        }
        secretCode = new Code(newSecret);
    }
}