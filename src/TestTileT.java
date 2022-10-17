/**
 * Author: Mark Hutchison
 * Revised: April 10th, 2021
 *
 * Description: The TileT test module.
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;

import java.awt.Color;

public class TestTileT {
    TileT[] tiles;

    @Before
    public void setUp() {
        tiles = new TileT[14];
        tiles[0] = new TileT(0);
        for (int i = 1; i < tiles.length; i++) {
            tiles[i] = new TileT((int) Math.pow(2, i));
        }
    }

    @After
    public void tearDown() {
        tiles = null;
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidTileT() {
        new TileT(-1);
    }

    @Test
    public void testTileTValues() {
        assertEquals(tiles[0].getValue(), 0);
        for (int i = 1; i < tiles.length; i++)
            assertEquals(tiles[i].getValue(), (int) Math.pow(2, i));
    }

    @Test
    public void testTileTStrings() {
        assertEquals(tiles[0].getStringRepresentation(), "0");
        
        for (int i = 1; i < tiles.length; i++) 
            assertEquals(tiles[i].getStringRepresentation(), String.valueOf((int) Math.pow(2, i)));
    }

    @Test
    public void testSetTileTValue() {
        assertEquals(tiles[0].getValue(), 0);
        for (int i = 1; i < tiles.length; i++) {
            tiles[0].setValue((int) Math.pow(2, i));
            assertEquals(tiles[0].getValue(), (int) Math.pow(2, i));
        }
    }

    @Test
    public void testGetColour() {
        for (TileT tileT : tiles) {
            String correct = colourString(colourMap(tileT.getValue()));
            assertEquals(colourString(tileT.getColour()), correct);
        }
    }

    private Color colourMap(int value) {
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

    private String colourString(Color c) {
        return String.format("(%d,%d,%d)", c.getRed(), c.getGreen(), c.getBlue());
    }
}
