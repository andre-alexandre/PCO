package test;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sequence of colors in the Mastermind game.
 * This class provides functionality to create, compare and manipulate color codes.
 *
 * @author PCO Team
 */
public class Code implements Cloneable {
    /** The list of colors that makes up this code */
    protected List<Colour> code;

    /**
     * Creates a new Code instance with the given sequence of colors.
     *
     * @param code A list of colors that extends the Colour interface
     */
    public Code(List<? extends Colour> code) {
        this.code = new ArrayList<>(code);
    }

    /**
     * Returns a copy of the color sequence in this code.
     *
     * @return A new list containing the colors in this code
     */
    public List<Colour> getCode() {
        return new ArrayList<>(code);
    }

    /**
     * Returns the length of this code sequence.
     *
     * @return The number of colors in this code
     */
    public int getLength() {
        return code.size();
    }

    /**
     * Compares this code with another code and returns how many colors match.
     * The returned array contains two numbers:
     * [0] - number of correct colors in correct positions
     * [1] - number of correct colors in wrong positions
     *
     * @param other The code to compare with
     * @return An array of two integers containing the matching results
     */
    public int[] howManyCorrect(Code other) {
        int[] result = new int[2];
        List<Colour> otherCode = other.getCode();
        List<Colour> tempThis = new ArrayList<>(code);
        List<Colour> tempOther = new ArrayList<>(otherCode);
        
        // First pass: count exact matches
        for (int i = 0; i < code.size(); i++) {
            if (tempThis.get(i).equals(tempOther.get(i))) {
                result[0]++;
                tempThis.set(i, null);
                tempOther.set(i, null);
            }
        }
        
        // Second pass: count color matches in wrong positions
        for (int i = 0; i < code.size(); i++) {
            if (tempThis.get(i) != null) {
                for (int j = 0; j < code.size(); j++) {
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
     * Creates and returns a copy of this code.
     *
     * @return A new Code object with the same color sequence
     */
    @Override
    public Code clone() {
        try {
            Code cloned = (Code) super.clone();
            cloned.code = new ArrayList<>(this.code);
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a string representation of this code.
     *
     * @return A string showing the sequence of colors
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < code.size(); i++) {
            sb.append(code.get(i));
            if (i < code.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Checks if this code equals another object.
     *
     * @param obj The object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Code)) return false;
        Code other = (Code) obj;
        return code.equals(other.code);
    }
}
