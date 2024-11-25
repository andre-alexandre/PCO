package test;

import java.util.*;

public abstract class AbstractMastermindGame implements MastermindGame {
    protected final Random random;
    protected final int size;
    protected final Colour[] colours;
    protected Code secretCode;
    protected List<Code> trials;
    protected List<int[]> results;
    protected int numberOfTrials;
    
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
    public void startNewRound() {
        trials.clear();
        results.clear();
        numberOfTrials = 0;
        
        // Generate new secret code
        List<Colour> newSecret = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            newSecret.add(colours[random.nextInt(colours.length)]);
        }
        secretCode = new Code(newSecret);
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
    
    @Override
    public int getNumberOfTrials() {
        return numberOfTrials;
    }
    
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
    
    @Override
    public boolean wasSecretRevealed() {
        if (results.isEmpty()) return false;
        int[] lastResult = results.get(results.size() - 1);
        return lastResult[0] == size;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Number of trials: ").append(numberOfTrials).append("\n");
        sb.append("Score: ").append(score()).append("\n");
        sb.append("Secret: ").append(wasSecretRevealed() ? secretCode : "????").append("\n");
        
        // Show last 10 trials
        int start = Math.max(0, trials.size() - 10);
        for (int i = start; i < trials.size(); i++) {
            sb.append("Try ").append(i + 1).append(": ")
              .append(trials.get(i))
              .append(" -> (")
              .append(results.get(i)[0])
              .append(",")
              .append(results.get(i)[1])
              .append(")\n");
        }
        return sb.toString();
    }
    
    protected abstract boolean updateScore();
    public abstract boolean isRoundFinished();
    public abstract int score();
    
    @Override
    public Colour hint() {
        List<Colour> secretColours = secretCode.getCode();
        return secretColours.get(random.nextInt(secretColours.size()));
    }
}