package test;
import java.util.List;

/**
 * Represents a code sequence specifically for the Bulls and Cows game variant.
 * This class provides a specialized implementation of colour matching logic.
 *
 * @author PCO Team
 */
public class BullsAndCowsCode extends Code {
    
	
	
    /**
     * Creates a new BullsAndCowsCode with the given sequence of binary colours.
     *
     * @param code A list of BinaryColour values
     */
    public BullsAndCowsCode(List<BinaryColour> code) {
        super(code);
    }
    
    

    /**
     * Specialized implementation of colour matching for the Bulls and Cows variant.
     * Compares this code with another code and returns how many bulls and cows/black 
     * and whites are in the wrong position.
     * The returned array contains:
     * array[0] - number of bulls/black in wrong positions;
     * array[1] - number of cows/white in wrong positions.
     *
     * @param other The code to compare with
     * @return An array containing two integers
     */
    @Override
    public int[] howManyCorrect(Code other) {
        int[] result = new int[2];
        List<Colour> otherCode = other.getCode();
        
        for (int i = 0; i < code.size(); i++) {
            if (code.get(i).equals(otherCode.get(i))) {
                result[0]++;
            }
        }
        
        int blackInSecret = 0, blackInGuess = 0;
        for (int i = 0; i < code.size(); i++) {
            if (code.get(i) == BinaryColour.BLACK) blackInSecret++;
            if (otherCode.get(i) == BinaryColour.BLACK) blackInGuess++;
        }
        
        int totalMatches = Math.min(blackInSecret, blackInGuess) + 
                          Math.min(code.size() - blackInSecret, otherCode.size() - blackInGuess);
        result[1] = totalMatches - result[0];
        
        return result;
    }
}