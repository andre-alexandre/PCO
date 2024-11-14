package test;

public interface MastermindGame {
	
	public static int MAX_TRIALS = 25;
    
	public void play(Code trial);
	
	public boolean isRoundFinished();
	
    public void startNewRound();
    
    public Code bestTrial();
    
    public Colour hint();
    
    public int getNumberOfTrials();
    
    public int score();
    
    public boolean wasSecretRevealed();
}
