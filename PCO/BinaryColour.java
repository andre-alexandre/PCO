package test;

/**
 * Implements the Colour Interface.
 * Represents two possible values: BLACK or WHITE.
 * Each enum constant is associated with a string representation.
 * 
 * @author PCO Team
 */
public enum BinaryColour implements Colour {
	
    BLACK("B"),
    WHITE("W");
    
    private String rep;
    
    
    
	/**
	 * Constructor for the BinaryColour enumeration.
	 * 
	 * @param s The string representation of the colour.
	 */
    BinaryColour(String s) {
        this.rep = s;
    }
    
    
    
	/**
	 * Returns the string representation of the colour.
	 * 
	 * @return The string representation of the colour.
	 */
    public String toString() {
        return this.rep;
    }
    
    
    
	/**
	 * Returns an array containing all of the values of the BinaryColour enum type.
	 * 
	 * @return An array of all enum constants in BinaryColour.
	 */
    public Colour[] colours() {
        return BinaryColour.values();
    }
}