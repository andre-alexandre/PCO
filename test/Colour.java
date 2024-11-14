package test;

/**
 * Interface representing a color in the Mastermind game.
 * Provides methods for color representation and enumeration.
 * 
 * @author PCO Team
 */
public interface Colour {
    /**
     * Returns an array of all possible colors of this type.
     * 
     * @return Array of all possible colors
     */
    Colour[] colours();
    
    /**
     * Returns the string representation of the color.
     * 
     * @return String representation of the color
     */
    String toString();
}