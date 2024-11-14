package test;

/**
 * An enumeration representing binary colors (black and white).
 * Implements the Colour interface for use in the Bulls and Cows variant.
 * 
 * @author PCO Team
 */
public enum BinaryColour implements Colour {
    BLACK("B"),
    WHITE("W");
    
    private String rep;
    
    BinaryColour(String s) {
        this.rep = s;
    }
    
    @Override
    public String toString() {
        return this.rep;
    }
    
    @Override
    public Colour[] colours() {
        return BinaryColour.values();
    }
}