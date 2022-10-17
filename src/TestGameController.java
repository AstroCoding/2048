/**
 * Author: Mark Hutchison
 * Revised: April 10th, 2021
 *
 * Description: The Game Controller Test Module.
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;

public class TestGameController {
    BoardT testingBoard;
    TileT[][] emptyBoard, bottomRow, almostWinner, winner, loser;

    @Before
    public void setUp() {
        GameController.newGame(4);
        testingBoard = new BoardT();
        emptyBoard = new TileT[][] { { new TileT(), new TileT(), new TileT(), new TileT() },
                { new TileT(), new TileT(), new TileT(), new TileT() },
                { new TileT(), new TileT(), new TileT(), new TileT() },
                { new TileT(), new TileT(), new TileT(), new TileT() }, };
        bottomRow = new TileT[][] { { new TileT(), new TileT(), new TileT(), new TileT() },
                { new TileT(), new TileT(), new TileT(), new TileT() },
                { new TileT(), new TileT(), new TileT(), new TileT() },
                { new TileT(4), new TileT(4), new TileT(4), new TileT(4) }, };
        almostWinner = new TileT[][] { { new TileT(), new TileT(32), new TileT(64), new TileT(128) },
                { new TileT(), new TileT(128), new TileT(256), new TileT(256) },
                { new TileT(64), new TileT(256), new TileT(512), new TileT(512) },
                { new TileT(128), new TileT(256), new TileT(1024), new TileT(1024) }, };
        winner = new TileT[][] { { new TileT(), new TileT(32), new TileT(64), new TileT(128) },
                { new TileT(), new TileT(), new TileT(128), new TileT(512) },
                { new TileT(), new TileT(64), new TileT(256), new TileT(1024) },
                { new TileT(), new TileT(128), new TileT(256), new TileT(2048) }, };
        loser = new TileT[][] { { new TileT(2), new TileT(4), new TileT(2), new TileT(4), new TileT(2) },
                { new TileT(4), new TileT(2), new TileT(4), new TileT(2), new TileT(4) },
                { new TileT(2), new TileT(4), new TileT(2), new TileT(4), new TileT(2) },
                { new TileT(4), new TileT(2), new TileT(4), new TileT(2), new TileT(4) },
                { new TileT(2), new TileT(4), new TileT(2), new TileT(4), new TileT(2) }, };
    }

    @After
    public void tearDown() {
        GameController.newGame(4);
        testingBoard = null;
        emptyBoard = bottomRow = almostWinner = winner = loser = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTileT() {
        GameController.newGame(1);
    }

    @Test
    public void testGetBoard() {
        assertTrue(boardEquivalence(GameController.getBoardT(), emptyBoard));
    }

    @Test
    public void testSetBoard() {
        testingBoard.setBoard(almostWinner);
        GameController.setBoardT(testingBoard);
        assertTrue(boardEquivalence(GameController.getBoardT(), almostWinner));
    }
    
    @Test
    public void testScoring() {
        assertEquals(GameController.getScore(), 0);
        GameController.addScore(16);
        assertEquals(GameController.getScore(), 16);
        GameController.addScore(16);
        assertEquals(GameController.getScore(), 32);
        GameController.setScore(2048);
        assertEquals(GameController.getScore(), 2048);
    }

    @Test
    public void testCanMove() {
        GameController.setBoardT(testingBoard);
        assertTrue(!GameController.canMove(DirectionT.UP));
        assertTrue(!GameController.canMove(DirectionT.DOWN));
        assertTrue(!GameController.canMove(DirectionT.LEFT));
        assertTrue(!GameController.canMove(DirectionT.RIGHT));
        testingBoard.setBoard(bottomRow);
        GameController.setBoardT(testingBoard);
        assertTrue(GameController.canMove(DirectionT.UP));
        assertTrue(!GameController.canMove(DirectionT.DOWN));
        assertTrue(GameController.canMove(DirectionT.LEFT));
        assertTrue(GameController.canMove(DirectionT.RIGHT));
        testingBoard.setBoard(almostWinner);
        GameController.setBoardT(testingBoard);
        assertTrue(GameController.canMove(DirectionT.UP));
        assertTrue(GameController.canMove(DirectionT.DOWN));
        assertTrue(GameController.canMove(DirectionT.LEFT));
        assertTrue(GameController.canMove(DirectionT.RIGHT));
        testingBoard.setBoard(winner);
        GameController.setBoardT(testingBoard);
        assertTrue(GameController.canMove(DirectionT.UP));
        assertTrue(GameController.canMove(DirectionT.DOWN));
        assertTrue(GameController.canMove(DirectionT.LEFT));
        assertTrue(!GameController.canMove(DirectionT.RIGHT));
        testingBoard.setBoard(loser);
        GameController.setBoardT(testingBoard);
        assertTrue(!GameController.canMove(DirectionT.UP));
        assertTrue(!GameController.canMove(DirectionT.DOWN));
        assertTrue(!GameController.canMove(DirectionT.LEFT));
        assertTrue(!GameController.canMove(DirectionT.RIGHT));
    }

    @Test
    public void testGameOver() {
        assertTrue(!GameController.gameOver());
        testingBoard.setBoard(bottomRow);
        GameController.setBoardT(testingBoard);
        assertTrue(!GameController.gameOver());
        testingBoard.setBoard(almostWinner);
        GameController.setBoardT(testingBoard);
        assertTrue(!GameController.gameOver());
        testingBoard.setBoard(winner);
        GameController.setBoardT(testingBoard);
        assertTrue(!GameController.gameOver());
        testingBoard.setBoard(loser);
        GameController.setBoardT(testingBoard);
        assertTrue(GameController.gameOver());
    }

    @Test
    public void testGetStringRepresentation() {
        final String ascii = "  ████████     █████    █████ █████   ████████  \n ███░░░░███  ███░░░███ ░░███ ░░███   ███░░░░███ \n░░░    ░███ ███   ░░███ ░███  ░███ █░███   ░███ \n   ███████ ░███    ░███ ░███████████░░████████  \n  ███░░░░  ░███    ░███ ░░░░░░░███░█ ███░░░░███ \n ███      █░░███   ███        ░███░ ░███   ░███ \n░██████████ ░░░█████░         █████ ░░████████  \n░░░░░░░░░░    ░░░░░░         ░░░░░   ░░░░░░░░";
        testingBoard.setBoard(almostWinner);
        GameController.setBoardT(testingBoard);
        String normal_expt = String.format(
                "%n%s%n%n%s%n Score: %d | Highest Tile: %d%n Press 'Enter' to Start!%n Press 'wasd' or Arrow Keys to slide!%n",
                ascii, testingBoard.getStringRepresentation(), GameController.getScore(), testingBoard.getHighestTileT().getValue());
        assertEquals(GameController.getStringRepresentation(), normal_expt);
        testingBoard.setBoard(winner);
        GameController.setBoardT(testingBoard);
        String winning_expt = String.format(
                "%n%s%n%n%s%n Score: %d | Highest Tile: %d%n Congratulations!%nPress 'Enter' to Restart or Keep Playing!%n",
                ascii, testingBoard.getStringRepresentation(), GameController.getScore(),
                testingBoard.getHighestTileT().getValue());
        assertEquals(GameController.getStringRepresentation(), winning_expt);
    }

    @Test
    public void testMove() {
        testingBoard.setBoard(almostWinner);
        GameController.setBoardT(testingBoard);
        assertEquals(GameController.getBoardT().getHighestTileT().getValue(), 1024);
        assertEquals(countEmpty(GameController.getBoardT()), 2);
        GameController.move(DirectionT.RIGHT);
        assertEquals(GameController.getBoardT().getHighestTileT().getValue(), 2048);
        assertEquals(countEmpty(GameController.getBoardT()), 4);
    }

    private int countEmpty(BoardT b) {
        int counter = 0;
        for (int i = 0; i < b.getDimension(); i++) 
            for (int j = 0; j < b.getDimension(); j++)
                if (b.getTileT(i, j).getValue() == 0)
                    counter++;
        return counter;
    }

    private boolean boardEquivalence(BoardT b, TileT[][] e) {
        TileT[][] board = b.getBoard();
        if (board.length != e.length)
            return false;
        for (TileT[] tileTs : e) {
            if (board.length != tileTs.length)
                return false;
        }
        for (int i = 0; i < e.length; i++) {
            for (int j = 0; j < e.length; j++) {
                if (board[i][j].getValue() != e[i][j].getValue())
                    return false;
            }
        }
        return true;
    }
}
