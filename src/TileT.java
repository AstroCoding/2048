/**
 * Author: Mark Hutchison
 * Revised: April 10th, 2021
 *
 * Description: The TileT module.
 */

package src;

import java.awt.Color;

/**
 * @brief The TileT Abstract Data Type
 */
public class TileT implements StringRepresentable {
    protected int value;
    protected Color c;

    /**
     * @brief Construct a default TileT with no argument.
     * @throws IllegalArgumentException
     */
    public TileT() throws IllegalArgumentException {
        value = 0;
    }

    /**
     * @brief Construct a TileT object
     * @param number The integer representation stored in the TileT object.
     * @throws IllegalArgumentException If number is negative, error.
     */
    public TileT(int number) throws IllegalArgumentException {
        if (number < 0)
            throw new IllegalArgumentException();
        value = number;
    }

    /**
     * @brief Basic getter for TileT's value.
     * @return The integer representation stored in the TileT object.
     */
    public int getValue() {
        return value;
    }

    /**
     * @brief Basic setter for TileT's value.
     * @param number The integer representation you want to store in the TileT object.
     * @throws IllegalArgumentException If the number is negative, error.
     */
    public void setValue(int number) throws IllegalArgumentException {
        if (number < 0)
            throw new IllegalArgumentException();
        value = number;
    }

    /**
     * @brief Basic getter for TileT's color, represented by the Color class.
     * @return Color object stored by the TileT.
     */
    public Color getColour() {
        switch (value) {
            case 0:
                return new Color(128, 128, 128);
            case 2:
                return new Color(238, 228, 218);
            case 4:
                return new Color(237, 224, 200);
            case 8:
                return new Color(242, 177, 121);
            case 16:
                return new Color(245, 149, 99);
            case 32:
                return new Color(246, 124, 95);
            case 64:
                return new Color(246, 94, 59);
            case 128:
                return new Color(237, 207, 114);
            case 256:
                return new Color(237, 204, 97);
            case 512:
                return new Color(237, 200, 80);
            case 1024:
                return new Color(237, 197, 63);
            default:
                return new Color(237, 194, 46);
        }
    }

    /**
     * @brief Returns the integer representation of the TileT as a String.
     * @return The string representation of integer value stored in the TileT object.
     */
    public String getStringRepresentation() {
        return String.valueOf(value);
    }
}
