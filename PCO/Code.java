package test;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sequence of colours.
 * This class provides functionality to create, compare and manipulate colour codes.
 *
 * @author PCO Team
 */
public class Code implements Cloneable {
	
    protected List<Colour> code;

    
    
    /**
     * Creates a new Code instance with the given sequence of colours.
     *
     * @param code A list of colours that extends the Colour interface
     */
    public Code(List<? extends Colour> code) {
        this.code = new ArrayList<>(code);
    }
    
    

    /**
     * Returns a copy of the colour sequence.
     *
     * @return A new list containing the colours in this code
     */
    public List<Colour> getCode() {
        return new ArrayList<>(this.code);
    }
    
    

    /**
     * Returns the length of the colour sequence.
     *
     * @return The number of colours in this code
     */
    public int getLength() {
        return this.code.size();
    }
    
    

    /**
     * Compares this code with another code and returns how many colours match.
     * The returned array contains:
     * array[0] - number of correct colours in correct positions;
     * array[1] - number of correct colours in wrong positions.
     *
     * @param other The code to compare with
     * @return An array containing two integers
     */
    public int[] howManyCorrect(Code other) {
        int[] result = new int[2];
        List<Colour> otherCode = other.getCode();
        List<Colour> tempThis = new ArrayList<>(this.code);
        List<Colour> tempOther = new ArrayList<>(otherCode);
        
        for (int i = 0; i < this.code.size(); i++) {
            if (tempThis.get(i).equals(tempOther.get(i))) {
                result[0]++;
                tempThis.set(i, null);
                tempOther.set(i, null);
            }
        }
        
        for (int i = 0; i < code.size(); i++) {
            if (tempThis.get(i) != null) {
                for (int j = 0; j < this.code.size(); j++) {
                    if (tempOther.get(j) != null && tempThis.get(i).equals(tempOther.get(j))) {
                        result[1]++;
                        tempOther.set(j, null);
                        break;
                    }
                }
            }
        }
        
        return result;
    }

    

    /**
     * Returns a string representation of the colour sequence.
     *
     * @return A string showing the sequence of colours
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < this.code.size(); i++) {
            sb.append(this.code.get(i));
            if (i < this.code.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    
    
    /**
     * Creates and returns a copy of the colour sequence.
     *
     * @return A new Code object with the same colour sequence.
     */
    @Override
    public Code clone() {
        Code cloned = null;
        
		// TODO Auto-generated catch block
		try {
			cloned = (Code) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
        cloned.code = new ArrayList<>(this.code);
        return cloned;
    }
    
    

    /**
     * Checks if the colour sequence equals another colour sequence.
     *
     * @param obj The object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Code)) return false;
        Code other = (Code) obj;
        return this.code.equals(other.code);
    }
}
