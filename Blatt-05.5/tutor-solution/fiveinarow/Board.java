package fiveinarow;

/**
 * This class represents a board.
 *
 * @author Martin Thoma (based on a solution of BlackBlizzard)
 * @version 1.2
 *
 */
public class Board {
    /** The width of this board. This is the x-coordinate. */
    public final int width;

    /** The height of this board. This is the y-coordinate. */
    public final int height;

    /** The actual field where you place your stuff. */
    private final Token[][] field;

    /**
     * Constructor of a board.
     *
     * @param width the width of the board
     * @param height the height of the board
     */
    public Board(int width, int height) {
        if (width < 5 || height < 5) {
            throw new IllegalArgumentException(
                    "Board has to have at least size 5x5");
        }

        this.width = width;
        this.height = height;

        field = new Token[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                field[x][y] = Token.EMPTY;
            }
        }
    }

    public void setTile(Player player, int x, int y) {
        if (field[x][y] != Token.EMPTY) {
            throw new IllegalStateException(
                    "A tile is already there!");
        }

        switch (player) {
        case WHITE:
            field[x][y] = Token.WHITE;
            break;
        case BLACK:
            field[x][y] = Token.BLACK;
        default:
        }
    }

    public void setTile(Token token, int x, int y) {
        if (field[x][y] != Token.EMPTY) {
            throw new IllegalStateException(
                    "A tile is already there!");
        }

        field[x][y] = token;
    }

    public boolean isEmpty(int x, int y) {
        if (!isOnBoard(x, y)) {
            throw new IllegalArgumentException(x + "|" + y
                    + " is not on this " + width + "x" + height
                    + " board");
        }

        return field[x][y] == Token.EMPTY;
    }

    public boolean isOnBoard(int x, int y) {
        return 0 <= x && x < width && 0 <= y && y < height;
    }

    public Token getTile(int x, int y) {
        if (!isOnBoard(x, y)) {
            throw new IllegalArgumentException(
                    "Tile is not on board.");
        }

        return field[x][y];
    }

    public boolean isFull() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (field[x][y] == Token.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String representative = "";
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                representative += field[x][y].toString();
            }
            representative += "\n";
        }

        return representative;
    }
}
