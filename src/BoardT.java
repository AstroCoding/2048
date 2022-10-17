/**
 * Author: Mark Hutchison
 * Revised: April 10th, 2021
 *
 * Description: The BoardT module.
 */

package src;

/**
 * @brief An abstraction data type representing a Gameboard.
 * @details The gameboard is a square 2-dimensional list of TileT instances,
 *  and shares many properties that a square matrix shares such as rotation.
 */
public class BoardT implements StringRepresentable {
    protected TileT[][] board;
    protected int dimension;

    /**
     * @brief Instantiate a new BoardT object.
     * @throws IllegalArgumentException
     */
    public BoardT() throws IllegalArgumentException {
        dimension = 4;
        board = new TileT[4][4];
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                board[i][j] = new TileT();
    }

    /**
     * @brief Instantiate a new BoardT object.
     * @throws IllegalArgumentException If the dimension is less than 3, error.
     */
    public BoardT(int dim) throws IllegalArgumentException {
        if (dim < 3)
            throw new IllegalArgumentException();
        dimension = dim;
        board = new TileT[dim][dim];
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                board[i][j] = new TileT();
    }

    /**
     * @brief Returns sqeunce of all adjacent TileT instances of TileT at
     *        coordinates.
     * @param i The row containting the target TileT.
     * @param j The column containting the target TileT.
     * @return The sequence of 4 neighbouring TileT instances.
     * @throws IllegalArgumentException if the target TileT is not a TileT on the board, error.
     */
    public TileT[] getAdjacentTileTs(int i, int j) throws IllegalArgumentException{
        if (i < 0 || i >= dimension)
            throw new IllegalArgumentException();
        if (j < 0 || j >= dimension)
            throw new IllegalArgumentException();
        TileT[] ans = new TileT[4];
        ans[0] = (i - 1 > 0) ? board[i - 1][j] : null;         // UP
        ans[1] = (i + 1 < dimension) ? board[i + 1][j] : null; // DOWN
        ans[2] = (j - 1 > 0) ? board[i][j - 1] : null;         // LEFT
        ans[3] = (j + 1 < dimension) ? board[i][j + 1] : null; // RIGHT
        return ans;
    }

    /**
     * @brief Returns target TileT at coordinates.
     * @param i The row containting the target TileT.
     * @param j The column containting the target TileT.
     * @return The TileT instance at coordinates.
     * @throws IllegalArgumentException if the target TileT is not a TileT on the
     *                                  board, error.
     */
    public TileT getTileT(int i, int j) throws IllegalArgumentException {
        if (i < 0 || i >= dimension)
            throw new IllegalArgumentException();
        if (j < 0 || j >= dimension)
            throw new IllegalArgumentException();
        return board[i][j];
    }

    /**
     * @brief Basic getter for the dimension of the BoardT.
     * @return The dimension of the BoardT.
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * @brief Basic getter for the board of the BoardT.
     * @return The board of the BoardT.
     */
    public TileT[][] getBoard() {
        return board;
    }

    /**
     * @brief Basic setter for the board of the BoardT.
     * @param b The new 2-dimensional array of TileT instances.
     * @throws IllegalArgumentException If b is not a valid board, error.
     */
    public void setBoard(TileT[][] b) throws IllegalArgumentException {
        int dim = b.length;
        for (TileT[] tileTs : b) {
            if (tileTs.length != dim) throw new IllegalArgumentException();
        }
        if (dim < 3)
            throw new IllegalArgumentException();
        dimension = dim;
        board = b;
    }

    /**
     * @brief Find the Highest Valued TileT on the board and return it.
     * @return The TileT with the largest value.
     */
    public TileT getHighestTileT() {
        TileT highest_value = board[0][0];
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                if (getTileT(i, j).getValue() > highest_value.getValue())
                    highest_value = getTileT(i, j);
        return highest_value;
    }

    private String repeat(String text, int times) {
        String s = "";
        for (int i = 0; i < times; i++) {
            s += text;
        }
        return s;
    }

