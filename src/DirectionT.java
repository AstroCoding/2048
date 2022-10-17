/**
 * Author: Mark Hutchison
 * Revised: April 10th, 2021
 *
 * Description: The DirectionT module.
 */

package src;

/**
 * @brief The 4 possible DPad directions represented as an enum.
 */
public enum DirectionT {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    /**
     * @brief Returns whether a direction is a member of the DirectionT enum.
     * @param o The object you want to test the equality of.
     * @return Whether the DirectionT is a member of the enum.
     */
    public boolean equals(DirectionT o) { return this == o; }
}
