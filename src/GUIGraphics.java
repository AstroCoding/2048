/**
 * Author: Mark Hutchison
 * Revised: April 10th, 2021
 *
 * Description: The GUIGraphics module.
 */

package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIGraphics extends JPanel implements KeyListener {
    private static final long serialVersionUID = 1L;
    static int gameSize = 4;
    static GUIGraphics newGraphics = new GUIGraphics();
    static JFrame frame = new JFrame("2048");

    /**
     * @brief Set up the GUI window for rendering, and instantiate the game.
     * @param gameDimension The Gameboard Dimension.
     * @throws IllegalArgumentException If the arguement is an Illegal Gameboard size, error.
     */
    public static void setUpGUI(int gameDimension) throws IllegalArgumentException {
        if (gameDimension < 3) throw new IllegalArgumentException();
        GameController.newGame(gameDimension);
        frame.addKeyListener(newGraphics);
        frame.getContentPane().add(newGraphics);
        frame.setSize(600, 100 * GameController.getBoardT().getDimension());
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * @brief Monitors standard input for key presses, and sends those keys to the GameController.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {
            if (GameController.canMove(DirectionT.UP)) {
                GameController.move(DirectionT.UP);
            } else {
                System.out.println("Invalid Move!");
            }
        } else if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (GameController.canMove(DirectionT.DOWN)) {
                GameController.move(DirectionT.DOWN);
            }
        } else if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (GameController.canMove(DirectionT.LEFT)) {
                GameController.move(DirectionT.LEFT);
            }
        } else if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (GameController.canMove(DirectionT.RIGHT)) {
                GameController.move(DirectionT.RIGHT);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            GameController.newGame(gameSize);
            GameController.getBoardT().generateTileT();
            GameController.getBoardT().generateTileT();
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(GameController.getStringRepresentation() + "\n");
        frame.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * @breif Begin painting items to GUI window that was opened.
     * @param g Graphics engine
     */
    public void paint(Graphics g) {
        int tileSize = 15 * GameController.getBoardT().getDimension();
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawString("2048", 250, 20);
        g2.drawString("Score: " + GameController.getScore(), 200 - 4 * String.valueOf(GameController.getScore()).length(), 40);
        g2.drawString("Highest Tile: " + GameController.getBoardT().getHighestTileT().getValue(), 280 - 4 * String.valueOf(
                GameController.getBoardT().getHighestTileT().getValue()).length(), 40);
        g2.drawString("Press 'Enter' to Start", 210, 315);
        g2.drawString("Use 'wasd' or Arrow Keys to move", 180, 335);
        if (GameController.gameOver()){
            if (GameController.getBoardT().getHighestTileT().getValue() >= 2048)
                g2.drawString("Congratulations! You Win!", 200, 355);
            g2.drawString("Press 'Enter' to restart", 200, 400);
        }
        g2.setColor(Color.gray);
        g2.fillRect(140, 50, 250, 250);
        for (int i = 0; i < GameController.getBoardT().getDimension(); i++)
            for (int j = 0; j < GameController.getBoardT().getDimension(); j++)
                drawTileTs(g, GameController.getBoardT().getTileT(i, j), j * tileSize + 150, i * tileSize + tileSize);
        if (GameController.gameOver()) {
            g2.setColor(Color.gray);
            g2.fillRect(140, 50, 250, 250);
            for (int i = 0; i < GameController.getBoardT().getDimension(); i++) {
                for (int j = 0; j < GameController.getBoardT().getDimension(); j++) {
                    if (GameController.getBoardT().getHighestTileT().getValue() >= 2048)
                        g2.setColor(Color.GREEN);
                    else
                        g2.setColor(Color.RED);
                    g2.fillRoundRect(j * tileSize + 150, i * tileSize + tileSize, 50, 50, 5, 5);
                    g2.setColor(Color.black);
                    g.drawString("GAME", j * tileSize + 160, i * tileSize + 75);
                    g.drawString("OVER", j * tileSize + 160, i * tileSize + 95);
                }
            }
        }
    }

    /** 
     * @brief Render every TileT on the Gameboard with correct information.
     * @param g Graphics engine
     * @param tile Information held by TileT instance
     * @param x X Coordinate on Graphics Panel
     * @param Y Y Coordinate on Graphics Panel
    */
    public void drawTileTs(Graphics g, TileT tile, int x, int y) {
        int tileValue = tile.getValue();
        int length = tile.getStringRepresentation().length();
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.lightGray);
        g2.fillRoundRect(x, y, 50, 50, 5, 5);
        g2.setColor(Color.black);
        if (tileValue > 0) {
            g2.setColor(tile.getColour());
            g2.fillRoundRect(x, y, 50, 50, 5, 5);
            g2.setColor(Color.black);
            g.drawString("" + tileValue, x + 25 - 3 * length, y + 25);
        }
    }

    public static void main(String[] args) {
        setUpGUI(gameSize);
    }
}
