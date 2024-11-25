package test;
import java.util.List;

/**
 * Represents a code sequence specifically for the Bulls and Cows game variant.
 * This class provides a specialized implementation of color matching logic.
 *
 * @author PCO Team
 */
public class BullsAndCowsCode extends Code {
    
    /**
     * Creates a new BullsAndCowsCode with the given sequence of binary colors.
     *
     * @param code A list of BinaryColour values
     */
    public BullsAndCowsCode(List<BinaryColour> code) {
        super(code);
    }

    /**
     * Specialized implementation of color matching for Bulls and Cows.
     * Bulls (index 0) are colors in correct positions.
     * Cows (index 1) are colors in wrong positions.
     *
     * @param other The code to compare with
     * @return An array where [0] is bulls and [1] is cows
     */
    @Override
    public int[] howManyCorrect(Code other) {
        int[] result = new int[2];
        List<Colour> otherCode = other.getCode();
        
        // Count bulls (correct position)
        for (int i = 0; i < code.size(); i++) {
            if (code.get(i).equals(otherCode.get(i))) {
                result[0]++;
            }
        }
        
        // Count total matches for each color
        int blackInSecret = 0, blackInGuess = 0;
        for (int i = 0; i < code.size(); i++) {
            if (code.get(i) == BinaryColour.BLACK) blackInSecret++;
            if (otherCode.get(i) == BinaryColour.BLACK) blackInGuess++;
        }
        
        // Calculate cows
        int totalMatches = Math.min(blackInSecret, blackInGuess) + 
                          Math.min(code.size() - blackInSecret, otherCode.size() - blackInGuess);
        result[1] = totalMatches - result[0];
        
        return result;
    }
}