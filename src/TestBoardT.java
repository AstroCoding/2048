/**
 * Author: Mark Hutchison
 * Revised: April 10th, 2021
 *
 * Description: The BoardT Test Module.
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;

public class TestBoardT {
    BoardT testingBoard;
    TileT[][] emptyBoard, bottomRow, almostWinner, winner, loser;

    @Before
    public void setUp() {
        testingBoard = new BoardT();
        emptyBoard = new TileT[][] {
            {new TileT(), new TileT(), new TileT(), new TileT()},
            {new TileT(), new TileT(), new TileT(), new TileT()},
            {new TileT(), new TileT(), new TileT(), new TileT()},
            {new TileT(), new TileT(), new TileT(), new TileT()},
        };
        bottomRow = new TileT[][] {
            {new TileT(),  new TileT(),  new TileT(),  new TileT()},
            {new TileT(),  new TileT(),  new TileT(),  new TileT()},
            {new TileT(),  new TileT(),  new TileT(),  new TileT()},
            {new TileT(4), new TileT(4), new TileT(4), new TileT(4)},
        };
        almostWinner = new TileT[][] {
            { new TileT(),    new TileT(32),  new TileT(64),   new TileT(128) },
            { new TileT(),    new TileT(128), new TileT(256),  new TileT(256) },
            { new TileT(64),  new TileT(256), new TileT(512),  new TileT(512) },
            { new TileT(128), new TileT(256), new TileT(1024), new TileT(1024) },
        };
        winner = new TileT[][] {
            { new TileT(), new TileT(32),  new TileT(64),  new TileT(128) },
            { new TileT(), new TileT(),    new TileT(128), new TileT(512) },
            { new TileT(), new TileT(64),  new TileT(256), new TileT(1024) },
            { new TileT(), new TileT(128), new TileT(256), new TileT(2048) },
        };
        loser = new TileT[][] {
            { new TileT(2), new TileT(4), new TileT(2), new TileT(4), new TileT(2) },
            { new TileT(4), new TileT(2), new TileT(4), new TileT(2), new TileT(4) },
            { new TileT(2), new TileT(4), new TileT(2), new TileT(4), new TileT(2) },
            { new TileT(4), new TileT(2), new TileT(4), new TileT(2), new TileT(4) },
            { new TileT(2), new TileT(4), new TileT(2), new TileT(4), new TileT(2) },
        };
    }

    @After
    public void tearDown() {
        testingBoard = null;
        emptyBoard = bottomRow = almostWinner = winner = loser = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBoardT() {
        new BoardT(1);
    }

    @Test
    public void testBoardTInit() {
        assertTrue(boardEquivalence(new BoardT(4), emptyBoard));
    }

    @Test
    public void testGetDimension() {
        for (int i = 3; i < 9; i++) {
            assertEquals(new BoardT(i).getDimension(), i);
        }        
        BoardT changeDim = new BoardT(4);
        changeDim.setBoard(loser);
        assertEquals(changeDim.getDimension(), 5);
    }

    @Test
    public void testGetAndSetBoard() {
        assertTrue(boardEquivalence(testingBoard, emptyBoard));
        testingBoard.setBoard(bottomRow);
        assertTrue(boardEquivalence(testingBoard, bottomRow));
        testingBoard.setBoard(almostWinner);
        assertTrue(boardEquivalence(testingBoard, almostWinner));
        testingBoard.setBoard(winner);
        assertTrue(boardEquivalence(testingBoard, winner));
        testingBoard.setBoard(loser);
        assertTrue(boardEquivalence(testingBoard, loser));
    }

    @Test
    public void testGetTileT() {
        for (int i = 0; i < emptyBoard.length; i++)
            for (int j = 0; j < emptyBoard.length; j++)
                assertEquals(testingBoard.getTileT(i, j).getValue(), emptyBoard[i][j].getValue());
        testingBoard.setBoard(bottomRow);
        for (int i = 0; i < bottomRow.length; i++)
            for (int j = 0; j < bottomRow.length; j++)
                assertEquals(testingBoard.getTileT(i, j).getValue(), bottomRow[i][j].getValue());
        testingBoard.setBoard(almostWinner);
        for (int i = 0; i < almostWinner.length; i++)
            for (int j = 0; j < almostWinner.length; j++)
                assertEquals(testingBoard.getTileT(i, j).getValue(), almostWinner[i][j].getValue());
        testingBoard.setBoard(winner);
        for (int i = 0; i < winner.length; i++)
            for (int j = 0; j < winner.length; j++)
                assertEquals(testingBoard.getTileT(i, j).getValue(), winner[i][j].getValue());
        testingBoard.setBoard(loser);
        for (int i = 0; i < loser.length; i++)
            for (int j = 0; j < loser.length; j++)
                assertEquals(testingBoard.getTileT(i, j).getValue(), loser[i][j].getValue());
    }

    @Test
    public void testGetHighestTileT() {
        assertEquals(testingBoard.getHighestTileT().getValue(), 0);
        testingBoard.setBoard(bottomRow);
        assertEquals(testingBoard.getHighestTileT().getValue(), 4);
        testingBoard.setBoard(almostWinner);
        assertEquals(testingBoard.getHighestTileT().getValue(), 1024);
        testingBoard.setBoard(winner);
        assertEquals(testingBoard.getHighestTileT().getValue(), 2048);
        testingBoard.setBoard(loser);
        assertEquals(testingBoard.getHighestTileT().getValue(), 4);
    }

    @Test
    public void testGetStringRepresentation() {
        String empty = "╔══════╦══════╦══════╦══════╗\n║      ║      ║      ║      ║\n╠══════╬══════╬══════╬══════╣\n║      ║      ║      ║      ║\n╠══════╬══════╬══════╬══════╣\n║      ║      ║      ║      ║\n╠══════╬══════╬══════╬══════╣\n║      ║      ║      ║      ║\n╚══════╩══════╩══════╩══════╝\n", 
            winningString = "╔══════╦══════╦══════╦══════╗\n║      ║  32  ║  64  ║ 128  ║\n╠══════╬══════╬══════╬══════╣\n║      ║      ║ 128  ║ 512  ║\n╠══════╬══════╬══════╬══════╣\n║      ║  64  ║ 256  ║ 1024 ║\n╠══════╬══════╬══════╬══════╣\n║      ║ 128  ║ 256  ║ 2048 ║\n╚══════╩══════╩══════╩══════╝\n";
        assertEquals(testingBoard.getStringRepresentation(), empty);
        testingBoard.setBoard(winner);
        assertEquals(testingBoard.getStringRepresentation(), winningString);
    }

    @Test
    public void testGenerateTileT() {
        BoardT emptyBoard = new BoardT();
        assertTrue(boardEquivalence(emptyBoard, testingBoard));
        testingBoard.generateTileT();
        assertTrue(!boardEquivalence(emptyBoard, testingBoard));
        boolean found_new_TileT = false;
        for (int i = 0; i < testingBoard.getDimension(); i++)
            for (int j = 0; j < testingBoard.getDimension(); j++)
                if (testingBoard.getTileT(i, j).getValue() != 0)
                    found_new_TileT = true;
        assertTrue(found_new_TileT);
    }

    @Test
    public void testCanMove() {
        assertTrue(!testingBoard.canMove(DirectionT.UP));
        assertTrue(!testingBoard.canMove(DirectionT.DOWN));
        assertTrue(!testingBoard.canMove(DirectionT.LEFT));
        assertTrue(!testingBoard.canMove(DirectionT.RIGHT));
        testingBoard.setBoard(bottomRow);
        assertTrue(testingBoard.canMove(DirectionT.UP));
        assertTrue(!testingBoard.canMove(DirectionT.DOWN));
        assertTrue(testingBoard.canMove(DirectionT.LEFT));
        assertTrue(testingBoard.canMove(DirectionT.RIGHT));
        testingBoard.setBoard(almostWinner);
        assertTrue(testingBoard.canMove(DirectionT.UP));
        assertTrue(testingBoard.canMove(DirectionT.DOWN));
        assertTrue(testingBoard.canMove(DirectionT.LEFT));
        assertTrue(testingBoard.canMove(DirectionT.RIGHT));
        testingBoard.setBoard(winner);
        assertTrue(testingBoard.canMove(DirectionT.UP));
        assertTrue(testingBoard.canMove(DirectionT.DOWN));
        assertTrue(testingBoard.canMove(DirectionT.LEFT));
        assertTrue(!testingBoard.canMove(DirectionT.RIGHT));
        testingBoard.setBoard(loser);
        assertTrue(!testingBoard.canMove(DirectionT.UP));
        assertTrue(!testingBoard.canMove(DirectionT.DOWN));
        assertTrue(!testingBoard.canMove(DirectionT.LEFT));
        assertTrue(!testingBoard.canMove(DirectionT.RIGHT));
    }

    @Test
    public void testMove1() {
        testingBoard.setBoard(almostWinner);
        testingBoard.move(DirectionT.RIGHT);
        assertTrue(boardEquivalence(testingBoard, winner));
    }

    @Test
    public void testMove2() {
        testingBoard.setBoard(bottomRow);
        testingBoard.move(DirectionT.RIGHT);
        TileT[] bRow = new TileT[] { new TileT(), new TileT(), new TileT(8), new TileT(8), };
        assertTrue(rowEquivalence(testingBoard.getBoard()[3], bRow));
    }

    @Test
    public void testMove3() {
        testingBoard.setBoard(bottomRow);
        testingBoard.move(DirectionT.LEFT);
        TileT[] bRow = new TileT[] { new TileT(8), new TileT(8), new TileT(), new TileT(), };
        assertTrue(rowEquivalence(testingBoard.getBoard()[3], bRow));
    }

    @Test
    public void testMove4() {
        testingBoard.setBoard(bottomRow);
        testingBoard.move(DirectionT.UP);
        TileT[] tRow = new TileT[] { new TileT(4), new TileT(4), new TileT(4), new TileT(4), };
        assertTrue(rowEquivalence(testingBoard.getBoard()[0], tRow));
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

    private boolean boardEquivalence(BoardT b1, BoardT b2) {
        TileT[][] b = b1.getBoard();
        TileT[][] e = b2.getBoard();
        if (b.length != e.length)
            return false;
        for (TileT[] tileTs : e) {
            if (b.length != tileTs.length)
                return false;
        }
        for (int i = 0; i < e.length; i++) {
            for (int j = 0; j < e.length; j++) {
                if (b[i][j].getValue() != e[i][j].getValue())
                    return false;
            }
        }
        return true;
    }

    private boolean rowEquivalence(TileT[] row1, TileT[] row2) {
        if (row1.length != row2.length)
            return false;
        for (int i = 0; i < row1.length; i++) {
            if (row1[i].getValue() != row2[i].getValue())
                return false;
        }
        return true;
    }
}
