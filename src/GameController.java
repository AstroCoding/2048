/**
 * Author: Mark Hutchison
 * Revised: April 10th, 2021
 *
 * Description: The GameController module.
 */

package src;

/**
 * @brief The controller module of the Game state.
 */
public class GameController {
    protected static int score;
    protected static BoardT board;

    /**
     * @brief Reinstatiate the game for replay.
     * @throws IllegalArgumentException
     */
    public static void newGame() throws IllegalArgumentException {
        board = new BoardT(4);
        score = 0;
    }

    /**
     * @brief Reinstatiate the game for replay.
     * @throws IllegalArgumentException If the specified dim is too small, error.
     */
    public static void newGame(int dim) throws IllegalArgumentException {
        if (dim < 3) throw new IllegalArgumentException();
        board = new BoardT(dim);
        score = 0;
    }

    /**
     * @brief Basic getter for the Game's board.
     * @return The game's BoardT instance.
     */
    public static BoardT getBoardT() {
        return board;
    }

    /**
     * @brief Basic setter for the Game's board.
     * @param b The new game BoardT instance you want to hard load.
     */
    public static void setBoardT(BoardT b) {
        board = b;
    }

    /**
     * @brief Basic getter for the Game's score.
     * @return The game's score integer value.
     */
    public static int getScore() {
        return score;
    }

    /**
     * @brief Adds an integer value to the existing score.
     * @param gained The new addition to the existing score.
     */
    public static void addScore(int gained) {
        score += gained;
    }

    /**
     * @brief Overwrite the current game score with a new one.
     * @param newScore The new game score you want to hard load.
     */
    public static void setScore(int newScore) {
        score = newScore;
    }

    /**
     * @brief The String representing the Game's current state to export to the CLI.
     * @return A string representation of the Game's state variables.
     */
    public static String getStringRepresentation() {
        final String ascii = "  ████████     █████    █████ █████   ████████  \n ███░░░░███  ███░░░███ ░░███ ░░███   ███░░░░███ \n░░░    ░███ ███   ░░███ ░███  ░███ █░███   ░███ \n   ███████ ░███    ░███ ░███████████░░████████  \n  ███░░░░  ░███    ░███ ░░░░░░░███░█ ███░░░░███ \n ███      █░░███   ███        ░███░ ░███   ░███ \n░██████████ ░░░█████░         █████ ░░████████  \n░░░░░░░░░░    ░░░░░░         ░░░░░   ░░░░░░░░";
        if (board.getHighestTileT().getValue() != 2048)
            return String.format(
                    "%n%s%n%n%s%n Score: %d | Highest Tile: %d%n Press 'Enter' to Start!%n Press 'wasd' or Arrow Keys to slide!%n",
                    ascii, board.getStringRepresentation(), score, board.getHighestTileT().getValue());
        return String.format(
                "%n%s%n%n%s%n Score: %d | Highest Tile: %d%n Congratulations!%nPress 'Enter' to Restart or Keep Playing!%n",
                ascii, board.getStringRepresentation(), score, board.getHighestTileT().getValue());

    }

    /**
     * @brief Determine if no tile can move in any direction.
     * @return Whether the BoardT isntance cannot move.
     */
    public static boolean gameOver() {
        int dimension = board.getDimension();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board.getTileT(i, j).getValue() > 0) {
                    for (TileT tile : board.getAdjacentTileTs(i, j)) {
                        if (tile == null)
                            continue;
                        if (tile.getValue() == board.getTileT(i, j).getValue())
                            return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @brief Determine if any tile on the BoardT can move.
     * @param dir The DirectionT you want to move in.
     * @return Whether the BoardT state can move in the direction specified.
     */
    public static boolean canMove(DirectionT dir) {
        return board.canMove(dir);
    }

    /**
     * @brief Move the BoardT state in a direction.
     * @param dir The DirectionT you want to move the BoardT isntance.
     */
    public static void move(DirectionT dir) {
        board.move(dir);
        board.generateTileT();
    }

}
