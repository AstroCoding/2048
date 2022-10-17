package src;

public class expt {
    public static void main(String[] args) {
        try {
            new TileT(-2);
        }  catch (IllegalArgumentException e) {
            System.out.println("Error accounted for: Negative TileT value");
        } catch (Exception e) {
            System.out.println("Unintentional Error: ");
            System.out.println(e);
        }

        TileT[] tiles = new TileT[14];

        tiles[0] = new TileT(0);
        int value = 1;
        for (int i = 1; i < tiles.length; i++) {
            value *= 2;
            tiles[i] = new TileT(value);
        }

        for (TileT tileT : tiles) {
            System.out.print(tileT.getStringRepresentation() + " | ");
            System.out.println(tileT.getColour().getRed() + ", " + tileT.getColour().getGreen() + ", " + tileT.getColour().getBlue());
        }

        BoardT board = new BoardT(4);
        board.board = new TileT[][] {
            {new TileT(), new TileT(), new TileT(), new TileT()},
            {new TileT(), new TileT(), new TileT(), new TileT()},
            {new TileT(), new TileT(), new TileT(), new TileT()},
            {new TileT(4), new TileT(4), new TileT(4), new TileT(4)},
        };

        if (board.canMove(DirectionT.LEFT)) board.move(DirectionT.LEFT);
        System.out.printf("Testing basic board sliding: %b|%b%n", board.getTileT(3, 0).getValue() == board.getTileT(3, 1).getValue(), board.getTileT(3, 0).getValue() == 8);

        board.board = new TileT[][] { { new TileT(), new TileT(32), new TileT(64), new TileT(128) },
                { new TileT(), new TileT(128), new TileT(256), new TileT(256) },
                { new TileT(64), new TileT(256), new TileT(512), new TileT(512) },
                { new TileT(128), new TileT(256), new TileT(1024), new TileT(1024) }, };

        System.out.printf("Semi-populated Board: %s%d%n", board.getStringRepresentation(), board.getHighestTileT().getValue());
        board.generateTileT();
        board.generateTileT();
        System.out.printf("%b | %b%n", board.getTileT(0,0).getValue() != 0, board.getTileT(1, 0).getValue() != 0);
        if (board.canMove(DirectionT.RIGHT)) board.move(DirectionT.RIGHT);
        System.out.printf("%s%d | %b%n", board.getStringRepresentation(), board.getHighestTileT().getValue(), board.getHighestTileT().getValue() == 2048);

        board.board = new TileT[][] { { new TileT(2), new TileT(4), new TileT(2), new TileT(4) },
                { new TileT(4), new TileT(2), new TileT(4), new TileT(2) },
                { new TileT(2), new TileT(4), new TileT(2), new TileT(4) },
                { new TileT(4), new TileT(2), new TileT(4), new TileT(2) }, };

        System.out.printf("Unmovable Board:%n%s%b | %b | %b | %b%n", board.getStringRepresentation(), !board.canMove(DirectionT.UP), !board.canMove(DirectionT.DOWN),
                !board.canMove(DirectionT.LEFT), !board.canMove(DirectionT.RIGHT));

        // Begin board scenarios

        GameController.newGame(4);

        GameController.setBoardT(board);

        System.out.printf("Unmovable Board Game-Over? %b%n", GameController.gameOver());

        System.out.printf("Game Controller Print:%n%s", GameController.getStringRepresentation());

        board.board = new TileT[][] { { new TileT(), new TileT(32), new TileT(64), new TileT(128) },
                { new TileT(), new TileT(128), new TileT(256), new TileT(256) },
                { new TileT(64), new TileT(256), new TileT(512), new TileT(512) },
                { new TileT(128), new TileT(256), new TileT(1024), new TileT(2048) }, };

        System.out.printf("Winning Game Print:%n%s", GameController.getStringRepresentation());

        if (GameController.canMove(DirectionT.RIGHT)) GameController.move(DirectionT.RIGHT);

        System.out.printf("Game Controller Score update on move: %d%n", GameController.getScore());
        GameController.setScore(100000);
        System.out.printf("Game Controller Set Score: %d%n", GameController.getScore());
    }
}