    /**
     * @brief Return the String Representation of the gameboard, as detailed roughly in the MIS.
     * @return String representation of the board stored in the BoardT instance.
     */
    public String getStringRepresentation() {
        int tileLength = Math.max(6, 2 + getHighestTileT().getStringRepresentation().length());

        final String TL = "\u2554",
            TR = "\u2557",
            BL = "\u255A",
            BR = "\u255D",
            joinTop = "\u2566",
            joinLeft = "\u2560",
            joinRight = "\u2563",
            joinMiddle = "\u256C",
            joinBottom = "\u2569",
            vert = "\u2551",
            horizontal = "\u2550";

        // String gameBoard = TL + (horizontal.repeat(
        //         tileLength) + joinTop).repeat(dimension - 1)
        //         + horizontal.repeat(tileLength) + TR + "\n";

        String gameBoard = TL + repeat(repeat(horizontal, tileLength) + joinTop, dimension - 1)
                + repeat(horizontal, tileLength) + TR + "\n";

        for (int i = 0; i < dimension; i++) {
            gameBoard += vert;
            for (int j = 0; j < dimension; j++) {
                int leftSpaces = (tileLength - board[i][j].getStringRepresentation().length()) / 2;
                int rightSpaces = tileLength - (leftSpaces + board[i][j].getStringRepresentation().length());
                if (board[i][j].getValue() > 0) {
                    // gameBoard += " ".repeat(leftSpaces) + board[i][j].getStringRepresentation()
                    //     + " ".repeat(rightSpaces) + vert;

                    gameBoard += repeat(" ", leftSpaces) + board[i][j].getStringRepresentation()
                            + repeat(" ", rightSpaces) + vert;
                } else {
                    // gameBoard += " ".repeat(tileLength) + vert;
                    gameBoard += repeat(" ", tileLength) + vert;
                }
            }
            // gameBoard += (i != dimension - 1)
            //         ? "\n" + joinLeft + (horizontal.repeat(
            //                 tileLength) + joinMiddle).repeat(dimension - 1)
            //                 + horizontal.repeat(tileLength) + joinRight + "\n"
            //         : "\n";
            gameBoard += (i != dimension - 1)
                    ? "\n" + joinLeft + repeat(repeat(horizontal, tileLength) + joinMiddle, dimension - 1)
                            + repeat(horizontal, tileLength) + joinRight + "\n"
                    : "\n";

        }
        // gameBoard += BL + (horizontal.repeat(
        //         tileLength) + joinBottom).repeat(dimension - 1)
        //         + horizontal.repeat(tileLength) + BR + "\n";
        gameBoard += BL + repeat(repeat(horizontal, tileLength) + joinBottom, dimension - 1)
                + repeat(horizontal, tileLength) + BR + "\n";
        return gameBoard;
    }

    /**
     * @brief Find an empty tile on the board and populate it with a TileT instance
     * @details The gameboard will have a ~90% change add TileT with the value 4,
     *      otherwise it will add a TileT with the value 2.
     */
    public void generateTileT() {
        boolean empty = true;
        while (empty) {
            int row = (int) (Math.random() * dimension);
            int col = (int) (Math.random() * dimension);
            if (board[row][col].getValue() == 0) {
                board[row][col] = (Math.random() < 0.2) ? new TileT(4) : new TileT(2);
                empty = false;
            }
        }
    }

    private void combine(TileT[] row, DirectionT dir) {
        if (dir.equals(DirectionT.LEFT)) {
            for (int i = 0; i < dimension - 1; i++)
                if (row[i].getValue() != 0 && row[i].getValue() == row[i + 1].getValue()) {
                    int newTileScore = row[i + 1].getValue() * 2;
                    GameController.addScore(newTileScore);
                    row[i] = new TileT(newTileScore);
                    row[i + 1] = new TileT();
                }
        } else if (dir.equals(DirectionT.RIGHT)) {
            for (int i = dimension - 1; i > 0; i--)
                if (row[i].getValue() != 0 && row[i].getValue() == row[i - 1].getValue()) {
                    int newTileScore = row[i - 1].getValue() * 2;
                    GameController.addScore(newTileScore);
                    row[i] = new TileT(newTileScore);
                    row[i - 1] = new TileT();
                }
        }
    }

    private void slide(TileT[] row, DirectionT dir) {
        if (dir.equals(DirectionT.LEFT)) {
            for (int i = 1; i < dimension; i++) {
                for (int j = i; j > 0; j--) {
                    if (row[j].getValue() != 0 && row[j - 1].getValue() == 0) {
                        TileT temp = row[j - 1];
                        row[j - 1] = row[j];
                        row[j] = temp;
                        continue;
                    }
                    break;
                }
            }
        } else if (dir.equals(DirectionT.RIGHT)) {
            for (int i = dimension - 2; i >= 0; i--) {
                for (int j = i; j < dimension - 1; j++) {
                    if (row[j].getValue() != 0 && row[j + 1].getValue() == 0) {
                        TileT temp = row[j + 1];
                        row[j + 1] = row[j];
                        row[j] = temp;
                        continue;
                    }
                    break;
                }
            }
        }
    }

    /**
     * @brief Determine if any TileT on the board can move in a DirectionT.
     * @param dir The DirectionT you want to move the board.
     * @return Whether there exists a TileT that can move in dir on the board.
     */
    public boolean canMove(DirectionT dir) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j].getValue() == 0)
                    continue;
                if (dir.equals(DirectionT.UP)) {
                    if (i == 0)
                        continue;
                    if (board[i - 1][j].getValue() == 0 || board[i][j].getValue() == board[i - 1][j].getValue())
                        return true;
                } else if (dir.equals(DirectionT.DOWN)) {
                    if (i == dimension - 1)
                        continue;
                    if (board[i + 1][j].getValue() == 0 || board[i][j].getValue() == board[i + 1][j].getValue())
                        return true;
                } else if (dir.equals(DirectionT.LEFT)) {
                    if (j == 0)
                        continue;
                    if (board[i][j - 1].getValue() == 0 || board[i][j].getValue() == board[i][j - 1].getValue())
                        return true;
                } else if (dir.equals(DirectionT.RIGHT)) {
                    if (j == dimension - 1)
                        continue;
                    if (board[i][j + 1].getValue() == 0 || board[i][j].getValue() == board[i][j + 1].getValue())
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * @brief Preform a "game move" on the board.
     * @details A game move consists of 3 stages:
     *  Slide the TileTs in a DirectionT specified
     *  Combine the TileTs in that same DirectionT
     *  Re-Slide the board one final time
     * @param dir The DirectionT you want to move the board in.
     */
    public void move(DirectionT dir) {
        if (dir.equals(DirectionT.UP)) {
            // Rotate Counterclockwise
            rotateCCW(1);
            // Move Left
            for (int row = 0; row < dimension; row++) {
                slide(board[row], DirectionT.LEFT);
                combine(board[row], DirectionT.LEFT);
                slide(board[row], DirectionT.LEFT);
            }
            // Rotate Clockwise
            rotateCW(1);
        } else if (dir.equals(DirectionT.DOWN)) {
            // Rotate Counterclockwise
            rotateCCW(1);
            // Move Right
            for (int row = 0; row < dimension; row++) {
                slide(board[row], DirectionT.RIGHT);
                combine(board[row], DirectionT.RIGHT);
                slide(board[row], DirectionT.RIGHT);
            }
            // Rotate Clockwise
            rotateCW(1);
        } else if (dir.equals(DirectionT.LEFT)) {
            // Move Left
            for (int row = 0; row < dimension; row++) {
                slide(board[row], DirectionT.LEFT);
                combine(board[row], DirectionT.LEFT);
                slide(board[row], DirectionT.LEFT);
            }
        } else if (dir.equals(DirectionT.RIGHT)) {
            // Move Right
            for (int row = 0; row < dimension; row++) {
                slide(board[row], DirectionT.RIGHT);
                combine(board[row], DirectionT.RIGHT);
                slide(board[row], DirectionT.RIGHT);
            }
        }
    }

    private void rotateCW(int times) {
        for (int i = 0; i < times; i++) {
            transpose();
            mirror();
        }
    }

    private void rotateCCW(int times) {
        for (int i = 0; i < times; i++) {
            mirror();
            transpose();
        }
    }

    private void transpose() {
        for (int i = 0; i < dimension; i++) {
            for (int j = i; j < dimension; j++) {
                TileT temp = board[i][j];
                board[i][j] = board[j][i];
                board[j][i] = temp;
            }
        }
    }

    private void mirror() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension / 2; j++) {
                TileT temp = board[i][j];
                board[i][j] = board[i][dimension - 1 - j];
                board[i][dimension - 1 - j] = temp;
            }
        }
    }

}
